package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Cart;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.service.CartService;


@SpringBootTest
 class CartServiceTest {

	
	@Autowired 
	CartService cartService;

	@AfterEach
	void deleteCartById() throws CartException{
		assertEquals(true,cartService.deleteCartById(14));
	}

	
	@Test
	void addCartTest() throws CartException{
		Cart cart = new Cart(14,null,null,null);
		assertTrue(cartService.addCart(cart));
		assertNotNull(cart);	
	}
		
	@Test
	void getCardByIdTest() throws CartException{
		Cart cart = new Cart(14,null,null,null);
		assertTrue(cartService.addCart(cart));
		Cart cart2 = cartService.getCartById(14).get();
		Integer idInteger = cart2.getCartId();
		assertEquals(14,idInteger);
	}
		
}
