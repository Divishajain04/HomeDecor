package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import com.example.homedecor.dto.Category;
import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.ProductException;

public interface CategoryService {
	
public Boolean addCategory(Category category)throws CategoryException;
	
	public Optional<Category> getCategoryById(Integer categoryId)throws CategoryException;
	
	public Category updateCategory(Category category)throws CategoryException;
	
	public Boolean deleteCategoryById(Integer categoryId)throws CategoryException;
	
	public List<Category> getAllCategory()throws CategoryException;

	public Category getCategoryByName(String CategoryName)throws CategoryException;

}
