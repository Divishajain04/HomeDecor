package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import com.homedecor.app.dto.Category;
import com.homedecor.app.exception.CategoryException;

public interface CategoryService {
	
	public Boolean addCategory(Category category)throws CategoryException;
	
	public Optional<Category> getCategoryById(Integer categoryId)throws CategoryException;
	
	public Category updateCategory(Category category)throws CategoryException;
	
	public Boolean deleteCategoryById(Integer categoryId)throws CategoryException;
	
	public List<Category> getAllCategories()throws CategoryException;

	public List<Category> getCategoryByName(String categoryName)throws CategoryException;

}
