package com.mbb.thirdparty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbb.thirdparty.dto.CategoryDTO;
import com.mbb.thirdparty.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
        .map(category -> new CategoryDTO.Builder().id(category.getId()).name(category.getName()).build())
        .toList();
    }
}
