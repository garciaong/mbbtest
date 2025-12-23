package com.mbb.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbb.test.dto.ProductDTO;
import com.mbb.test.entity.Product;
import com.mbb.test.service.IProductService;
import com.mbb.test.util.Pagination;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public Pagination<ProductDTO> getAllProducts(@RequestParam("page") Integer pageNo) {
        // Adjusting pageNo to be zero-based index
        return productService.getAllProducts(pageNo-1);
    }
    
    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product/update")
    public ProductDTO updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PutMapping("/product/add")
    public ProductDTO addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
