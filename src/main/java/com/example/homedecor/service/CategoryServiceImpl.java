package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.CategoryRepository;
import com.example.homedecor.dto.Category;

import com.example.homedecor.exception.CategoryException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepositary;

	@Override
	public Boolean addCategory(Category category) throws CategoryException {
		if(category==null)throw new CategoryException("Category not added");
     	Optional<Category> foundCategory=this.categoryRepositary.findById(category.getCategoryId());
		if(foundCategory.isPresent()) 
			throw new CategoryException("Category already exist");
		
		this.categoryRepositary.save(category);
		return true;
	}

	@Override
	public Optional<Category> getCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> foundCategory=this.categoryRepositary.findById(categoryId);
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for this Id"+categoryId);
		return this.categoryRepositary.findById(categoryId);
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		if(category==null)throw new CategoryException("Category not updated");
     	Optional<Category> foundCategory=this.categoryRepositary.findById(category.getCategoryId());
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for update");
		return this.categoryRepositary.save(category);
	}

	@Override
	public Boolean deleteCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> foundCategory=this.categoryRepositary.findById(categoryId);
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for this Id"+categoryId);
		this.categoryRepositary.deleteById(categoryId);
		return true;
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> foundCategory=this.categoryRepositary.findAll();
		if(foundCategory.isEmpty())throw new CategoryException("Category is empty");
		return this.categoryRepositary.findAll();
	}

	@Override
	public Category getCategoryByName(String CategoryName) throws CategoryException {
		Category foundCategory=this.categoryRepositary.findByCategoryNameStartingWith(CategoryName);
		if(foundCategory==null)throw new CategoryException("No Category avilable by this name");
		return foundCategory;
	}

	
}
