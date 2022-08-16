package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("order")
	public Boolean addOrder(@RequestBody OrderByCustomer orderByCustomer) throws OrderException {
			this.orderService.addOrder(orderByCustomer);
		
		return true;
	}
	
	
	@GetMapping("order/{orderId}")
	public Optional<OrderByCustomer> getOrderById(@PathVariable ("orderId") Integer orderId) throws OrderException  {
		Optional<OrderByCustomer> foundOrder = this.orderService.getOrderById(orderId);
		 return foundOrder;
	}
	
	
	@PatchMapping("order")
	public OrderByCustomer updateOrder(@RequestBody OrderByCustomer order) throws OrderException {
		OrderByCustomer foundOrder = this.orderService.updateOrder(order);
		 return foundOrder;
	}
	
	@GetMapping("orders")
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> foundOrders = this.orderService.getAllOrders();
		 return foundOrders;
		
	}
	
	@DeleteMapping("order/{orderId}")
	public Boolean deleteOrderById(@PathVariable("orderId") Integer orderId) throws OrderException {
			this.orderService.deleteOrderById(orderId);
		return true;
	}
	
	@PatchMapping("order/placeOrder/{customerId}/{orderId}/{paymentId}")
	public String placeOrder(@PathVariable("customerId") Integer customerId,@PathVariable("orderId") Integer orderId,
			@PathVariable("paymentId") Integer paymentId)
			throws OrderException, PaymentException, CartException, CustomerException {
				this.orderService.placeOrder(customerId,orderId, paymentId);
		return "Order Placed Successfully";
	}

}
