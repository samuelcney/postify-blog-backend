package com.spring.app.postify.dtos;

import javax.validation.constraints.NotBlank;

public record UserRequestDTO(

        String username,
        String email,
        String password,
        String firstName,
        String lastName
) {
}
