package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.ProductException;

public interface ProductService {
	
	public Boolean addProducts(Product product)throws ProductException; 
	
	public Optional<Product> getProductById(Integer productId)throws ProductException;
	
	public List<Product> getAllProducts()throws ProductException;
	
	public Boolean deleteProductById(Integer productId)throws ProductException;
	
	public Product updateProduct(Product product)throws ProductException;
	
	public List<Product> findAllProductsHighToLow() throws ProductException;

	public List<Product> findAllProductsLowToHigh() throws ProductException;
	
	public Product findProductByName(String productName) throws ProductException;
	 
	public long countAllVaritiesOfProduct() throws ProductException;
	
	public Integer countTotalStock() throws ProductException;
}
