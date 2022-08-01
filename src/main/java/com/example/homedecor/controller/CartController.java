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
	public String addCart(@RequestBody Cart cart) throws CartException  {
		try {
			this.cartService.addCart(cart);
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		return "Cart added Successfully";
	}
	
	@GetMapping("cart/{cartId}")
	public Optional<Cart> getCartById(@PathVariable ("cartId") Integer cartId) throws CartException  {
		Optional<Cart> foundCart=null;
		 try {
			foundCart=this.cartService.getCartById(cartId);
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		 return foundCart;
	}
	
	@PatchMapping("cart")
	public Cart updateCart(@RequestBody Cart cart) throws CartException {
		Cart cartUpdated=null;
		 try {
			cartUpdated=this.cartService.updateCart(cart);
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		 return cartUpdated;
	}
	
	@GetMapping("cart/")
	public List<Cart> getAllCart() throws CartException {
		List<Cart> foundAllCart=null;
		try {
			foundAllCart=this.cartService.getAllCart();
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		return foundAllCart;
	}
	
	@DeleteMapping("cart/{cartId}")
	public String deleteCartById(@PathVariable ("cartId") Integer cartId) throws CartException  {
		try {
			this.cartService.deleteCartById(cartId);
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		return "Cart deleted successfully";
	}
	
	


}
