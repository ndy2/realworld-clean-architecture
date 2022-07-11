package com.deukyun.realworld.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
@Import(P6spyLogMessageFormatConfiguration.class)
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String token;

    protected String createJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    protected void 사용자_등록(String username, String email, String password) throws Exception {
        ObjectNode registerUserRequest = objectMapper.createObjectNode();
        ObjectNode user = registerUserRequest.putObject("user");
        user.put("username", username);
        user.put("email", email);
        user.put("password", password);

        mockMvc.perform(post("/api/users")
                        .contentType(APPLICATION_JSON)
                        .content(createJson(registerUserRequest)))
                .andExpect(status().isOk());
    }

    //인증 후 토큰 저장
    protected void 인증(String email, String password) throws Exception {
        ObjectNode authenticationRequest = objectMapper.createObjectNode();
        ObjectNode user = authenticationRequest.putObject("user");
        user.put("email", email);
        user.put("password", password);

        MvcResult mvcResult = mockMvc.perform(post("/api/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(createJson(authenticationRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        token = objectMapper.readValue(content, JsonNode.class).get("user").get("token").asText();
    }

    protected void 프로필_업데이트(String email, String username, String password, String image, String bio) throws Exception {

        ObjectNode editUserRequest = objectMapper.createObjectNode();
        ObjectNode user = editUserRequest.putObject("user");
        user.put("email", email);
        user.put("username", username);
        user.put("password", password);
        user.put("bio", bio);
        user.put("image", image);

        mockMvc.perform(patch("/api/users")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(editUserRequest)))
                .andExpect(status().isOk());
    }

    protected void 팔로우(String username) throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/profiles/{username}/follow", username)
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    protected void 아티클_등록(String title, String description, String body, List<String> tagList) throws Exception {

        //given
        ObjectNode createArticleRequest = objectMapper.createObjectNode();
        ObjectNode article = createArticleRequest.putObject("article");
        article.put("title", title);
        article.put("description", description);
        article.put("body", body);
        if (tagList != null && !tagList.isEmpty()) {
            ArrayNode tagArrayNode = article.putArray("tagList");
            tagList.forEach(tagArrayNode::add);
        }

        mockMvc.perform(post("/api/articles")
                        .header(AUTHORIZATION, token)
                        .contentType(APPLICATION_JSON)
                        .content(createJson(createArticleRequest)))
                //then
                .andExpect(status().isOk());
    }

    protected void 페이보릿_추가(String slug) throws Exception {

        mockMvc.perform(post("/api/articles/{slug}/favorite", slug)
                        .header(AUTHORIZATION, token)
                        .contentType(APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

}
