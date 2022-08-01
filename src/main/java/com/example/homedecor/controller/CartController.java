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

import com.example.homedecor.dto.Cart;
import com.example.homedecor.dto.Product;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.service.CartService;
import com.example.homedecor.service.CartServiceImpl;

@RestController
public class CartController {

	@Autowired
	private CartServiceImpl cartService;
	
	@PostMapping("cart")
	public Boolean addCart(@RequestBody Cart cart) throws CartException{
		this.cartService.addCart(cart);
		return true;
	}
	
	
	@GetMapping("cart/{cartId}")
	public Optional<Cart> getCartById(@PathVariable ("cartId") Integer cartId) throws CartException {
		return this.cartService.getCartById(cartId);
	}
	
	
	@PatchMapping("cart")
	public Cart updateCart(@RequestBody Cart cart) throws CartException {
		return this.cartService.updateCart(cart);
	}
	
	@GetMapping("cart/")
	public List<Cart> getAllCarts() throws CartException {
		return this.cartService.getAllCart();
	}
	
	@DeleteMapping("cart/{cartId}")
	public Boolean deleteCartById(@PathVariable ("cartId") Integer cartId) throws CartException {
		this.cartService.deleteCartById(cartId);
		return true;
	}
	
	


}
