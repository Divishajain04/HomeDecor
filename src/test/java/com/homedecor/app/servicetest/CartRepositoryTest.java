package com.homedecor.app.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dto.Cart;

@SpringBootTest
 class CartRepositoryTest {

	@Autowired
	private CartRepository cartRepository;
	
	@Test
	void addCartTest() {
		Cart cart = new Cart(111, null, null);
		this.cartRepository.save(cart);
		Boolean a=this.cartRepository.existsById(111);
		assertThat(a).isTrue();
		this.cartRepository.deleteById(111);
		Boolean b=this.cartRepository.existsById(111);
		assertThat(b).isFalse();
		
	}
}
