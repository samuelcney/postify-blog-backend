package com.spring.app.postify.service;

import com.spring.app.postify.model.Category;
import com.spring.app.postify.model.Post;
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

    public Category findById(Integer id){
        Category category = this.categoryRepository.findById(id)
                .orElse(null);

        if(category == null){
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        return category;
    }

    public Category create(Category categoryDto){

        List<Category> allCategories = this.findAll();

        boolean exists = allCategories.stream()
                .anyMatch(category -> category.getTitle().equalsIgnoreCase(categoryDto.getTitle()));

        if(exists){
            throw new IllegalArgumentException("A categoria com o titulo " + categoryDto.getTitle() + " já existe");
        }

        String title = categoryDto.getTitle();
        if (title != null && !title.isEmpty()) {
            title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
        }

        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("O título da categoria deve ter pelo menos 3 caracteres e não pode ser vazio.");
        }

        Category category = new Category();

        category.setTitle(title);

        return this.categoryRepository.save(category);
    }

    public Category update(Category categoryDto, Integer id){

        Category categoryExists = this.categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Categoria não encontrada"));

        List<Category> allCategories = this.findAll();

        boolean exists = allCategories.stream()
                .anyMatch(category -> category.getTitle().equalsIgnoreCase(categoryDto.getTitle()));

        if(exists){
            throw new IllegalArgumentException("A categoria com o titulo " + categoryDto.getTitle() + " já existe");
        }

        String title = categoryDto.getTitle();
        if (title != null && !title.isEmpty()) {
            title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
        }

        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("O título da categoria deve ter pelo menos 3 caracteres e não pode ser vazio.");
        }

        categoryExists.setTitle(title);

        return this.categoryRepository.save(categoryExists);
    }

    public void delete(Integer id){
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Categoria não encontrada"));

        this.categoryRepository.delete(category);
    }
}
