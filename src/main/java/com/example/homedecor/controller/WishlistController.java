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
import com.example.homedecor.dto.Wishlist;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.exception.WishlistException;
import com.example.homedecor.service.WishlistServiceImpl;

@RestController
public class WishlistController {

	@Autowired
	WishlistServiceImpl wishlistServiceImpl;
	
	
	@PostMapping("wishlist")
	public Boolean addProductInWishlist(@RequestBody Wishlist wishlist) throws WishlistException{
		this.wishlistServiceImpl.addWishlist(wishlist);
		return true;
	}
	
	
	@GetMapping("wishlist/{wishlistId}")
	public Optional<Wishlist> getWishlistById(@PathVariable ("wishlistId") Integer wishlistId) throws WishlistException {
		return this.wishlistServiceImpl.getWishlistById(wishlistId);
	}
	
	
	@PatchMapping("wishlist")
	public Wishlist updateWishlist(@RequestBody Wishlist wishlist) throws WishlistException {
		return this.wishlistServiceImpl.updateWishlist(wishlist);
	}
	
	@GetMapping("wishlist/")
	public List<Wishlist> getAllWishlist() throws WishlistException {
		return this.wishlistServiceImpl.getAllWishlist();
	}
	
	@DeleteMapping("wishlist/{wishlistId}")
	public Boolean deleteWishlistById(@PathVariable ("wishlistId") Integer wishlistId) throws WishlistException {
		this.wishlistServiceImpl.deleteWishlistById(wishlistId);
		return true;
	}	
}
