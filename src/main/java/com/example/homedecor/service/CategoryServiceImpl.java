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
		if (category == null) {
			throw new CategoryException("Category not added");
		}
		Optional<Category> addCategoryResult = this.categoryRepositary.findById(category.getCategoryId());
		if (addCategoryResult.isPresent()) {
			throw new CategoryException("Category Id is already present in the record");
		} else {
			this.categoryRepositary.save(category);
		}
		return true;
	}

	@Override
	public Optional<Category> getCategoryById(Integer categoryId) throws CategoryException {

		Optional<Category> resultCategory = this.categoryRepositary.findById(categoryId);
		if (resultCategory.isEmpty()) {
			throw new CategoryException("This category Id is not present in the database");
		}
		return resultCategory;
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		return this.categoryRepositary.save(category);
	}

	@Override
	public Boolean deleteCategoryById(Integer categoryId) throws CategoryException {
		this.categoryRepositary.deleteById(categoryId);
		return true;
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> categories = this.categoryRepositary.findAll();
		if (categories.isEmpty()) {
			throw new CategoryException("Category is not present in the database");
		}
		return categories;
	}

	@Override
	public Category findCategoryByName(String categoryName) throws CategoryException {
		return this.categoryRepositary.findByCategoryNameStartingWith(categoryName);
	}

}
