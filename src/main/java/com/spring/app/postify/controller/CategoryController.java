package com.spring.app.postify.controller;

import com.spring.app.postify.model.Category;
import com.spring.app.postify.service.CategoryService;
import com.spring.app.postify.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        try{
            return ResponseEntity.ok(
                    this.categoryService.findAll()
            );
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(
                    this.categoryService.findById(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category categoryDTO){
        try{
            return ResponseEntity.status(201)
                    .body(this.categoryService.create(categoryDTO));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category , @PathVariable Integer id){
        try{
            return ResponseEntity.status(200)
                    .body(this.categoryService.update(category, id));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        try{
            this.categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }
}
