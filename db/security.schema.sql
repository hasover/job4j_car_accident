create table authorities (
    id serial primary key,
    authority varchar(50) not null
);
create table users (
    id serial primary key,
    username varchar(50) not null ,
    password varchar(100) not null ,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);
insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users(username, password, authority_id) VALUES
('user', '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
 (select id from authorities where authority = 'ROLE_ADMIN'));