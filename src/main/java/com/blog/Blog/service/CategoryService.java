package com.blog.Blog.service;

import com.blog.Blog.model.Category;
import com.blog.Blog.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepo categoryRepository;

    @Autowired
    public CategoryService(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

}
