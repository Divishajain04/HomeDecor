package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import com.example.homedecor.dto.Cart;
import com.example.homedecor.exception.CartException;

public interface CartService {

	public Boolean addCart(Cart cart)throws CartException; 
	
	public Optional<Cart> getCartById(Integer cartId)throws CartException ;
	
	public List<Cart> getAllCarts()throws CartException;
	
	public Boolean deleteCartById(Integer cartId)throws CartException;
	
	public Cart updateCart(Cart cart)throws CartException;

}
