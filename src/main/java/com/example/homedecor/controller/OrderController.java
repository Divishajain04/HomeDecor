package com.example.homedecor.controller;

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

import com.example.homedecor.dto.Cart;
import com.example.homedecor.dto.OrderByCustomer;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.exception.OrderException;
import com.example.homedecor.service.OrderServiceImpl;

@RestController
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("order")
	public Boolean addOrder(@RequestBody OrderByCustomer orderByCustomer) throws OrderException{
		this.orderServiceImpl.addOrder(orderByCustomer);
		return true;
	}
	
	
	@GetMapping("order/{orderId}")
	public Optional<OrderByCustomer> getOrderById(@PathVariable ("orderId") Integer orderId) throws OrderException {
		return this.orderServiceImpl.getOrderById(orderId);
	}
	
	
	@PatchMapping("order")
	public OrderByCustomer updateOrder(@RequestBody OrderByCustomer order) throws OrderException {
		return this.orderServiceImpl.updateOrder(order);
	}
	
	@GetMapping("order/")
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		return this.orderServiceImpl.getAllOrder();
	}
	
	@DeleteMapping("order/{orderId}")
	public Boolean deleteOrderById(@PathVariable ("orderId") Integer orderId) throws OrderException {
		this.orderServiceImpl.deleteOrderById(orderId);
		return true;
	}

}
