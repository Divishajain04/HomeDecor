package com.example.homedecor.controller;

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

import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("product")
	public String addProduct(@RequestBody Product product) throws ProductException {
		try {
			this.productService.addProducts(product);
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
		return "product added successfully";
	}
	
	@GetMapping("product/{productId}")
	public Optional<Product> getProductById(@PathVariable ("productId") Integer productId) throws ProductException  {
		Optional<Product> foundProduct=null;
		try {
			foundProduct= this.productService.getProductById(productId);
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
		return foundProduct;
	}
	
	@PatchMapping("product")
	public Product updateProduct(@RequestBody Product product) throws ProductException {
		Product foundProduct=null;
		try {
			foundProduct=this.productService.updateProduct(product);
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
		return foundProduct;
	}
	
	@GetMapping("product/")
	public List<Product> getAllProduct() throws ProductException {
		List<Product> foundProduct=null;
		try {
			foundProduct= this.productService.getAllProduct();
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
		return foundProduct;
	}
	
	@DeleteMapping("product/{productId}")
	public String deleteProductById(@PathVariable ("productId") Integer productId) throws ProductException {
		try {
			this.productService.deleteProductById(productId);
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
		return "product deleted successfully";
	}
	
	

	@GetMapping("product/highToLow")
	public List<Product> findProductHighToLow() throws ProductException {
		List<Product> foundProduct=null;
		try {
			foundProduct= this.productService.findAllProductHighToLow();
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
	return foundProduct;
	}
	
	@GetMapping("product/lowToHigh")
	public List<Product> findProductLowToHigh() throws ProductException {
		List<Product> foundProduct=null;
		try {
			foundProduct= this.productService.findAllProductLowToHigh();
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
	return foundProduct;
	}
	
	@GetMapping("products/{productName}")
	public Product findProductByName(@PathVariable("productName") String productName) throws ProductException{
		Product foundProduct=null;
		try {
			foundProduct= this.productService.findProductByName(productName);
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		}
		return foundProduct;
	}
}
