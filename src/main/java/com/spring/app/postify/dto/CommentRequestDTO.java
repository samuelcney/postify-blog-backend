package com.spring.app.postify.dto;

public record CommentRequestDTO(
        Integer userId,
        Integer postId,
        String content
) {}
