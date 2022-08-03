package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.service.ProductService;

@SpringBootTest
 class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	
   @Test
	void deleteProductByIdTest() throws ProductException {
	   Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
	assertTrue(this.productService.addProducts(product));
		assertEquals(true,this.productService.deleteProductById(21));
		assertThrows(ProductException.class,()->this.productService.deleteProductById(21));
	}
	
	@Test
	void addProductTest() throws ProductException {
		assertThrows(ProductException.class,()->this.productService.addProducts(null));
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getProductById(21));
		assertThrows(ProductException.class,()->this.productService.addProducts(product));
		assertEquals(true,this.productService.deleteProductById(21));
		
	}
	
	@Test
	void getProductByIdTest() throws ProductException {
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getProductById(21));
		assertEquals(true,this.productService.deleteProductById(21));
		assertThrows(ProductException.class,()->this.productService.getProductById(21));
	}
	
	@Test
	void getAllProductTest() throws ProductException {
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getAllProducts());
		assertEquals(true,this.productService.deleteProductById(21));
		assertThrows(ProductException.class,()->this.productService.getAllProducts());
	}
	
	@Test
	void updateProductTest() throws ProductException {
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.updateProduct(new Product(21,"Chair","Double layer Foam",20000.0,20)));
		assertEquals(true,this.productService.deleteProductById(21));
		assertThrows(ProductException.class,()->this.productService.updateProduct(new Product(21,"Chair","Double layer Foam",20000.0,20)));
	}
	
	@Test
	void findAllProductHighToLowTest() throws ProductException {
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsHighToLow());
		assertEquals(true,this.productService.deleteProductById(21));
		assertThrows(ProductException.class,()->this.productService.findAllProductsHighToLow());
	}
	
	@Test
	void findAllProductLowToHighTest() throws ProductException {
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsLowToHigh());
		assertEquals(true,this.productService.deleteProductById(21));
		assertThrows(ProductException.class,()->this.productService.findAllProductsLowToHigh());
	}
	
	@Test
	void findProductByNameTest() throws ProductException {
		Product product=new Product(21,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findProductByName("Sofa"));
		assertEquals(true,this.productService.deleteProductById(21));
	assertThrows(ProductException.class,()->this.productService.findProductByName("Sofa"));
	}
	
}
