package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Category;
import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.service.CategoryService;
import com.homedecor.app.service.ProductService;

@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Test
	void countAllVaritiesOfProductTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.countAllVaritiesOfProduct());
		assertEquals(true, categoryService.deleteCategoryById(111));
		assertThrows(ProductException.class, () -> this.productService.deleteProductById(111));
	}

	@Test
	void addProductTest() throws ProductException, CategoryException {
		assertThrows(ProductException.class, () -> this.productService.addProducts(null));
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertEquals(true, categoryService.deleteCategoryById(111));
		assertThrows(ProductException.class, () -> this.productService.deleteProductById(111));
	}

	@Test
	void getProductByIdTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getProductById(111));
		assertEquals(true, categoryService.deleteCategoryById(111));
		assertThrows(ProductException.class, () -> this.productService.getProductById(111));
	}

	@Test
	void getAllProductTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getAllProducts());
		assertEquals(true, categoryService.deleteCategoryById(111));
	}

	@Test
	void updateProductTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(
				this.productService.updateProduct(new Product(111, "Chair", "Double layer Foam", 20000.0, 20, 111)));
		assertEquals(true, categoryService.deleteCategoryById(111));
	}

	@Test
	void findAllProductHighToLowTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsHighToLow());
		assertEquals(true, categoryService.deleteCategoryById(111));
	}

	@Test
	void findAllProductLowToHighTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsLowToHigh());
		assertEquals(true, categoryService.deleteCategoryById(111));
	}

	@Test
	void findProductByNameTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findProductByName("Sofa"));
		assertEquals(true, categoryService.deleteCategoryById(111));
	}

	@Test
	void countTotalStockTest() throws ProductException, CategoryException {
		Category category = new Category(111, "Furniture", null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20, 111);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.countTotalStock());
		assertEquals(true, categoryService.deleteCategoryById(111));
	}
}
