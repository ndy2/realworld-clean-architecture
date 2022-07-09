package com.deukyun.realworld.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(properties = {
        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE"
})
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

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

    //인증 후 토큰 반환
    protected String 인증(String email, String password) throws Exception {
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

        return objectMapper.readValue(content, JsonNode.class).get("user").get("token").asText();
    }
}
