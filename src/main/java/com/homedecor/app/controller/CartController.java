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

@RestController
public class CartController {

	@Autowired
	private CartServiceImpl cartService;

	@PostMapping("cart")
	public String addCart(@RequestBody Cart cart) throws CartException {
		this.cartService.addCart(cart);
		return "Cart added Successfully";
	}

	@GetMapping("cart/{cartId}")
	public Optional<Cart> getCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartService.getCartById(cartId);
		return foundCart;
	}

	@PatchMapping("cart")
	public Cart updateCart(@RequestBody Cart cart) throws CartException {
		Cart cartUpdated = this.cartService.updateCart(cart);
		return cartUpdated;
	}

	@GetMapping("cart/")
	public List<Cart> getAllCart() throws CartException {
		List<Cart> foundAllCart = this.cartService.getAllCarts();
		return foundAllCart;
	}

	@DeleteMapping("cart/{cartId}")
	public String deleteCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		this.cartService.deleteCartById(cartId);
		return "Cart deleted successfully";
	}

	@GetMapping("cart/customer/totalAmount/{cartId}")
	public String getTotalAmountOfCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		Optional<Double> totalBalance = this.cartService.totalAmountOfCustomerCartById(cartId);
		return "Total amount of " + cartId + " Id is :- " + totalBalance;
	}

	@GetMapping("cart/customer/totalProduct/{cartId}")
	public String countAllProductInCartById(@PathVariable("cartId") Integer cartId) throws CartException {
		Long totalProduct = this.cartService.totalProductInCustomerCartById(cartId);
		return "Total amount of " + cartId + " Id is :- " + totalProduct;
	}

	@PutMapping("cart/addProduct/{customerId}/{productId}/{quantity}")
	public String addProductInCartByProductId(@PathVariable("customerId") Integer customerId,
			@PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity)
			throws ProductException, CustomerException {
		this.cartService.addProductTocart(customerId, productId, quantity);
		return "Product added in cart Successfully";
	}
}
