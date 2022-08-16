package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.WishlistException;

public interface WishlistService {

	public Boolean addWishlist(Wishlist wishlist)throws WishlistException; 
	
	public Optional<Wishlist> getWishlistById(Integer wishlistId)throws WishlistException;
	
	public List<Wishlist> getAllWishlists() throws WishlistException;
	
	public Boolean deleteWishlistById(Integer wishlistId)throws WishlistException;
	
	public Wishlist updateWishlist(Wishlist wishlist)throws WishlistException;

	public Boolean addWishlistProductToCart(Integer customerId) throws CustomerException, WishlistException, CartException;

}
