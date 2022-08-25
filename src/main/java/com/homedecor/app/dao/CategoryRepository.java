package com.homedecor.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.homedecor.app.dto.Category;


@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer>{
    List<Category> findByCategoryNameStartingWith(String prefix);
}
