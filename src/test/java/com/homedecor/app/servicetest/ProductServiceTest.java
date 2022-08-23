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
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertEquals(1,this.productService.countAllVaritiesOfProduct());
		assertEquals(true,categoryService.deleteCategoryById(1));
		assertThrows(ProductException.class, () -> this.productService.deleteProductById(21));
	}

	@Test
	void addProductTest() throws ProductException, CategoryException {
		assertThrows(ProductException.class, () -> this.productService.addProducts(null));
		Category category = new Category(421,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(421, "Sofa", "Double layer Foam", 20000.0, 20,421);
		assertTrue(this.productService.addProducts(product));
		assertEquals(true,categoryService.deleteCategoryById(421));
		assertThrows(ProductException.class, () -> this.productService.deleteProductById(421));
	}

	@Test
	void getProductByIdTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getProductById(21));
		assertEquals(true,categoryService.deleteCategoryById(1));
		assertThrows(ProductException.class, () -> this.productService.getProductById(21));
	}

	@Test
	void getAllProductTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getAllProducts());
		assertEquals(true,categoryService.deleteCategoryById(1));
		assertThrows(ProductException.class, () -> this.productService.getAllProducts());
	}

	@Test
	void updateProductTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.updateProduct(new Product(21, "Chair", "Double layer Foam", 20000.0, 20,1)));
		assertEquals(true,categoryService.deleteCategoryById(1));
//		assertThrows(ProductException.class,
//				() -> this.productService.updateProduct(new Product(21, "Chair", "Double layer Foam", 20000.0, 20,1)));
	}

	@Test
	void findAllProductHighToLowTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsHighToLow());
		assertEquals(true,categoryService.deleteCategoryById(1));
		assertThrows(ProductException.class, () -> this.productService.findAllProductsHighToLow());
	}

	@Test
	void findAllProductLowToHighTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsLowToHigh());
		assertEquals(true,categoryService.deleteCategoryById(1));
		assertThrows(ProductException.class, () -> this.productService.findAllProductsLowToHigh());
	}

	@Test
	void findProductByNameTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findProductByName("Sofa"));
		assertEquals(true,categoryService.deleteCategoryById(1));
//		assertThrows(ProductException.class, () -> this.productService.findProductByName("Sofa"));
	}
	
	@Test
	void countTotalStockTest() throws ProductException, CategoryException {
		Category category = new Category(1,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(21, "Sofa", "Double layer Foam", 20000.0, 20,1);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.countTotalStock());
		assertEquals(true,categoryService.deleteCategoryById(1));
		//assertThrows(ProductException.class, () -> this.productService.findProductByName("Sofa"));
}
}
