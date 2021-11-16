create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  author_id INT,
  created timestamp without time zone not null default now()
);

create table messages (
 id serial primary key,
 post_id INT,
 author_id INT,
 text varchar(2000),
 created timestamp without time zone not null default now()
);

create table posts_messages (
 post_id INT references posts(id),
 messages_id INT references messages(id)
);

create table users (
 id serial primary key,
 username varchar(200),
 password varchar(200),
 authority_id INT,
 enabled boolean default true
);

create table authorities (
 id serial primary key,
 authority varchar(50)
);