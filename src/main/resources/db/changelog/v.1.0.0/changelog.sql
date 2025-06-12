CREATE TABLE users (
id BIGINT NOT NULL AUTO_INCREMENT,
last_name VARCHAR(20) NOT NULL,
first_name VARCHAR(20) NOT NULL,
middle_name VARCHAR(20) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(100) NOT NULL DEFAULT 'abc123', -- дефолтный пароль. todo: Пока он стоит - карта заблочена, когда поменяют - активна
role VARCHAR(10) NOT NULL,
PRIMARY KEY(id),
UNIQUE (email)
);

CREATE TABLE cards (
id BIGINT NOT NULL AUTO_INCREMENT,
user_id BIGINT,
card_number VARCHAR(20) NOT NULL,
expiration_date DATE,
status VARCHAR(20) NOT NULL DEFAULT 'BLOCKED',
balance BIGINT,
currency VARCHAR(3) NOT NULL DEFAULT 'USD',
card_blocking_request BOOLEAN,
secret_num INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE currency_rate (
id BIGINT NOT NULL AUTO_INCREMENT,
USD DOUBLE(15,2) DEFAULT '1.0',
rate_USD_EUR DOUBLE(15,2),
rate_USD_RUB DOUBLE(15,2),
rate_USD_KZT DOUBLE(15,2),
currency_date TIMESTAMP,
PRIMARY KEY(id)
);