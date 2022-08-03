package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.homedecor.app.dao.WishlistRepository;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.WishlistException;

@Service
public class WishlistServiceImpl implements WishlistService{

	@Autowired
	private WishlistRepository wishlistRepository;
	
	
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

}
