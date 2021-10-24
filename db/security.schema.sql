create table users (
    username varchar(50) not null ,
    password varchar(100) not null ,
    enabled boolean default true,
    PRIMARY KEY (username)
);
create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null ,
    FOREIGN KEY (username) references users(username)
);