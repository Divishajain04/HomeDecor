package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.service.ProductService;

@SpringBootTest
 class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	
   @Test
	void deleteProductByIdTest() throws ProductException {
	   Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.deleteProductById(5));
	}
	
	@Test
	void addProductTest() throws ProductException {
		assertThrows(ProductException.class,()->this.productService.addProducts(null));
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getProductById(5));
		assertThrows(ProductException.class,()->this.productService.addProducts(product));
		assertEquals(true,this.productService.deleteProductById(5));
		
	}
	
	@Test
	void getProductByIdTest() throws ProductException {
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getProductById(5));
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.getProductById(5));
	}
	
	@Test
	void getAllProductTest() throws ProductException {
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.getAllProducts());
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.getAllProducts());
	}
	
	@Test
	void updateProductTest() throws ProductException {
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.updateProduct(new Product(5,"Chair","Double layer Foam",20000.0,20)));
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.updateProduct(new Product(5,"Chair","Double layer Foam",20000.0,20)));
	}
	
	@Test
	void findAllProductHighToLowTest() throws ProductException {
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsHighToLow());
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.findAllProductsHighToLow());
	}
	
	@Test
	void findAllProductLowToHighTest() throws ProductException {
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findAllProductsLowToHigh());
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.findAllProductsLowToHigh());
	}
	
	@Test
	void findProductByNameTest() throws ProductException {
		Product product=new Product(5,"Sofa","Double layer Foam",20000.0,20);
		assertTrue(this.productService.addProducts(product));
		assertNotNull(this.productService.findProductByName("Sofa"));
		assertEquals(true,this.productService.deleteProductById(5));
		assertThrows(ProductException.class,()->this.productService.findProductByName("Sofa"));
	}
	
	

}
