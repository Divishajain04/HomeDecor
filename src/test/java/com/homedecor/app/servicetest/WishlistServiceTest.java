package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Category;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.exception.WishlistException;
import com.homedecor.app.service.CartService;
import com.homedecor.app.service.CategoryService;
import com.homedecor.app.service.CustomerService;
import com.homedecor.app.service.ProductService;
import com.homedecor.app.service.WishlistService;

@SpringBootTest
 class WishlistServiceTest {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	void deleteWishlistById() throws WishlistException{
		Wishlist wishlist = new Wishlist(111, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertEquals(true, this.wishlistService.deleteWishlistById(111));
		assertThrows(WishlistException.class, ()-> this.wishlistService.deleteWishlistById(111));
	}
	
	@Test
	void addWishlistTest() throws WishlistException{
		assertThrows(WishlistException.class, ()-> this.wishlistService.addWishlist(null));
		Wishlist wishlist = new Wishlist(111, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertThrows(WishlistException.class, ()-> this.wishlistService.addWishlist(wishlist));
		assertEquals(true, this.wishlistService.deleteWishlistById(111));
	}
	
	@Test
	void getWishlistByIdTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(111, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertEquals(111, this.wishlistService.getWishlistById(111).get().getWishlistId());
		assertEquals(true, this.wishlistService.deleteWishlistById(111));
		assertThrows(WishlistException.class,()-> this.wishlistService.getWishlistById(111));
	}
	
	@Test
	void getAllWishlistTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(111, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertNotNull(this.wishlistService.getAllWishlists());
		assertEquals(true, this.wishlistService.deleteWishlistById(111));
//		assertThrows(WishlistException.class,()-> this.wishlistService.getAllWishlists());
	}
	
	@Test
	void updateWishlistTest() throws WishlistException{
		Wishlist wishlist = new Wishlist(111, null);
		assertTrue(this.wishlistService.addWishlist(wishlist));
		assertNotNull(this.wishlistService.updateWishlist(wishlist));
		assertEquals(true, this.wishlistService.deleteWishlistById(111));
		assertThrows(WishlistException.class,()->this.wishlistService.updateWishlist(wishlist));
	}
	
	@Test
	void addWishlistProductToCartTest() throws WishlistException, CustomerException, CartException, ProductException, CategoryException{
		Customer customer = new Customer(111, "prince", "badluckLucky@gmail.com", "princess123", "9999966666", "xyz", null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Category category = new Category(111, "JD", null);
		assertTrue(this.categoryService.addCategory(category));
		List<Product> products=new ArrayList<>();
		Product product = new Product(111, "cinnamon spice", "tooooo good", 10000000.00, 2, 111);
		assertTrue(this.productService.addProducts(product));
		products.add(product);
		this.wishlistService.updateWishlist(new Wishlist(111,products));
		assertTrue(this.wishlistService.addWishlistProductToCart(111));
		assertEquals(true, this.customerService.deleteCustomer(111));
		assertEquals(true, this.categoryService.deleteCategoryById(111));
		
	}
	
}
