package com.spring.app.postify.controller;

import com.spring.app.postify.dto.UserRequestDTO;
import com.spring.app.postify.model.User;
import com.spring.app.postify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(userService.findById(id));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }

    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO dto){

        try{
            User newUser = userService.create(dto);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
