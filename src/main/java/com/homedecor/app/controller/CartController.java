package com.homedecor.app.controller;

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

import com.homedecor.app.dto.Cart;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.service.CartServiceImpl;

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
		Optional<Cart> foundCart;
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
			foundAllCart=this.cartService.getAllCarts();
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
	
	@GetMapping("cart/customer/totalAmount/{cartId}")
	public String getTotalAmountOfCartById(@PathVariable ("cartId") Integer cartId) throws CartException {
		Optional<Double> totalBalance;
		try {
			totalBalance=this.cartService.totalAmountOfCustomerCartById(cartId);
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		return "Total amount of "+cartId+" Id is :- "+totalBalance;
	}
	@GetMapping("cart/customer/totalProduct/{cartId}")
	public String countAllProductInCartById(@PathVariable ("cartId") Integer cartId) throws CartException {
		Long totalProduct;
		try {
			totalProduct=this.cartService.totalProductInCustomerCartById(cartId);
		} catch (CartException e) {
			throw new CartException(e.getMessage());
		}
		return "Total amount of "+cartId+" Id is :- "+totalProduct;
	}
	


}
