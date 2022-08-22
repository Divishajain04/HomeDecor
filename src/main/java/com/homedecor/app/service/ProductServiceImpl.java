package com.homedecor.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CategoryRepository;
import com.homedecor.app.dao.ProductRepository;
import com.homedecor.app.dto.Category;
import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.exception.ProductException;


/************************************************************************************
 * @author      Nikhil Narwat 
 * Description: It is a service class that provides the
 *              services for adding, deleting, updating, get all products, get
 *              product's by id, find product's with price low to high and high to low, 
 *              find product by name, count varieties of product and count total stock. 
 * Version      1.0 
 * Created Date 16-08-2022
 ************************************************************************************/

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/************************************************************************************
	 * Method: addProducts 
	 * Description: To add a single product or multiple products.
	 * 
	 * @object wishlist          - Adding product by providing proper product details.
	 * @returns Boolean          - true, if product is added otherwise throws ProductException
	 * @throws ProductException  - It is raised if mandatory details are not provided or if the product's id is already present.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Boolean addProducts(Product product) throws ProductException , CategoryException {
		if (product == null) {
			throw new ProductException("Product not added! Please fill the mandatory field.");
		}
		Optional<Product> addProductResult = this.productRepository.findById(product.getProductId());
		if (addProductResult.isPresent()) {
			throw new ProductException("Product Id is already present in the record! Try with new Id.");
		} else {
			Integer categoryId = 	product.getCategoryId();
			Optional<Category> foundCategory = this.categoryRepository.findById(categoryId);
			Category getCategory = foundCategory.get();
			List<Product> products = new ArrayList<>();
			if (foundCategory.isPresent()) {
				products.addAll(getCategory.getProduct());
				products.add(product);
				getCategory.setProduct(products);
				this.categoryService.updateCategory(getCategory);
				this.productRepository.save(product);
				
			}
				
			
		}
		return true;
	}
	
	/************************************************************************************
	 * Method: getProductById 
	 * Description: To fetch a product by it's id.
	 * 
	 * @param productId          - product's id
	 * @returns Optional         - present, if product is already present in the database otherwise throws ProductException
	 * @throws ProductException  - It is raised if the product's id is not present.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Optional<Product> getProductById(Integer productId) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) {
			throw new ProductException("Invalid Product Id");
		}
		return foundProduct;
	}
	
	/************************************************************************************
	 * Method: getAllProducts
	 * Description: To fetch every product from the database.
	 * 
	 * @returns List             - list of product, if product is already present in the database otherwise throws ProductException
	 * @throws ProductException  - It is raised if the product table is empty.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public List<Product> getAllProducts() throws ProductException {
		List<Product> productList = this.productRepository.findAll();
		if (productList.isEmpty()) {
			throw new ProductException("Product list is empty");
		}
		return productList;
	}
	
	/************************************************************************************
	 * Method: deleteProductById
	 * Description: To delete a product by it's id.
	 * 
	 * @returns Boolean          - true, delete the product if the product's id exist otherwise throws ProductException
	 * @throws ProductException  - It is raised if the product's id is not present in our database.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Boolean deleteProductById(Integer productId) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) {
			throw new ProductException("Product Id not exist");
		}
		this.productRepository.deleteById(productId);
		return true;
	}
	
	/************************************************************************************
	 * Method: updateProduct
	 * Description: To update a product by it's id.
	 * 
	 * @returns Product          - product, update the product's details if the product's id exist otherwise throws ProductException
	 * @throws ProductException  - It is raised if the product's id is not present in our database.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(product.getProductId());
		if (foundProduct.isEmpty()) {
			throw new ProductException("Product Id not exist");
		}
		return this.productRepository.save(product);
	}
	
	/************************************************************************************
	 * Method: findAllProductsHighToLow
	 * Description: To filter the products in terms of price from high to low(descending order)
	 * 
	 * @returns List             - list, gives the list of the products in descending order otherwise throws ProductException
	 * @throws ProductException  - It is raised if the product table is empty.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public List<Product> findAllProductsHighToLow() throws ProductException {
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceDesc();
		if (productList.isEmpty()) {
			throw new ProductException("Products are not available in the record");
		}
		return productList;
	}
	
	/************************************************************************************
	 * Method: findAllProductsLowToHigh
	 * Description: To filter the products in terms of price from low to high(Ascending order)
	 * 
	 * @returns List             - list, gives the list of the products in ascending order otherwise throws ProductException
	 * @throws ProductException  - It is raised if the product table is empty.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public List<Product> findAllProductsLowToHigh() throws ProductException {
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceAsc();
		if (productList.isEmpty()) {
			throw new ProductException("Products are not available in the record");
		}
		return productList;
	}
	
	/************************************************************************************
	 * Method: findProductByName
	 * Description: To search the products by their name
	 * 
	 * @returns Product          - product, searches the product by the name which we provide otherwise throws ProductException
	 * @throws ProductException  - It is raised if their is no product in the database with the same name.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public List<Product> findProductByName(String productName) throws ProductException {
		
		List<Product> foundProduct = this.productRepository.findByProductNameStartingWith(productName);
		if (foundProduct == null) {
			throw new ProductException("Product not found");
		}
		return foundProduct;
	}
	
	/************************************************************************************
	 * Method: countAllVaritiesOfProduct
	 * Description: To count the products by their variety
	 * 
	 * @returns Long             - totalVarietiesProduct, count the product by their varieties otherwise throws ProductException
	 * @throws ProductException  - It is raised if their is no product in the database.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public long countAllVaritiesOfProduct() throws ProductException {
		Long totalVarietyProduct = this.productRepository.count();
		if (totalVarietyProduct == 0) {
			throw new ProductException("No Product is listed in the record");
		}
		return totalVarietyProduct;
	}
	
	/************************************************************************************
	 * Method: countTotalStock
	 * Description: To count the products by their total quantity
	 * 
	 * @returns Integer          - Total Quantity, count all the products quantity present in the database otherwise throws ProductException
	 * @throws ProductException  - It is raised if their is no product in the database.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Integer countTotalStock() throws ProductException {
	List<Product> products =	this.productRepository.findAll();
	Integer totalQuantity =  0;
		for (Product product : products) {
			totalQuantity =totalQuantity + product.getQuantity();
		}
		return totalQuantity;
	}

}
