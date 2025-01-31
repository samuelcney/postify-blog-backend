package com.spring.app.postify.controller;

import com.spring.app.postify.dto.FavoriteRequestDTO;
import com.spring.app.postify.service.FavoriteService;
import com.spring.app.postify.service.PostService;
import com.spring.app.postify.utils.ApiResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{id}/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping
    public ResponseEntity<?> getFavoriteByPostId(@PathVariable Integer id){
        try{
           return ResponseEntity.status(200).body(
                this.favoriteService.getFavoritesByPostId(id)
        ); 
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }
    
    @GetMapping("/isFavorite")
    public ResponseEntity<?> isFavorite(@PathVariable Integer postId, @RequestParam Integer userId){
        try{
            boolean isFavorite = this.favoriteService.isFavorite(postId, userId);
            return ResponseEntity.status(200).body(
                    Map.of("isFavorite", isFavorite)
            );
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> favoritePost(@PathVariable Integer id, @RequestBody FavoriteRequestDTO dto){
        try{
            return ResponseEntity.status(200).body(
                    this.favoriteService.addFavorite(id, dto.userId())
            );
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }
}
