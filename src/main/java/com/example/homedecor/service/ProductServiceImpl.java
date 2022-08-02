package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.ProductRepository;
import com.example.homedecor.dto.Category;
import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.OrderException;
import com.example.homedecor.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Boolean addProducts(Product product) throws ProductException {
		if (product == null) {
			throw new ProductException("Product not added");
		}
		Optional<Product> addProductResult = this.productRepository.findById(product.getProductId());
		if (addProductResult.isPresent()) {
			throw new ProductException("Product Id is already present in the record");
		} else {
			this.productRepository.save(product);
		}
		return true;
	}

	@Override
	public Optional<Product> getProductById(Integer productId) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) {
			throw new ProductException("Invalid Product Id");
		}
		return foundProduct;
	}

	@Override
	public List<Product> getAllProduct() throws ProductException {
		List<Product> productList = this.productRepository.findAll();
		if (productList.isEmpty()) {
			throw new ProductException("Product list is empty");
		}
		return productList;
	}

	@Override
	public Boolean deleteProductById(Integer productId) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) {
			throw new ProductException("Product Id not exist");
		}
		this.productRepository.deleteById(productId);
		return true;
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(product.getProductId());
		if (foundProduct.isEmpty()) {
			throw new ProductException("Invalid Product Id");
		}
		return this.productRepository.save(product);
	}

	@Override
	public List<Product> findAllProductHighToLow() throws ProductException {
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceDesc();
		if (productList.isEmpty()) {
			throw new ProductException("Product list is empty");
		}
		return productList;
	}

	@Override
	public List<Product> findAllProductLowToHigh() throws ProductException {
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceAsc();
		if (productList.isEmpty()) {
			throw new ProductException("Product list is empty");
		}
		return productList;
	}

	@Override
	public Product findProductByName(String productName) throws ProductException {
		
		Product foundProduct = this.productRepository.findByProductNameStartingWith(productName);
		if (foundProduct == null) {
			throw new ProductException("Product not found");
		}
		return foundProduct;
	}

}
