package com.example.homedecor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.homedecor.dto.Cart;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.service.CartService;

@DataJpaTest
public class CartTest {
	
	@Autowired 
	CartService cartService;
	
	
	@Test
	void testCreateCart() throws CartException{
		Boolean actualBoolean = cartService.addCart(new Cart());
	}
	

}
