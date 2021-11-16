insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, authority_id) values ('user', '$2a$10$.qXisgTGC6Fu1dpEeoTiM.1VvyOz/Uy6kPZAGGukNiTHhHEQvX0ta', 1);