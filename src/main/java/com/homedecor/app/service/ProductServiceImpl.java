package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.ProductRepository;
import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Boolean addProducts(Product product) throws ProductException {
		if (product == null) {
			throw new ProductException("Product not added! Please fill the mandatory field.");
		}
		Optional<Product> addProductResult = this.productRepository.findById(product.getProductId());
		if (addProductResult.isPresent()) {
			throw new ProductException("Product Id is already present in the record! Try with new Id.");
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
	public List<Product> getAllProducts() throws ProductException {
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
			throw new ProductException("Product Id not exist");
		}
		return this.productRepository.save(product);
	}

	@Override
	public List<Product> findAllProductsHighToLow() throws ProductException {
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceDesc();
		if (productList.isEmpty()) {
			throw new ProductException("Products are not available in the record");
		}
		return productList;
	}

	@Override
	public List<Product> findAllProductsLowToHigh() throws ProductException {
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceAsc();
		if (productList.isEmpty()) {
			throw new ProductException("Products are not available in the record");
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

	@Override
	public long countAllVaritiesOfProduct() throws ProductException {
		Long totalVarietyProduct = this.productRepository.count();
		if (totalVarietyProduct == 0) {
			throw new ProductException("No Product is listed in the record");
		}
		return totalVarietyProduct;
	}

}
