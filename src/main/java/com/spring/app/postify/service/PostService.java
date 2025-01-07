package com.spring.app.postify.service;

import com.spring.app.postify.dto.PostRequestDTO;
import com.spring.app.postify.model.Category;
import com.spring.app.postify.model.Post;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.CategoryRepository;
import com.spring.app.postify.repository.PostRepository;
import com.spring.app.postify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Post> findAll(){
        return this.postRepository.findAll();
    }

    public Post findById(Integer id){
        Post post = this.postRepository.findById(id)
                .orElse(null);

        if(post == null){
            throw new IllegalArgumentException("Post não encontrado");
        }

        return post;
    }

    public List<Post> findAllByCategoryId(Integer id){
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria não encontrada"));

        return this.postRepository.findByCategory(category);
    }

    public Post create(PostRequestDTO postDTO){

        Optional<User> user = this.userRepository.findById(postDTO.userId());
        Optional<Category> category = this.categoryRepository.findById(postDTO.categoryId());

        if(user.isEmpty()){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if(category.isEmpty()){
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        Post post = new Post();

        post.setContent(postDTO.content());
        post.setUser(user.get());
        post.setCategory(category.get());

        return this.postRepository.save(post);
    }

    public Post update(PostRequestDTO postDTO, Integer id){
        Post post = this.postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Post não encontrado"));

        if(postDTO.content() != null && !postDTO.content().isEmpty()){
            post.setContent(postDTO.content());
        }

        if(postDTO.categoryId() != null){
            Category category = this.categoryRepository.findById(postDTO.categoryId())
                    .orElseThrow(()-> new IllegalArgumentException("Categoria não encontrada"));

            post.setCategory(category);
        }

        post.setUpdatedAt(LocalDateTime.now());

        return this.postRepository.save(post);
    }


    public void delete(Integer id){
        Post post = this.postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Post não encontrado"));

        this.postRepository.delete(post);
    }
}
