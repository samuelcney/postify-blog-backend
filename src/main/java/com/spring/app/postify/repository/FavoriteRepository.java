package com.spring.app.postify.repository;

import com.spring.app.postify.model.Favorite;
import com.spring.app.postify.model.Post;
import com.spring.app.postify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Optional<Favorite> findByPostAndUser(Post post, User user);
    List<Favorite> findByPost(Post post);
    List<Favorite> findByUser(User user);

    Optional<Favorite> findByPostIdAndUserId(Integer postId, Integer userId);
}
