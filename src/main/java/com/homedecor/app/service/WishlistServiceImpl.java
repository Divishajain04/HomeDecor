package com.homedecor.app.service;



import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.WishlistRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.exception.WishlistException;

@Service
public class WishlistServiceImpl implements WishlistService{

	@Autowired
	private WishlistRepository wishlistRepository;
		
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public Boolean addWishlist(Wishlist wishlist) throws WishlistException {
		if (wishlist == null) {
			throw new WishlistException("Wishlist is not added!Please fill the mandatory field.");
		}
		Optional<Wishlist> addNewWishlist = this.wishlistRepository.findById(wishlist.getWishlistId());
		if (addNewWishlist.isPresent()) {
			throw new WishlistException("Wishlist Id is already present");
		}else {
			this.wishlistRepository.save(wishlist);	
		}
		return true;
	}

	@Override
	public Optional<Wishlist> getWishlistById(Integer wishlistId) throws WishlistException {
		Optional<Wishlist> findWishlist = this.wishlistRepository.findById(wishlistId);
		if (findWishlist.isEmpty()) {
			throw new WishlistException("This wishlist Id is not present in the record");
		}
		return findWishlist;
	}

	@Override
	public List<Wishlist> getAllWishlists() throws WishlistException {
		List<Wishlist> getList = this.wishlistRepository.findAll();
		if (getList.isEmpty()) {
			throw new WishlistException("Wishlist is Empty");
		}
		return getList;
	}

	@Override
	public Boolean deleteWishlistById(Integer wishlistId) throws WishlistException {
		Optional<Wishlist> findWishlist = this.wishlistRepository.findById(wishlistId);
		if(findWishlist.isEmpty()) {
			throw new WishlistException("Wishlist Id is not present in the record.");
		}
		else {
			this.wishlistRepository.deleteById(wishlistId);
		}
		return true;
	}

	@Override
	public Wishlist updateWishlist(Wishlist wishlist) throws WishlistException {
		Optional<Wishlist> findWishlist = this.wishlistRepository.findById(wishlist.getWishlistId());
		if(findWishlist.isEmpty()) {
			throw new WishlistException("Wishlist Id doesn't exist");
		}else {
			this.wishlistRepository.save(wishlist);
		}
		return wishlist;
	}

	@Override    
	public Boolean addWishlistProductTocart(Integer customerId)
			throws  CustomerException, ProductException {
		
		Optional<Customer> foundCustomer = this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) throw new CustomerException("Invalid Customer Id");
		
		Customer getCustomer = foundCustomer.get();
		
		Wishlist getWishlist = getCustomer.getWishlist();
		
		List<Product> getProducts =	getWishlist.getProduct();
		
		Cart getCart =	getCustomer.getCart();	
		getCart.setProduct(getProducts);
		
		getWishlist.setProduct(null);
		
		this.wishlistRepository.save(getWishlist);
		
		this.cartRepository.save(getCart);
		
		this.customerRepository.save(getCustomer);
		
			
		return true;
	}

}
