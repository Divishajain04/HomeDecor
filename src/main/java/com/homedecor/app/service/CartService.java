package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;

public interface CartService {

	public Boolean addCart(Cart cart)throws CartException; 
	
	public Optional<Cart> getCartById(Integer cartId)throws CartException ;
	
	public List<Cart> getAllCarts()throws CartException;
	
	public Boolean deleteCartById(Integer cartId)throws CartException;
	
	public Cart updateCart(Cart cart)throws CartException;
	
	public Optional<Double> totalAmountOfCustomerCartById(Integer cartId)throws CartException;
	
	public Long totalProductInCustomerCartById(Integer cartId)throws CartException;
	
	public Boolean addProductToCart(Integer customerId, Integer productId, Integer quantity) throws ProductException, CustomerException, CartException;

	public Boolean removeProductFromCart(Integer customerId, Integer productId, Integer quantity) throws ProductException, CustomerException, CartException;
}
