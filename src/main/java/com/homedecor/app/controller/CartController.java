package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.service.CartServiceImpl;
 
/************************************************************************************
 *          @author          Prince Verma
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/

 
@RestController
public class CartController {

	@Autowired
	private CartServiceImpl cartService;

	/************************************************************************************
	 * Method: addCart
     * Description: To handle a Http POST request
     * 
     * @Object cart                  - Cart's object
	 * @returns String               - Cart added Successfully
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("cart")
	public String addCart(@RequestBody Cart cart) throws CartException {
		this.cartService.addCart(cart);
		return "Cart added Successfully";
	}
 
	/************************************************************************************
	 * Method: getCartById
     * Description: To handle a Http GET request
     * 
     * @Param cartId                 - Cart's id
	 * @returns Optional<Cart>       - foundCart
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	
	@GetMapping("cart/{cartId}")
	public Optional<Cart> getCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartService.getCartById(cartId);
		return foundCart;
	}

	/************************************************************************************
	 * Method: updateCart
     * Description: To handle a Http PATCH request
     * 
     * @Object cart                  - Cart's object
	 * @returns Cart                 - CartUpdated
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PatchMapping("cart")
	public Cart updateCart(@RequestBody Cart cart) throws CartException {
		Cart cartUpdated = this.cartService.updateCart(cart);
		return cartUpdated;
	}

	/************************************************************************************
	 * Method: getAllCart
     * Description: To handle a Http GET request
     * 
     * @returns List<Cart>           - foundAllCart
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("cart/")
	public List<Cart> getAllCart() throws CartException {
		List<Cart> foundAllCart = this.cartService.getAllCarts();
		return foundAllCart;
	}

	/************************************************************************************
	 * Method: deleteCartById
     * Description: To handle a Http DELETE request
     * 
     * @Param cartId                 - Cart's id
	 * @returns String               - Cart deleted Successfully
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@DeleteMapping("cart/{cartId}")
	public String deleteCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		this.cartService.deleteCartById(cartId);
		return "Cart deleted successfully";
	}

	/************************************************************************************
	 * Method: getTotalAmountOfCartById
     * Description: To handle a Http GET request
     * 
     * @Param cartId                 - Cart's id
	 * @returns String               - Total amount of products present in cart
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("cart/customer/totalAmount/{cartId}")
	public String getTotalAmountOfCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		Optional<Double> totalBalance = this.cartService.totalAmountOfCustomerCartById(cartId);
		return "Total amount of " + cartId + " Id is :- " + totalBalance;
	}

	/************************************************************************************
	 * Method: countAllProductInCartById
     * Description: To handle a Http GET request
     * 
     * @Param cartId                 - Cart's id
	 * @returns String               - count of total products in cart
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("cart/customer/totalProduct/{cartId}")
	public String countAllProductInCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		Long totalProduct = this.cartService.totalProductInCustomerCartById(cartId);
		return "Total amount of " + cartId + " Id is :- " + totalProduct;
	}

	/************************************************************************************
	 * Method: addProductInCartByProductId
     * Description: To handle a Http PUT request
     * 
     * @Param customerId             - Customer's id
     * @param productId              - product's id
     * @param quantity               - quantity of product
	 * @returns String               - Product added in cart Successfully
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PutMapping("cart/addProduct/{customerId}/{productId}/{quantity}")
	public String addProductInCartByProductId(@PathVariable("customerId") Integer customerId,
			@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity)
			throws ProductException, CustomerException, CartException {
		this.cartService.addProductToCart(customerId, productId, quantity);
		return "Product added in cart Successfully";
	}
	
	/************************************************************************************
	 * Method: removeProductInCartByProductId
     * Description: To handle a Http PUT request
     * 
     * @Param customerId             - Customer's id
     * @param productId              - product's id
     * @param quantity               - quantity of product
	 * @returns String               - Product removed from cart Successfully
     * Created By                    - Prince Verma
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PutMapping("cart/removeProduct/{customerId}/{productId}/{quantity}")
	public String removeProductInCartByProductId(@PathVariable("customerId") Integer customerId,
			@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity)
			throws ProductException, CustomerException, CartException {
		this.cartService.removeProductFromCart(customerId, productId, quantity);
		return "Product removed from cart Successfully";
	}
}
