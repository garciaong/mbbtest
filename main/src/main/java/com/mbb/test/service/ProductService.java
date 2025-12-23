package com.mbb.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbb.test.dto.ProductDTO;
import com.mbb.test.entity.Product;
import com.mbb.test.external.CategoryService;
import com.mbb.test.repository.ProductRepository;
import com.mbb.test.util.Pagination;

@Service
@Transactional
public class ProductService implements IProductService {
    private static final int PAGE_SIZE = 10;
    @Autowired
    private ProductRepository productRepository;
    
    public Pagination<ProductDTO> getAllProducts(Integer pageNo) {
        PageRequest pageable = PageRequest.of(pageNo, PAGE_SIZE);
        Page<Product> products = productRepository.findAll(pageable);
        return new Pagination<ProductDTO>(
            pageNo+1,
            products.getTotalPages(),
            products.hasNext(),
            products.hasPrevious(),
            products.getContent().stream().map(product -> new ProductDTO.Builder()
            .id(product.getId())
            .name(product.getName())
            .category(CategoryService.categoryData.get(product.getCategoryId()).getName())
            .categoryId(product.getCategoryId())
            .build()).toList());
        }
        
        public ProductDTO getProductById(String id) {
            Optional<Product> product = productRepository.findById(Integer.parseInt(id));
            ProductDTO record = new ProductDTO.Builder()
            .id(product.get().getId())
            .name(product.get().getName())
            .category(CategoryService.categoryData.get(product.get().getCategoryId()).getName())
            .categoryId(product.get().getCategoryId())
            .build();
            return record;
        }
        
        public ProductDTO addProduct(Product product) {
            Product savedProduct = productRepository.save(product);
            ProductDTO record = new ProductDTO.Builder()
            .id(savedProduct.getId())
            .name(savedProduct.getName())
            .category(CategoryService.categoryData.get(savedProduct.getCategoryId()).getName())
            .categoryId(savedProduct.getCategoryId())
            .build();
            return record;
        }
        
        public ProductDTO updateProduct(Product product) {
            Product savedProduct = productRepository.save(product);
            ProductDTO record = new ProductDTO.Builder()
            .id(savedProduct.getId())
            .name(savedProduct.getName())
            .category(CategoryService.categoryData.get(savedProduct.getCategoryId()).getName())
            .categoryId(savedProduct.getCategoryId())
            .build();
            return record;
        }
    }
    