package com.mbb.test.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private String category;
    private Integer categoryId;

    ProductDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
        this.categoryId = builder.categoryId;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String category;
        private Integer categoryId;
        
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder categoryId(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }
        
        public ProductDTO build() {
            return new ProductDTO(this);
        }
    }
}
