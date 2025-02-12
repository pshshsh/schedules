
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255),
                       created_at DATETIME(6),
                       modified_at DATETIME(6)
);

CREATE TABLE schedules (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           contents LONGTEXT,
                           user_id BIGINT,
                           created_at DATETIME(6),
                           modified_at DATETIME(6),
                           FOREIGN KEY (user_id) REFERENCES users(id)
);
