package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import com.example.homedecor.dto.Product;
import com.example.homedecor.dto.Wishlist;
import com.example.homedecor.exception.WishlistException;

public interface WishlistService {

	public Boolean addWishlist(Wishlist wishlist)throws WishlistException; 
	
	public Optional<Wishlist> getWishlistById(Integer wishlistId)throws WishlistException;
	
	public List<Wishlist> getAllWishlist() throws WishlistException;
	
	public Boolean deleteWishlistById(Integer wishlistId)throws WishlistException;
	
	public Wishlist updateWishlist(Wishlist wishlist)throws WishlistException;


}
