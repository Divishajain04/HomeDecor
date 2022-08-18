package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.service.OrderService;

@SpringBootTest
 class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	void deleteOrderByIdTest() throws OrderException {
		OrderByCustomer getOrder = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(getOrder));
		assertEquals(true, this.orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()->this.orderService.deleteOrderById(8));
	}
	
	@Test
	void addOrderTest() throws OrderException {
		assertThrows(OrderException.class,()->this.orderService.addOrder(null));
		OrderByCustomer getOrder = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(getOrder));
		assertNotNull(orderService);
		assertThrows(OrderException.class,()->this.orderService.addOrder(getOrder));
		assertEquals(true, orderService.deleteOrderById(8));
			
	}
	
	@Test
	void getOrderByIdTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		OrderByCustomer getOrder = orderService.getOrderById(8).get();
		Integer orderId = getOrder.getOrderId();
		assertEquals(8,orderId);
		assertNotNull(orderService.getOrderById(orderId));
		assertEquals(true, orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()->this.orderService.getOrderById(8));
		
	}
	
	@Test
	void getAllOrdersTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		assertNotNull(this.orderService.getAllOrders());
		assertEquals(true, this.orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()-> this.orderService.getAllOrders());
	}
	
	@Test
	void updateOrderTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		assertNotNull(this.orderService.updateOrder(new OrderByCustomer(8, "Done", null,null)));
		assertEquals(true, this.orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()-> this.orderService.updateOrder(new OrderByCustomer(8, "Successfull", null,null)));
		
	}

}
