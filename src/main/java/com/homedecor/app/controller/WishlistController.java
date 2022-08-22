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

import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.WishlistException;
import com.homedecor.app.service.WishlistServiceImpl;

@RestController
public class WishlistController {

	@Autowired
	WishlistServiceImpl wishlistServiceImpl;

	@PostMapping("wishlist")
	public Boolean addProductInWishlist(@RequestBody Wishlist wishlist) throws WishlistException {
		this.wishlistServiceImpl.addWishlist(wishlist);
		return true;
	}

	@GetMapping("wishlist/{wishlistId}")
	public Optional<Wishlist> getWishlistById(@PathVariable("wishlistId") Integer wishlistId) throws WishlistException {
		return this.wishlistServiceImpl.getWishlistById(wishlistId);
	}

	@PatchMapping("wishlist")
	public String updateWishlist(@RequestBody Wishlist wishlist) throws WishlistException {

		this.wishlistServiceImpl.updateWishlist(wishlist);

		return "Wishlist Updated Successfully";
	}

	@GetMapping("wishlist/")
	public List<Wishlist> getAllWishlist() throws WishlistException {
		
		return this.wishlistServiceImpl.getAllWishlists();
	}

	@DeleteMapping("wishlist/{wishlistId}")
	public Boolean deleteWishlistById(@PathVariable("wishlistId") Integer wishlistId) throws WishlistException {
		this.wishlistServiceImpl.deleteWishlistById(wishlistId);
		return true;
	}

	@PatchMapping("wishlist/addProductToCart/{customerId}")
	public Boolean addProductToCart(@PathVariable("customerId") Integer customerId)
			throws CustomerException, WishlistException, CartException {
		this.wishlistServiceImpl.addWishlistProductToCart(customerId);
		return true;
	}

}
