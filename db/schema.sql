create table rules (
    id serial primary key ,
    name varchar(2000)
);
create table accident_types(
    id serial primary key,
    name varchar(2000)
);
create table accidents(
    id serial primary key ,
    name varchar(1000),
    text varchar(2000),
    address varchar(1000),
    accident_type int references accident_types(id)
);
create table accidents_rules (
    accident_id int references accidents(id),
    rule_id int references rules(id),
    UNIQUE (accident_id, rule_id)
)