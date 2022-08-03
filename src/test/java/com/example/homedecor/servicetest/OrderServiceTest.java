package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.OrderByCustomer;
import com.example.homedecor.exception.OrderException;
import com.example.homedecor.service.OrderService;

@SpringBootTest
 class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@AfterEach
	void deleteOrderByIdTest() throws OrderException {
		assertEquals(true, this.orderService.deleteOrderById(8));
	}
	
	@Test
	void addOrderTest() throws OrderException {
		OrderByCustomer getOrder = new OrderByCustomer(8, "Pending", null);
		assertTrue(orderService.addOrder(getOrder));
		assertNotNull(orderService!=null);
		
	}
	
	@Test
	void getOrderByIdTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null);
		assertTrue(orderService.addOrder(order));
		OrderByCustomer getOrder = orderService.getOrderById(8).get();
		Integer orderId = getOrder.getOrderId();
		assertEquals(8,orderId);
		assertNotNull(orderService.getOrderById(orderId));
		
	}

}
