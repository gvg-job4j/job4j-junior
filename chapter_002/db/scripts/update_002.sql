create table if not exists items (
   id serial primary key not null,
   name varchar(100),
   request_id varchar (100),
   user_id int REFERENCES users(id),
   status_id int REFERENCES status(id),
   category_id int REFERENCES category(id)
);