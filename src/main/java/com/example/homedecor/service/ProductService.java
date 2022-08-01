package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.ProductException;

public interface ProductService {
	
	public Boolean addProducts(Product product)throws ProductException; 
	
	public Optional<Product> getProductById(Integer productId)throws ProductException;
	
	public List<Product> getAllProduct()throws ProductException;
	
	public Boolean deleteProductById(Integer productId)throws ProductException;
	
	public Product updateProduct(Product product)throws ProductException;
	
	public List<Product> findAllProductHighToLow() throws ProductException;

	public List<Product> findAllProductLowToHigh() throws ProductException;
	
	public Product findProductByName(String productName) throws ProductException;
}
