package com.homedecor.app.controller;

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

import com.homedecor.app.dto.Category;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("category")
	public String addCategory(@RequestBody Category category) throws CategoryException  {
			this.categoryService.addCategory(category);
		return "Category added Successfully";
	}

	@PatchMapping("category")
	public Category updateCategory(@RequestBody Category category) throws CategoryException {
		Category categoryUpdated = this.categoryService.updateCategory(category);
		 return categoryUpdated;
	}

	@GetMapping("category/{categoryId}")
	public Optional<Category> getCategoryById(@PathVariable ("categoryId") Integer categoryId) throws CategoryException  {
		Optional<Category> foundCategory=this.categoryService.getCategoryById(categoryId);
		 return foundCategory;
	}
	
	@GetMapping("category/allCategory")
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> foundAllCategory=this.categoryService.getAllCategories();
		 return foundAllCategory;
	}
	
	@DeleteMapping("category/{categoryId}")
	public String deleteCategoryById(@PathVariable ("categoryId") Integer categoryId) throws CategoryException {
			this.categoryService.deleteCategoryById(categoryId);
		return "Category deleted Successfully";
	}
	
	@GetMapping("category/name/{categoryName}")
	public Category getCategoryByName(@PathVariable ("categoryName") String categoryName) throws CategoryException  {
		Category foundCategory=this.categoryService.getCategoryByName(categoryName);
		 return foundCategory;
	}

}
