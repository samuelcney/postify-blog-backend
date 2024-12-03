package com.spring.app.postify.controller;

import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    public LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userDto){
        try{
            String response = loginService.login(userDto);

            return ResponseEntity.status(200)
                    .body(new ApiResponse("success", response));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
