package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.WishlistException;
import com.homedecor.app.service.WishlistService;

@SpringBootTest
 class WishlistServiceTest {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Test
	void deleteWishlistById() throws WishlistException{
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertEquals(true, this.wishlistService.deleteWishlistById(1));
		assertThrows(WishlistException.class, ()-> this.wishlistService.deleteWishlistById(1));
	}
	
	@Test
	void addWishlistTest() throws WishlistException{
		assertThrows(WishlistException.class, ()-> this.wishlistService.addWishlist(null));
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertThrows(WishlistException.class, ()-> this.wishlistService.addWishlist(wishlist));
		assertEquals(true, this.wishlistService.deleteWishlistById(1));
	}
	
	@Test
	void getWishlistByIdTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertEquals(1, this.wishlistService.getWishlistById(1).get().getWishlistId());
		assertEquals(true, this.wishlistService.deleteWishlistById(1));
		assertThrows(WishlistException.class,()-> this.wishlistService.getWishlistById(1));
	}
	
	@Test
	void getAllWishlistTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertNotNull(this.wishlistService.getAllWishlists());
		assertEquals(true, this.wishlistService.deleteWishlistById(1));
		assertThrows(WishlistException.class,()-> this.wishlistService.getAllWishlists());
	}
	
	@Test
	void updateWishlistTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(1, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertNotNull(this.wishlistService.updateWishlist(wishlist));
		assertEquals(true, this.wishlistService.deleteWishlistById(1));
		assertThrows(WishlistException.class,()->this.wishlistService.updateWishlist(wishlist));
	}
	
	
}
