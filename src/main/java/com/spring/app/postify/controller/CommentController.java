package com.spring.app.postify.controller;

import com.spring.app.postify.dto.CommentRequestDTO;
import com.spring.app.postify.service.CommentService;
import com.spring.app.postify.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{id}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllComments(@PathVariable Integer id){
        try {
            return ResponseEntity.status(200)
                    .body(this.commentService.getAllCommentsByPost(id));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addComment(@PathVariable Integer id, @RequestBody CommentRequestDTO commentDTO){
        try {
            return ResponseEntity.status(201)
                    .body(this.commentService.addComment(commentDTO, id));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }
}
