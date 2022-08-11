package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CategoryRepository;
import com.homedecor.app.dto.Category;
import com.homedecor.app.exception.CategoryException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Boolean addCategory(Category category) throws CategoryException {
		if(category==null)throw new CategoryException("Category not added please fill the mandatory details");
     	Optional<Category> foundCategory=this.categoryRepository.findById(category.getCategoryId());
		if(foundCategory.isPresent()) 
			throw new CategoryException("Category already exist");
		
		this.categoryRepository.save(category);
		return true;
	}

	@Override
	public Optional<Category> getCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> foundCategory=this.categoryRepository.findById(categoryId);
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for this Id"+categoryId);
		return this.categoryRepository.findById(categoryId);
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
     	Optional<Category> foundCategory=this.categoryRepository.findById(category.getCategoryId());
		if(foundCategory.isEmpty())throw new CategoryException("Category not found can't update");
		return this.categoryRepository.save(category);
	}

	@Override
	public Boolean deleteCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> foundCategory=this.categoryRepository.findById(categoryId);
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for this Id"+categoryId);
		this.categoryRepository.deleteById(categoryId);
		return true;
	}

	@Override
	public List<Category> getAllCategories() throws CategoryException {
		List<Category> foundCategories=this.categoryRepository.findAll();
		if(foundCategories.isEmpty())throw new CategoryException("No Categories found");
		return this.categoryRepository.findAll();
	}

	@Override
	public Category getCategoryByName(String categoryName) throws CategoryException {
		Category foundCategory=this.categoryRepository.findByCategoryNameStartingWith(categoryName);
		if(foundCategory==null)throw new CategoryException("No Category found by this name");
		return foundCategory;
	}

	
}
