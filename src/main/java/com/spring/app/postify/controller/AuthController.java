package com.spring.app.postify.controller;

import com.spring.app.postify.dto.LoginResponseDTO;
import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.model.User;
import com.spring.app.postify.service.LoginService;
import com.spring.app.postify.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    public LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userDto){

        if (userDto.email() == null || userDto.email().isEmpty() || userDto.password() == null || userDto.password().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Preencha todos os campos"));
        }
        try{
            LoginResponseDTO data = this.loginService.login(userDto);

            return ResponseEntity.status(200)
                    .body(new ApiResponse(HttpStatus.OK.name(),"success", data));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
