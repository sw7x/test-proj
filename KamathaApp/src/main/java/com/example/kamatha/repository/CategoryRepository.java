package com.example.kamatha.repository;

import java.util.List;
import com.example.kamatha.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);
    List<Category> findCategoryByCategoryOrderByCreatedDateDesc(String id);
}
