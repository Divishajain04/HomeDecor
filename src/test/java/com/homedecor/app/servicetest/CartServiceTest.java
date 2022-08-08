package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.service.CartService;

@SpringBootTest
class CartServiceTest {

	@Autowired
	CartService cartService;

	@Test
	void deleteCartByIdTest() throws CartException {
		Cart cart = new Cart(14, null, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertEquals(true, this.cartService.deleteCartById(14));
		assertThrows(CartException.class, () -> this.cartService.deleteCartById(14));
	}

	@Test
	void addCartTest() throws CartException {
		assertThrows(CartException.class, () -> this.cartService.addCart(null));
		Cart cart = new Cart(14, null, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.getCartById(14));
		assertThrows(CartException.class, () -> this.cartService.addCart(cart));
		assertEquals(true, this.cartService.deleteCartById(14));

	}

	@Test
	void getCartByIdTest() throws CartException {
		Cart cart = new Cart(14, null, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.getCartById(14));
		assertEquals(true, this.cartService.deleteCartById(14));
		assertThrows(CartException.class, () -> this.cartService.getCartById(14));
	}

	@Test
	void getAllCartTest() throws CartException {
		Cart cart = new Cart(14, null, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.getAllCarts());
		assertEquals(true, this.cartService.deleteCartById(14));
	//	assertThrows(CartException.class, () -> this.cartService.getAllCarts());
	}

	@Test
	void updateCartTest() throws CartException {
		Cart cart = new Cart(14, null, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.updateCart(new Cart(14, null, null, null)));
		assertEquals(true, this.cartService.deleteCartById(14));
		assertThrows(CartException.class, () -> this.cartService.updateCart(new Cart(14, null, null, null)));
	}

}
