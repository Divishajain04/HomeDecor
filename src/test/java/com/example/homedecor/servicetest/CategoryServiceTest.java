package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Category;
<<<<<<< HEAD
import com.example.homedecor.exception.CategoryException;
=======
import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.CustomerException;
>>>>>>> e3ffa5e41852d9a0dcda220094575c0d83d614e6
import com.example.homedecor.service.CategoryService;

@SpringBootTest
public class CategoryServiceTest {
<<<<<<< HEAD
	
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

=======

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
	
>>>>>>> e3ffa5e41852d9a0dcda220094575c0d83d614e6
}
