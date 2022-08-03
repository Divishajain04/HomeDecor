package com.example.homedecor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.homedecor.dto.Category;


@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer>{
    Category findByCategoryNameStartingWith(String prefix);
}
