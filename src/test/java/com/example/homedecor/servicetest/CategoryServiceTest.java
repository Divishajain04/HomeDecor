package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Category;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.service.CategoryService;

@SpringBootTest
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@AfterEach
	void deleteCategoryByIdTest() throws CategoryException {
		assertEquals(true,this.categoryService.deleteCategoryById(4));
	}
	
	@Test
	void addCategoryTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(addCategory);
		
	}
	
	@Test
	void getCategoryByIdTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.getCategoryById(4));
	}
	
	@Test
	void getAllCategoryTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.getAllCategory());
	}
	
	@Test
	void getCategoryByNameTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.getCategoryByName("Furniture"));
	}
	
	@Test
	void updateCategoryTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.updateCategory(new Category(4,"furniture",null)));
	}

}
