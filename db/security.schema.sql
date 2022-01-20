create table authorities (
    id serial primary key,
    authority varchar(50) not null
);
create table users (
    id serial primary key,
    username varchar(50) not null unique ,
    password varchar(100) not null ,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);
insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');
