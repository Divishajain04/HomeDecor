package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.OrderRepository;
import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException {
		if (orderByCustomer == null) {
			throw new OrderException("Order not added! Please fill the mandatory field");
		}
		Optional<OrderByCustomer> addOrderResult = this.orderRepository.findById(orderByCustomer.getOrderId());
		if (addOrderResult.isPresent()) {
			throw new OrderException("Order Id is already present in the record");
		} else {
			this.orderRepository.save(orderByCustomer);
		}
		return true;
	}

	@Override
	public Optional<OrderByCustomer> getOrderById(Integer orderId) throws OrderException {
		Optional<OrderByCustomer> foundOrder = this.orderRepository.findById(orderId);
		if (foundOrder.isEmpty()) {
			throw new OrderException("Invalid Order Id");
		}
		return foundOrder;
	}

	@Override
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> orderList = this.orderRepository.findAll();
		if (orderList.isEmpty()) {
			throw new OrderException("Order List is empty");
		}
		return orderList;
	}

	@Override
	public Boolean deleteOrderById(Integer orderId) throws OrderException {
		Optional<OrderByCustomer> foundOrder = this.orderRepository.findById(orderId);
		if (foundOrder.isEmpty()) {
			throw new OrderException("Order not exist for this id");
		}
		this.orderRepository.deleteById(orderId);
		return true;
	}

	@Override
	public OrderByCustomer updateOrder(OrderByCustomer orderByCustomer) throws OrderException {
		Optional<OrderByCustomer> foundOrder = this.orderRepository.findById(orderByCustomer.getOrderId());
		if (foundOrder.isEmpty()) {
			throw new OrderException("Order not exist for this id");
		}
		return 	this.orderRepository.save(orderByCustomer);	
	}
}
