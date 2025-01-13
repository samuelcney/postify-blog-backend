package com.spring.app.postify.service;

import com.spring.app.postify.dto.LoginResponseDTO;
import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    public LoginResponseDTO login(UserRequestDTO userDto){

        Optional<User> user = this.userRepository
                .findByEmail(userDto.email());

        if (user.isEmpty()){
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        Boolean isPasswordValid = passwordEncoder.matches(userDto.password(), user.get().getPassword());

        if(!isPasswordValid){
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = Jwts.builder()
                .setSubject(user.get().getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return new LoginResponseDTO(token, user.get());
    }
}
