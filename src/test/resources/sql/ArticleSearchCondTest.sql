-- 사용자 1 -> 프로필 4 -> 아티클 7
-- 사용자 2 -> 프로필 5 -> 아티클 8
-- 사용자 3 -> 프로필 6 -> 아티클 9

insert into users (email, password, id)
values ('user1@user1.user1', 'user1', 1);
insert into users (email, password, id)
values ('user2@user2.user2', 'user2', 2);
insert into users (email, password, id)
values ('user3@user3.user3', 'user3', 3);

insert into profile (bio, image, user_id, username, id)
values ('user1bio', 'user1image', 1, 'user1', 4);
insert into profile (bio, image, user_id, username, id)
values ('user2bio', 'user2image', 2, 'user2', 5);
insert into profile (bio, image, user_id, username, id)
values ('user3bio', 'user3image', 3, 'user3', 6);

insert into article (author_profile_id, body, created_at, description, slug, title, updated_at, id)
values (4, 'article7body', '2022-07-11 22:42:43', 'article7desc', 'article7slug', 'article7title', NULL, 7);

insert into article (author_profile_id, body, created_at, description, slug, title, updated_at, id)
values (5, 'article8body', '2022-07-11 22:42:43', 'article8desc', 'article8slug', 'article8title', NULL, 8);

insert into article (author_profile_id, body, created_at, description, slug, title, updated_at, id)
values (6, 'article9body', '2022-07-11 22:42:43', 'article9desc', 'article9slug', 'article9title', NULL, 9);

-- 태그 16, 17, 18
insert into tag (name, id)
values ('tag16', 16);
insert into tag (name, id)
values ('tag17', 17);
insert into tag (name, id)
values ('tag18', 18);


-- 아티클 7 -> 아티클 태그 19 -> 태그 16 - tag16

-- 아티클 8 -> 아티클 태그 20 -> 태그 16 - tag16
-- 아티클 8 -> 아티클 태그 21 -> 태그 17 - tag17

-- 아티클 9 -> 아티클 태그 22 -> 태그 16 - tag16
-- 아티클 9 -> 아티클 태그 23 -> 태그 17 - tag17
-- 아티클 9 -> 아티클 태그 24 -> 태그 18 - tag18
insert into article_tag (article_id, tag_id, id)
values (7, 16, 19);

insert into article_tag (article_id, tag_id, id)
values (8, 16, 20);
insert into article_tag (article_id, tag_id, id)
values (8, 17, 21);

insert into article_tag (article_id, tag_id, id)
values (9, 16, 22);
insert into article_tag (article_id, tag_id, id)
values (9, 17, 23);
insert into article_tag (article_id, tag_id, id)
values (9, 18, 24);

-- 사용자 1 -> 아티클 7,8,9 favorite
-- 사용자 2 -> 아티클 8,9 favorite
-- 사용자 3 -> 아티클 9 favorite
insert into favorite (article_id, user_id, id)
values (7, 1, 10);
insert into favorite (article_id, user_id, id)
values (8, 1, 11);
insert into favorite (article_id, user_id, id)
values (9, 1, 12);

insert into favorite (article_id, user_id, id)
values (8, 2, 13);
insert into favorite (article_id, user_id, id)
values (9, 2, 14);

insert into favorite (article_id, user_id, id)
values (9, 3, 15);

// user2 -> 팔로우 user1 -> 아티클 7
// user3 -> 팔로우 user2,user1 -> 아티클 7,8
insert into follow (ID, FOLLOWEE_ID, FOLLOWER_ID)
VALUES (25, 4, 6);
insert into follow (ID, FOLLOWEE_ID, FOLLOWER_ID)
VALUES (26, 5, 6);
insert into follow (ID, FOLLOWEE_ID, FOLLOWER_ID)
VALUES (27, 4, 5);


-- delete from favorite;
-- delete from article_tag;
-- delete from tag;
-- delete from article;
-- delete from profile;
-- delete from users;