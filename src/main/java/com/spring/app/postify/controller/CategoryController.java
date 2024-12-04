package com.spring.app.postify.controller;

import com.spring.app.postify.model.Category;
import com.spring.app.postify.service.CategoryService;
import com.spring.app.postify.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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
}
