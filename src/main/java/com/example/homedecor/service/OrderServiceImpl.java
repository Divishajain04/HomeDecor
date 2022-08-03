package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.OrderRepository;
import com.example.homedecor.dto.OrderByCustomer;
import com.example.homedecor.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException {
		if (orderByCustomer == null) {
			throw new OrderException("Order not added");
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
	public List<OrderByCustomer> getAllOrder() throws OrderException {
		List<OrderByCustomer> orderList = this.orderRepository.findAll();
		if (orderList.isEmpty()) {
			throw new OrderException("Order List is empty");
		}
		return orderList;
	}

	@Override
	public Boolean deleteOrderById(Integer orderId) throws OrderException {
		this.orderRepository.deleteById(orderId);
		return true;
	}

	@Override
	public OrderByCustomer updateOrder(OrderByCustomer orderByCustomer) throws OrderException {
		return this.orderRepository.save(orderByCustomer);
	}

}
