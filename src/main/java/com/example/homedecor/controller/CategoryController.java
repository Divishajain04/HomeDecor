package com.example.homedecor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.homedecor.dto.Category;
import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("category")
	public String addCategory(@RequestBody Category category) throws CategoryException {
		this.categoryService.addCategory(category);
		return "Category added Successfully";
	}
	
	@GetMapping("category/")
	public List<Category> getAllCategory() throws CategoryException{
		return this.categoryService.getAllCategory();
	}
	

	@GetMapping("category/{categoryName}")
	public Category findCategoryByName(@PathVariable("categoryName") String categoryName) throws CategoryException{
		return this.categoryService.findCategoryByName(categoryName);
	}

}
