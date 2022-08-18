package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("product")
	public String addProduct(@Valid @RequestBody Product product) throws ProductException , CategoryException{
		this.productService.addProducts(product);

		return "product added successfully";
	}

	@GetMapping("product/{productId}")
	public Optional<Product> getProductById(@PathVariable("productId") Integer productId) throws ProductException {
		Optional<Product> foundProduct = this.productService.getProductById(productId);
		return foundProduct;
	}

	@PatchMapping("product")
	public Product updateProduct(@Valid @RequestBody Product product) throws ProductException {
		Product foundProduct = this.productService.updateProduct(product);
		return foundProduct;
	}

	@GetMapping("product/allProduct")
	public List<Product> getAllProduct() throws ProductException {
		List<Product> foundProducts = this.productService.getAllProducts();
		return foundProducts;
	}

	@DeleteMapping("product/{productId}")
	public String deleteProductById(@PathVariable("productId") Integer productId) throws ProductException {
		this.productService.deleteProductById(productId);
		return "product deleted successfully";
	}

	@GetMapping("product/sortedHighToLow")
	public List<Product> findProductHighToLow() throws ProductException {
		List<Product> foundProducts = this.productService.findAllProductsHighToLow();
		return foundProducts;
	}

	@GetMapping("product/SortedLowToHigh")
	public List<Product> findProductLowToHigh() throws ProductException {
		List<Product> foundProducts = this.productService.findAllProductsLowToHigh();
		return foundProducts;
	}

	@GetMapping("product/productByName/{productName}")
	public List<Product> findProductByName(@PathVariable("productName") String productName) throws ProductException {
		List<Product> foundProduct = this.productService.findProductByName(productName);
		return foundProduct;
	}

	@GetMapping("varietiesOfProduct")
	public Long countAllVaritiesOfProducts() throws ProductException {
		return this.productService.countAllVaritiesOfProduct();
	}
	
	@GetMapping("countOfProductByQuantity")
	public Integer countOfProductByQuantity() throws ProductException{
		return this.productService.countTotalStock();
	}
	
}
