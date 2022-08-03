package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.exception.OrderException;

public interface OrderService {

	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException;

	public Optional<OrderByCustomer> getOrderById(Integer orderId) throws OrderException;

	public List<OrderByCustomer> getAllOrders() throws OrderException;

	public Boolean deleteOrderById(Integer orderId) throws OrderException;

	public OrderByCustomer updateOrder(OrderByCustomer orderByCustomer) throws OrderException;
}
