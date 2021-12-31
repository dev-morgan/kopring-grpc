DROP TABLE IF EXISTS users;
CREATE TABLE users AS SELECT * FROM CSVREAD('classpath:users.csv', NULL, 'charset=UTF-8');