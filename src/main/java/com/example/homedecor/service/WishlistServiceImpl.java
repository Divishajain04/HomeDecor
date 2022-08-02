package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.WishlistRepository;
import com.example.homedecor.dto.Product;
import com.example.homedecor.dto.Wishlist;
import com.example.homedecor.exception.WishlistException;

@Service
public class WishlistServiceImpl implements WishlistService{

	@Autowired
	private WishlistRepository wishlistRepository;
	
	
	@Override
	public Boolean addWishlist(Wishlist wishlist) throws WishlistException {
		if (wishlist == null) {
			throw new WishlistException("Wishlist is not added");
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
	public List<Wishlist> getAllWishlist() throws WishlistException {
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
			throw new WishlistException("Id not found");
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
