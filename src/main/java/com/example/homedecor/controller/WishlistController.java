package com.example.homedecor.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
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
import com.example.homedecor.exception.OrderException;
import com.example.homedecor.exception.WishlistException;
import com.example.homedecor.service.WishlistServiceImpl;

@RestController
public class WishlistController {

	@Autowired
	WishlistServiceImpl wishlistServiceImpl;
	
	
	@PostMapping("wishlist")
	public Boolean addProductInWishlist(@RequestBody Wishlist wishlist) throws WishlistException {
		try {
			this.wishlistServiceImpl.addWishlist(wishlist);
		} catch (WishlistException e) {
			throw new WishlistException(e.getMessage());
		}
		return true;
	}
	
	
	@GetMapping("wishlist/{wishlistId}")
	public Optional<Wishlist> getWishlistById(@PathVariable ("wishlistId") Integer wishlistId) throws WishlistException {
		Optional<Wishlist> foundWishlist=null;
		try {
			foundWishlist=this.wishlistServiceImpl.getWishlistById(wishlistId);
		} catch (WishlistException e) {
			throw new WishlistException(e.getMessage());
		}
		return foundWishlist;
	}
	
	
	@PatchMapping("wishlist")
	public Wishlist updateWishlist(@RequestBody Wishlist wishlist) throws WishlistException {
		 Wishlist foundWishlist=null;
		try {
			this.wishlistServiceImpl.updateWishlist(wishlist);
		} catch (WishlistException e) {
			throw new WishlistException(e.getMessage());
		}
		return foundWishlist;
	}
	
	@GetMapping("wishlist/")
	public List<Wishlist> getAllWishlist() throws WishlistException {
		List<Wishlist> foundAllWishlist ;
		try {
			foundAllWishlist=this.wishlistServiceImpl.getAllWishlist();
		} catch (WishlistException e) {
			throw new WishlistException(e.getMessage());
		}
		return foundAllWishlist;
	}
	
	@DeleteMapping("wishlist/{wishlistId}")
	public Boolean deleteWishlistById(@PathVariable ("wishlistId") Integer wishlistId) throws WishlistException {
		try {
			this.wishlistServiceImpl.deleteWishlistById(wishlistId);
		} catch (WishlistException e) {
			throw new WishlistException(e.getMessage());
		}
		return true;
	}	
}
