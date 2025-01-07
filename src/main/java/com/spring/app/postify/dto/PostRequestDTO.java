package com.spring.app.postify.dto;

public record PostRequestDTO(
        String content,
        Integer userId,
        Integer categoryId
) {
}
