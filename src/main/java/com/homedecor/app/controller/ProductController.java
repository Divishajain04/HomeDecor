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

/************************************************************************************
 *          @author          Nikhil Narwat
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	/************************************************************************************
	 * Method: addProduct
     * Description: To handle a Http POST request
     * 
     * @Object customer              - Product's object
	 * @returns String               - Product added Successfully
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("product")
	public String addProduct(@Valid @RequestBody Product product) throws ProductException , CategoryException{
		this.productService.addProducts(product);

		return "product added successfully";
	}

	/************************************************************************************
	 * Method: getProductById
     * Description: To handle a Http GET request
     * 
     * @Param productId              - Product's Id
	 * @returns Optional<Product>    - foundProduct
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("product/{productid}")
	public Optional<Product> getProductById(@PathVariable("productid") Integer productId) throws ProductException {
		return this.productService.getProductById(productId);
	}

	/************************************************************************************
	 * Method: updateProduct
     * Description: To handle a Http PATCH request
     * 
     * @Object product               - Product's object
	 * @returns Product              - updated Product
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PatchMapping("product")
	public Product updateProduct(@Valid @RequestBody Product product) throws ProductException {
		return this.productService.updateProduct(product);
	}

	/************************************************************************************
	 * Method: getAllProduct
     * Description: To handle a Http GET request
     * 
	 * @returns List<Product>        - List of Products
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("products")
	public List<Product> getAllProduct() throws ProductException {
		return this.productService.getAllProducts();
	}

	/************************************************************************************
	 * Method: deleteProductById
     * Description: To handle a Http DELETE request
     * 
     * @Param productId              - Product's Id
	 * @returns String               - Product deleted Successfully
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@DeleteMapping("product/{productid}")
	public String deleteProductById(@PathVariable("productid") Integer productId) throws ProductException {
		this.productService.deleteProductById(productId);
		return "product deleted successfully";
	}

	/************************************************************************************
	 * Method: findProductsHighToLow
     * Description: To handle a Http GET request
     * 
	 * @returns List<Product>        - return products in high to low price order
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("products/price/hightolow")
	public List<Product> findProductHighToLow() throws ProductException {
		return this.productService.findAllProductsHighToLow();
	}

	/************************************************************************************
	 * Method: findProductLowToHigh
     * Description: To handle a Http GET request
     * 
	 * @returns List<Product>        - return products in low to high price order
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("product/price/lowtohigh")
	public List<Product> findProductLowToHigh() throws ProductException {
		return this.productService.findAllProductsLowToHigh();
	}

	/************************************************************************************
	 * Method: findProductByName
     * Description: To handle a Http GET request
     * 
     * @Param productname            - Product's Name
	 * @returns List<Product>        - return list of products
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("products/{productname}")
	public List<Product> findProductByName(@PathVariable("productname") String productName) throws ProductException {
		return this.productService.findProductByName(productName);
	}
	
	/************************************************************************************
	 * Method: varietiesOfProduct
     * Description: To handle a Http GET request
     * 
	 * @returns Long                 - return count of varieties
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@GetMapping("varietiesofproduct")
	public Long countAllVaritiesOfProducts() throws ProductException {
		return this.productService.countAllVaritiesOfProduct();
	}
	
	/************************************************************************************
	 * Method: countOfProductByQuantity
     * Description: To handle a Http GET request
     * 
	 * @returns Integer              - count of products
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("totalstock")
	public Integer countOfProductByQuantity() throws ProductException{
		return this.productService.countTotalStock();
	}
	
}
