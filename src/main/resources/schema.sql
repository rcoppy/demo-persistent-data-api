CREATE TABLE users (
    user_id INT NOT NULL, 
    handle VARCHAR(30) NOT NULL,
    bcrypt_hash CHAR(60) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE messages (
    message_id INT NOT NULL,
    sender_id INT NOT NULL,
    recipient_id INT NOT NULL,
    contents VARCHAR(140),
    created_at DATETIME2 NOT NULL,
    PRIMARY KEY (message_id),
    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (recipient_id) REFERENCES users(user_id)
);