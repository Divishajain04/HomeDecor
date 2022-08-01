package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.CartRepository;
import com.example.homedecor.dto.Cart;
import com.example.homedecor.dto.Category;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.exception.CategoryException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public Boolean addCart(Cart cart) throws CartException {
		if (cart == null) {
			throw new CartException("Cart not added");
		}
		Optional<Cart> addCartResult = this.cartRepository.findById(cart.getCartId());
		if (addCartResult.isPresent()) {
			throw new CartException("Cart Id is already present in the record");
		} else {
			this.cartRepository.save(cart);
		}
		return true;
	}

	@Override
	public Optional<Cart> getCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty()) {
			throw new CartException("Invalid Cart Id");
		}
		return foundCart;
	}

	@Override
	public List<Cart> getAllCart() throws CartException {
		List<Cart> cartList = this.cartRepository.findAll();
		if (cartList.isEmpty()) {
			throw new CartException("Cart is empty");
		}
		return cartList;
	}

	@Override
	public Boolean deleteCartById(Integer cartId) throws CartException {
		this.cartRepository.deleteById(cartId);
		return true;
	}

	@Override
	public Cart updateCart(Cart cart) throws CartException {
		return this.cartRepository.save(cart);
	}

}
