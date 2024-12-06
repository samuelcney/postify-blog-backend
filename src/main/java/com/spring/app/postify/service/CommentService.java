package com.spring.app.postify.service;

import com.spring.app.postify.dto.CommentRequestDTO;
import com.spring.app.postify.model.Comment;
import com.spring.app.postify.model.Post;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.CommentRepository;
import com.spring.app.postify.repository.PostRepository;
import com.spring.app.postify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Comment> getAllCommentsByPost(Integer postId){
        Optional<Post> post = this.postRepository.findById(postId);

        if(post.isEmpty()){
            throw new IllegalArgumentException("Post não encontrado");
        }

        return this.commentRepository.findByPost(post.get());
    }

    public Comment addComment(CommentRequestDTO commentDTO, Integer id){
        Optional<Post> post = this.postRepository.findById(id);
        Optional<User> user = this.userRepository.findById(commentDTO.userId());

        if(post.isEmpty()){
            throw new IllegalArgumentException("Post não encontrado");
        }
        if(user.isEmpty()){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if(commentDTO.content().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível inserir comentários vazios...");
        }

        Comment comment = new Comment();

        comment.setPost(post.get());
        comment.setUser(user.get());
        comment.setContent(commentDTO.content());

        return this.commentRepository.save(comment);
    }
}
