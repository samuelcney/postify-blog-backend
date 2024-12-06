package com.spring.app.postify.service;

import com.spring.app.postify.model.Favorite;
import com.spring.app.postify.model.Post;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public Favorite addFavorite(Post post, User user){
        Optional<Favorite> existFavorite = this.favoriteRepository.findByPostAndUser(post, user);

        if (existFavorite.isPresent()) {
            this.favoriteRepository.delete(existFavorite.get());
            return null;
        } else {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setPost(post);
            return this.favoriteRepository.save(favorite);
        }
    }
}
