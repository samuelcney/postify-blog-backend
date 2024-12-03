package com.spring.app.postify.service;

import com.spring.app.postify.model.Post;
import com.spring.app.postify.repository.PostRepository;
import com.spring.app.postify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return this.postRepository.findAll();
    }
}
