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

		if (cart == null)
			throw new CartException("cart not added");
		Optional<Cart> foundCart = this.cartRepository.findById(cart.getCartId());
		if (foundCart.isPresent()) {
			throw new CartException("cart already exist");
		} else {
			this.cartRepository.save(cart);
		}
		return true;
	}

	@Override
	public Cart updateCart(Cart cart) throws CartException {
		if (cart == null)
			throw new CartException("cart not updated please fill the mandatory feilds");
		Optional<Cart> foundCart = this.cartRepository.findById(cart.getCartId());
		if (foundCart.isEmpty())
			throw new CartException("Cart not avilable for this Id");
		return this.cartRepository.save(cart);
	}

	@Override
	public Optional<Cart> getCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty())
			throw new CartException("Cart not avilable for this Id " + cartId);
		return foundCart;
	}

	@Override
	public List<Cart> getAllCart() throws CartException {
		List<Cart> foundCart = this.cartRepository.findAll();
		if (foundCart.isEmpty())
			throw new CartException("Cart is null");
		return this.cartRepository.findAll();
	}

	@Override
	public Boolean deleteCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty())
			throw new CartException("Cart not avilable for this Id " + cartId);
		this.cartRepository.deleteById(cartId);
		return true;
	}

}
