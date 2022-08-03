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
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.service.OrderServiceImpl;

@RestController
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("order")
	public Boolean addOrder(@RequestBody OrderByCustomer orderByCustomer) throws OrderException {
		try {
			this.orderServiceImpl.addOrder(orderByCustomer);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		return true;
	}
	
	
	@GetMapping("order/{orderId}")
	public Optional<OrderByCustomer> getOrderById(@PathVariable ("orderId") Integer orderId) throws OrderException  {
		Optional<OrderByCustomer> foundOrder;
		try {
			foundOrder= this.orderServiceImpl.getOrderById(orderId);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		 return foundOrder;
	}
	
	
	@PatchMapping("order")
	public OrderByCustomer updateOrder(@RequestBody OrderByCustomer order) throws OrderException {
		OrderByCustomer foundOrder=null;
		 try {
			foundOrder= this.orderServiceImpl.updateOrder(order);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		 return foundOrder;
	}
	
	@GetMapping("order/")
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> foundOrder=null;
		 try {
			foundOrder= this.orderServiceImpl.getAllOrders();
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		 return foundOrder;
		
	}
	
	@DeleteMapping("order/{orderId}")
	public Boolean deleteOrderById(@PathVariable("orderId") Integer orderId) throws OrderException {
		try {
			this.orderServiceImpl.deleteOrderById(orderId);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		return true;
	}

}
