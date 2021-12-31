DROP TABLE IF EXISTS movies;
CREATE TABLE movies AS SELECT * FROM CSVREAD('classpath:movies.csv', NULL, 'charset=UTF-8');