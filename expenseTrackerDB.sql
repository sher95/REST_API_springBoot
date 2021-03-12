drop database expenseTrackerdb;
drop user expenseTracker;

create user expenseTracker with password 'password';
create database expenseTrackerdb with template=template0 owner=expenseTracker;
\connect expenseTrackerdb;
alter default privileges grant all on tables to expenseTracker;
alter default privileges grant all on sequences to expenseTracker;


create table users(
user_id integer primary key not null,
f_name  varchar(20) not null,
l_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

create table categories(
cat_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar(60) not null
);

alter table categories add constraint cat_users_fk
foreign key (user_id) references users(user_id);

create table transactions(
trans_id integer primary key not null,
cat_id integer not null,
user_id integer not null,
amount numeric(10,2) not null,
note varchar(60) not null,
trans_date bigint not null
);

alter table transactions add constraint trans_cat_fk
foreign key (cat_id) references categories(cat_id);
alter table transactions add constraint trans_user_fk
foreign key (user_id) references users(user_id);


create sequence users_seq increment 1 start 1;
create sequence categories_seq increment 1 start 1;
create sequence transactions_seq increment 1 start 1000;