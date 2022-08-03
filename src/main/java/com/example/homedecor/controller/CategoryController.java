package com.example.homedecor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.homedecor.dto.Category;

import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("category")
	public String addCategory(@RequestBody Category category) throws CategoryException  {
		try {
			this.categoryService.addCategory(category);
		} catch (CategoryException e) {
			throw new CategoryException(e.getMessage());
		}
		return "Category added Successfully";
	}

	@PatchMapping("category")
	public Category updateCategory(@RequestBody Category category) throws CategoryException {
		Category categoryUpdated=null;
		 try {
			categoryUpdated=this.categoryService.updateCategory(category);
		} catch (CategoryException e) {
			throw new CategoryException(e.getMessage());
		}
		 return categoryUpdated;
	}

	@GetMapping("category/{categoryId}")
	public Optional<Category> getCategoryById(@PathVariable ("categoryId") Integer categoryId) throws CategoryException  {
		Optional<Category> foundCategory=null;
		 try {
			foundCategory=this.categoryService.getCategoryById(categoryId);
		} catch (CategoryException e) {
			throw new CategoryException(e.getMessage());
		}
		 return foundCategory;
	}
	
	@GetMapping("category/")
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> foundAllCategory=null;
		 try {
			foundAllCategory=this.categoryService.getAllCategory();
		} catch (CategoryException e) {
			throw new CategoryException(e.getMessage());
		}
		 return foundAllCategory;
	}
	
	@DeleteMapping("category/{categoryId}")
	public String deleteCategoryById(@PathVariable ("categoryId") Integer categoryId) throws CategoryException {
		try {
			this.categoryService.deleteCategoryById(categoryId);
		} catch (CategoryException e) {
			throw new CategoryException(e.getMessage());
		}
		return "Category deleted Successfully";
	}
	
	@GetMapping("categories/{categoryName}")
	public Category getCategoryByName(@PathVariable ("categoryName") String categoryName) throws CategoryException  {
		Category foundCategory=null;
		 try {
			foundCategory=this.categoryService.getCategoryByName(categoryName);
		} catch (CategoryException e) {
			throw new CategoryException(e.getMessage());
		}
		 return foundCategory;
	}

}
