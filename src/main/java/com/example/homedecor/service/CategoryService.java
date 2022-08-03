package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import com.example.homedecor.dto.Category;

import com.example.homedecor.exception.CategoryException;

public interface CategoryService {
	
public Boolean addCategory(Category category)throws CategoryException;
	
	public Optional<Category> getCategoryById(Integer categoryId)throws CategoryException;
	
	public Category updateCategory(Category category)throws CategoryException;
	
	public Boolean deleteCategoryById(Integer categoryId)throws CategoryException;
	
	public List<Category> getAllCategories()throws CategoryException;

	public Category getCategoryByName(String categoryName)throws CategoryException;

}
