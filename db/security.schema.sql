CREATE TABLE users
(
    id serial primary key,
    username VARCHAR(50)  NOT NULL UNIQUE ,
    password VARCHAR(100) NOT NULL UNIQUE ,
    enabled  boolean default true,
    role_id int not null references roles(id)
);
insert into users (enabled, username, password, role_id)
values (true,
        'admin',
        '$2a$10$dCVBjGI1tJcyqbgm1EulfuGQ7p3.tjl1tfdAszKqWN4Epp7K7CVAe',
        (select id from roles where name = 'ADMIN'));

CREATE TABLE roles
(
    id serial primary key,
    username  VARCHAR(50) NOT NULL UNIQUE
);
insert into roles (username) values ('ROLE_ADMIN');
insert into roles (username) values ('ROLE_USER');
