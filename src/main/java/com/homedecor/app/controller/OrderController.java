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
		try {
			this.orderService.addOrder(orderByCustomer);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		return true;
	}
	
	
	@GetMapping("order/{orderId}")
	public Optional<OrderByCustomer> getOrderById(@PathVariable ("orderId") Integer orderId) throws OrderException  {
		Optional<OrderByCustomer> foundOrder;
		try {
			foundOrder= this.orderService.getOrderById(orderId);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		 return foundOrder;
	}
	
	
	@PatchMapping("order")
	public OrderByCustomer updateOrder(@RequestBody OrderByCustomer order) throws OrderException {
		OrderByCustomer foundOrder;
		 try {
			foundOrder= this.orderService.updateOrder(order);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		 return foundOrder;
	}
	
	@GetMapping("orders")
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> foundOrders;
		 try {
			foundOrders= this.orderService.getAllOrders();
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		 return foundOrders;
		
	}
	
	@DeleteMapping("order/{orderId}")
	public Boolean deleteOrderById(@PathVariable("orderId") Integer orderId) throws OrderException {
		try {
			this.orderService.deleteOrderById(orderId);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		return true;
	}
	
	@PatchMapping("order/placeOrder/{customerId}/{orderId}/{paymentId}")
	public String placeOrder(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId,
			@PathVariable("paymentId") Integer paymentId)
			throws OrderException, PaymentException, CartException, CustomerException {
		try {
			try {
				this.orderService.placeOrderStatus(customerId, orderId, paymentId);
			} catch (PaymentException e) {
				throw new PaymentException(e.getMessage());
			} catch (CartException e) {
				throw new CartException(e.getMessage());
			} catch (CustomerException e) {
				throw new CustomerException(e.getMessage());
			}
		} catch (OrderException e) {
			throw new OrderException(e.getMessage());
		}
		return "Order Placed Successfully";
	}

}
