MYSQL DDL문
------------------------------------------------------------------
create database Test;

use Test;

CREATE TABLE request_book (
  request_id int NOT NULL AUTO_INCREMENT,
  book_name varchar(30) NOT NULL,
  author varchar(30) NOT NULL,
  publisher varchar(30) NOT NULL,
  isbn bigint NOT NULL,
  date datetime NOT NULL,
  completion tinyint(1) NOT NULL,
  user_id varchar(30)  NOT NULL,
  password varchar(30) NOT NULL,
  PRIMARY KEY (request_id)
)
CREATE TABLE reservation (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(255) NOT NULL,
                             table_name VARCHAR(255) NOT NULL
);
CREATE TABLE book (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255),
                      author VARCHAR(255),
                      rented BOOLEAN
);
CREATE TABLE client (
  `id` varchar(30) NOT NULL,
  `pw` varchar(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);
create table script_table
(
    script_hits     int          null,
    created_time    datetime(6)  null,
    id              bigint auto_increment
        primary key,
    updated_time    datetime(6)  null,
    script_writer   varchar(20)  not null,
    script_contents text         null,
    script_pass     varchar(255) null,
    script_title    varchar(255) null
);
----------------------------------------------------------------------
* 실행 시 주의사항 *
yml에서 username과 password는 mysql 계정 입력,
url의 맨 뒤의 /test는 ddl.txt에서 create database test; 으로 생성하였기에 url 뒤에 test로 붙여줌
