package com.spring.app.postify.dto;

public record UserRequestDTO(

        String username,
        String email,
        String password,
        String firstName,
        String lastName
) {
}
