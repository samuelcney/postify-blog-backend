create table posts(
    id int not null primary key auto_increment,
    title varchar(255) not null,
    content text not null,
    user_id int not null,
    category_id int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    foreign key (user_id) references users(id),
    foreign key (category_id) references categories(id)
);
