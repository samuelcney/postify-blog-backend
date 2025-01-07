package com.spring.app.postify.service;

import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.model.User;
import com.spring.app.postify.repository.UserRepository;
import com.spring.app.postify.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
        User user = this.userRepository.findById(id)
                .orElse(null);

        if(user == null){
           throw new IllegalArgumentException("Usuário não encontrado");
        }

        return user;
    }

    public User create(@Valid UserRequestDTO userDTO){

        if(!EmailValidator.isValidEmail(userDTO.email())){
            throw new IllegalArgumentException("O e-mail fornecido é inválido");
        }

        Optional<User> userExist = this.userRepository.findByEmail(userDTO.email());

        if(userDTO.password().length() < 8){
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
        }

        String hashPassword = passwordEncoder.encode(userDTO.password());

        if(userExist.isPresent()){
            throw new IllegalArgumentException("O email informado já está cadastrado");
        }

        User newUser = new User();

        newUser.setUsername(userDTO.username());
        newUser.setEmail(userDTO.email());
        newUser.setPassword(hashPassword);
        newUser.setFirstName(userDTO.firstName());
        newUser.setLastName(userDTO.lastName());

        return userRepository.save(newUser);
    }

    public User update(UserRequestDTO userDTO, Integer id){
        User user = this.userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));

        if(!EmailValidator.isValidEmail(userDTO.email())){
            throw new IllegalArgumentException("O e-mail fornecido é inválido");
        }

        Optional<User> userAlreadyExist = this.userRepository.findByEmail(userDTO.email());

        if(userAlreadyExist.isPresent() && !userAlreadyExist.get().getEmail().equals(user.getEmail())){
            throw new IllegalArgumentException("O email informado já está cadastrado");
        }else {
            user.setEmail(userDTO.email());
        }

        if(userDTO.username() != null && !userDTO.username().isEmpty()){
           user.setUsername(userDTO.username());
        }

        if(userDTO.firstName() != null && !userDTO.firstName().isEmpty()){
            user.setFirstName(userDTO.firstName());
        }

        if(userDTO.lastName() != null && !userDTO.lastName().isEmpty()){
            user.setLastName(userDTO.lastName());
        }

        user.setUpdatedAt(LocalDateTime.now());

        return this.userRepository.save(user);
    }


    public void delete(Integer id){
        User user = this.userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));

        this.userRepository.delete(user);
    }
}
