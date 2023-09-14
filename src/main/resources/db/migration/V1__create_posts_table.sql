#create table posts with id, user_id, title and body in mysql
CREATE TABLE posts (
                        uuid BIGINT NOT NULL PRIMARY KEY,
                        id BIGINT NOT NULL,
                        user_id BIGINT NOT NULL,
                        title VARCHAR(255) NOT NULL,
                        body TEXT NOT NULL
);

CREATE TABLE posts_seq (
                       uuid BIGINT NOT NULL PRIMARY KEY,
                       id BIGINT NOT NULL,
                       user_id BIGINT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       body TEXT NOT NULL
);





