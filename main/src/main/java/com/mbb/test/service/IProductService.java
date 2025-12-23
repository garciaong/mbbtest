package com.mbb.test.service;

import com.mbb.test.dto.ProductDTO;
import com.mbb.test.entity.Product;
import com.mbb.test.util.Pagination;

public interface IProductService {
    public Pagination<ProductDTO> getAllProducts(Integer pageNo);
    
    public ProductDTO getProductById(String id);

    public ProductDTO addProduct(Product product);

    public ProductDTO updateProduct(Product product);
}
