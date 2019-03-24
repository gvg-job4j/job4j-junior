CREATE DATABASE requests;

CREATE TABLE users
(id int primary key,
name varchar (100));

CREATE TABLE roles(
id int primary key,
name varchar (100));

CREATE TABLE rules(
id int primary key,
name varchar (100));

CREATE TABLE rules_to_roles(
id int primary key,
role_id int REFERENCES roles(id),
rigth_id int REFERENCES rules(id));

CREATE TABLE category(
id int primary key,
name varchar (100));

CREATE TYPE status AS ENUM(
'Opened',
'Handles',
'Completed',
'Returned',
'Closed');

CREATE TABLE requests(
id int primary key,
name varchar (100),
user_id int REFERENCES users(id),
category_id int REFERENCES category(id),
status status);

CREATE TABLE comment(
id int primary key,
name varchar (100),
request_id int REFERENCES requests(id));


CREATE TABLE files(
id int primary key,
name varchar (100),
request_id int REFERENCES requests(id));