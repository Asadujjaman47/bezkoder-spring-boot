CREATE TABLE tutorials
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    published BOOLEAN
);