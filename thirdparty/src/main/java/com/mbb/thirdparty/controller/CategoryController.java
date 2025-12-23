package com.mbb.thirdparty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbb.thirdparty.dto.CategoryDTO;
import com.mbb.thirdparty.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
}
