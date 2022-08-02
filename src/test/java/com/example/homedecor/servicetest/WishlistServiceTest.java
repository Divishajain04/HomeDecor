package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Wishlist;
import com.example.homedecor.exception.WishlistException;
import com.example.homedecor.service.WishlistService;

@SpringBootTest
public class WishlistServiceTest {
	
	@Autowired
	private WishlistService wishlistService;
	
	@AfterEach
	void deleteWishlistById() throws WishlistException{
		assertEquals(true, this.wishlistService.deleteWishlistById(1));
	}
	
	@Test
	void addWishlistTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
	}
	
	@Test
	void getWishlistByIdTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
//		assertNotNull(this.wishlistService.getWishlistById(1));
		assertEquals(1, this.wishlistService.getWishlistById(1).get().getWishlistId());
		assertEquals(1, this.wishlistService.getWishlistById(1).get().getWishlistId());
	}
	


}
