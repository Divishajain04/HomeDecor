package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Category;
import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.CustomerException;
import com.example.homedecor.service.CategoryService;

@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	@Test
	void addCategoryTest() throws CategoryException {
		Category category = new Category(4,"Handicarft",null);
		assertTrue(categoryService.addCategory(category));
		assertNotNull(category);
	}
	

	@AfterEach
	void deleteCategoryByIdTest() throws CategoryException {
		assertEquals(true,categoryService.deleteCategoryById(4));
	}
	
	
	
	@Test
	void getCategoryByIdTest() throws CategoryException {
		Category category = new Category(4,"Handicraft",null);
		assertTrue(categoryService.addCategory(category));
		Category category2 = categoryService.getCategoryById(4).get();
		Integer id = category2.getCategoryId();
		assertEquals(id,4);
		assertNotNull(categoryService.getCategoryById(id));
	}
	
}
