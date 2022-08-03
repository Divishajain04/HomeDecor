package com.homedecor.app.servicetest;


import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Admin;
import com.homedecor.app.dto.Payment;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.exception.WishlistException;
import com.homedecor.app.service.PaymentService;
import com.homedecor.app.service.WishlistService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
 class PaymentServiceTest {
	@Autowired
	private PaymentService paymentService;
	
	@Test
	void deletePaymentById() throws PaymentException{
		Payment payment = new Payment(2,"cod", 20.0,"done");
		assertTrue(this.paymentService.addPayment(payment));
		assertEquals(true, this.paymentService.deletePaymentById(2));
		assertThrows(PaymentException.class, ()-> this.paymentService.deletePaymentById(2));
	}
	
	@Test
	void addPaymentTest() throws PaymentException{
		assertThrows(PaymentException.class, ()-> this.paymentService.addPayment(null));
		Payment payment = new Payment(2,"cod", 20.0,"done");
		assertTrue(this.paymentService.addPayment(payment));
		assertThrows(PaymentException.class, ()-> this.paymentService.addPayment(payment));
		assertEquals(true, this.paymentService.deletePaymentById(2));
	}
	
	@Test
	void getPaymentByIdTest() throws PaymentException{
		Payment payment = new Payment(2,"cod", 20.0,"done");
		assertTrue(this.paymentService.addPayment(payment));
		assertEquals(2, this.paymentService.getPaymentById(2).get().getPaymentId());
		assertEquals(true, this.paymentService.deletePaymentById(2));
		assertThrows(PaymentException.class, ()-> this.paymentService.getPaymentById(2));
	}
	
	@Test
	void getAllPaymentTest() throws PaymentException{
		Payment payment = new Payment(2,"cod", 20.0,"done");
		assertTrue(this.paymentService.addPayment(payment));
		assertNotNull(this.paymentService.getAllPayments());
	
		assertEquals(true, this.paymentService.deletePaymentById(2));
		assertThrows(PaymentException.class, ()-> this.paymentService.getAllPayments());
	}
	
	@Test
	void updatePaymentTest() throws PaymentException{
		Payment payment = new Payment(2,"cod", 20.0,"done");
		assertTrue(this.paymentService.addPayment(payment));
		assertNotNull(this.paymentService.updatePayment(payment));
		assertEquals(true, this.paymentService.deletePaymentById(2));
		assertThrows(PaymentException.class, ()-> this.paymentService.updatePayment(payment));
	}
	

}
