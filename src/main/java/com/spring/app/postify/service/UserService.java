package com.spring.app.postify.service;

import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User findById(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public User create(UserRequestDTO user){

        Optional<User> userExist = this.userRepository.findByEmail(user.email());

        String hashPassword = passwordEncoder.encode(user.password());

        if(userExist.isPresent()){
            throw new IllegalArgumentException("O email informado já está cadastrado");
        }

        User newUser = new User();

        newUser.setUsername(user.username());
        newUser.setEmail(user.email());
        newUser.setPassword(hashPassword);
        newUser.setFirstName(user.firstName());
        newUser.setLastName(user.lastName());

        return userRepository.save(newUser);
    }
}
