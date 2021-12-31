DROP TABLE IF EXISTS movie;
CREATE TABLE movie AS SELECT * FROM CSVREAD('classpath:movie.csv', NULL, 'charset=UTF-8');