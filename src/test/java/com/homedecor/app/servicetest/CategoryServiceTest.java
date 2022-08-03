package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Category;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.service.CategoryService;

@SpringBootTest
 class CategoryServiceTest {

	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	void deleteCategoryByIdTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertEquals(true,this.categoryService.deleteCategoryById(4));
		assertThrows(CategoryException.class,()->this.categoryService.deleteCategoryById(4));
	}
	
	@Test
	void addCategoryTest() throws CategoryException {
		assertThrows(CategoryException.class,()->this.categoryService.addCategory(null));
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertThrows(CategoryException.class,()->this.categoryService.addCategory(new Category(4,"Furniture",null)));
		assertEquals(true,this.categoryService.deleteCategoryById(4));
		
		
	}
	
	@Test
	void getCategoryByIdTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.getCategoryById(4));
		assertEquals(true,this.categoryService.deleteCategoryById(4));
		assertThrows(CategoryException.class,()->this.categoryService.getCategoryById(4));

	}
	
	@Test
	void getAllCategoryTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.getAllCategories());
		assertEquals(true,this.categoryService.deleteCategoryById(4));
		assertThrows(CategoryException.class,()->this.categoryService.getAllCategories());

	}
	
	@Test
	void getCategoryByNameTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.getCategoryByName("Furniture"));
		assertEquals(true,this.categoryService.deleteCategoryById(4));
		assertThrows(CategoryException.class,()->this.categoryService.getCategoryByName("Furniture"));

	}
	
	@Test
	void updateCategoryTest() throws CategoryException {
		Category addCategory=new Category(4,"Furniture",null);
		assertTrue(this.categoryService.addCategory(addCategory));
		assertNotNull(this.categoryService.updateCategory(new Category(4,"furniture",null)));
		assertEquals(true,this.categoryService.deleteCategoryById(4));
		assertThrows(CategoryException.class,()->this.categoryService.updateCategory(new Category(4,"Furni",null)));
		
	}


}
