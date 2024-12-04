create table users(
    id int not null primary key auto_increment,
    username varchar(255) not null,
    email varchar(255) unique not null,
    password_hash varchar(255) not null,
    first_name varchar(100),
    last_name varchar(100),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);