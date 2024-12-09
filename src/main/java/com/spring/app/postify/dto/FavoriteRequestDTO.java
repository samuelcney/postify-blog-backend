package com.spring.app.postify.dto;

public record FavoriteRequestDTO(
        Integer userId,
        Integer postId
) {
}
