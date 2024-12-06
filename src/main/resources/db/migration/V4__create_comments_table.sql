create table comments(
    id int primary key auto_increment,
    content text not null,
    post_id int not null,
    user_id int not null,
    created_at timestamp not null,
    foreign key (post_id) references posts(id),
    foreign key (user_id) references users(id)
);