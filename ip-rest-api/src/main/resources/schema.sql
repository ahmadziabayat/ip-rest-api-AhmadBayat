CREATE TABLE USER(
USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
FIRST_NAME VARCHAR(64),
LAST_NAME VARCHAR(64),
EMAIL_ADDRESS VARCHAR(64)
);

CREATE TABLE IP_ADDRESS(
    IP_ADDRESS_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    IP_ADDRESS VARCHAR(64),
    CURRENT_STATUS VARCHAR(64),
    CIDR_BLOCK VARCHAR(64),
    CREATED_BY_USER BIGINT,
    UPDATED_BY_USER BIGINT
);

ALTER TABLE IP_ADDRESS ADD FOREIGN KEY (CREATED_BY_USER) REFERENCES USER(USER_ID);
ALTER TABLE IP_ADDRESS ADD FOREIGN KEY (UPDATED_BY_USER) REFERENCES USER(USER_ID);