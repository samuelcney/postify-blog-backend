package com.spring.app.postify.service;

import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(UserRequestDTO userDto){

        Optional<User> user = this.userRepository
                .findByEmail(userDto.email());

        if (user.isEmpty()){
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        Boolean isPasswordValid = passwordEncoder.matches(userDto.password(), user.get().getPassword());

        if(!isPasswordValid){
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        return "Login bem-sucedido! Seja bem vindo " + user.get().getFirstName();
    }
}
