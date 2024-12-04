package com.spring.app.postify.service;

import com.spring.app.postify.model.Category;
import com.spring.app.postify.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return this.categoryRepository.findAll();
    }

    public Category findByID(Integer id){
        return this.categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria não encontrada"));
    }

    public Category create(Category categoryDto){

        List<Category> allCategories = this.findAll();

        boolean exists = allCategories.stream()
                .anyMatch(category -> category.getTitle().equalsIgnoreCase(categoryDto.getTitle()));

        if(exists){
            throw new IllegalArgumentException("A categoria com o titulo" + categoryDto.getTitle() + "já existe");
        }

        Category category = new Category();

        category.setTitle(categoryDto.getTitle());

        return this.categoryRepository.save(category);
    }
}
