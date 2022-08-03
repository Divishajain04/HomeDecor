package com.homedecor.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Category;


@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer>{
    Category findByCategoryNameStartingWith(String prefix);
}
