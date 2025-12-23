package com.mbb.thirdparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbb.thirdparty.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
