package com.mbb.test.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;
    private String name;

    CategoryDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder {
        private Integer id;
        private String name;
        
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }
}
