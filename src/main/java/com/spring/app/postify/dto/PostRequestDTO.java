package com.spring.app.postify.dto;

public record PostRequestDTO(
        String title,
        String content,
        Integer userId,
        Integer categoryId
) {
}
