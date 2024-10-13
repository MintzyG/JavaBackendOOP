drop database finances_db;
drop user finances;

create user finances with password 'root';
create database finances_db with template=template0 owner=finances;

\connect finances_db;

alter default privileges grant all on tables to finances;
alter default privileges grant all on  sequences to finances;

create table users(
    user_id integer primary key not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(255) not null unique,
    password text not null
);

create table categories (
    category_id integer primary key not null,
    user_id integer not null,
    title varchar(20) not null,
    description varchar(255) not null,
    foreign key (user_id) references users(user_id)
);

create table transactions (
    transaction_id integer primary key not null,
    category_id integer not null,
    user_id integer not null,
    amount numeric(10,2) not null,
    note varchar(255),
    transaction_date bigint not null,
    foreign key (category_id) references categories(category_id),
    foreign key (user_id) references users(user_id)
);

create sequence user_seq increment 1 start 1;
create sequence cat_seq increment 1 start 1;
create sequence trans_seq increment 1 start 1000;