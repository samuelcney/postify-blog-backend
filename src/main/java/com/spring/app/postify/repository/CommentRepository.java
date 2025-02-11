package com.spring.app.postify.repository;

import com.spring.app.postify.model.Comment;
import com.spring.app.postify.model.Post;
import com.spring.app.postify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
