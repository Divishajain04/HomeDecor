package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;


import com.example.homedecor.dto.OrderByCustomer;

import com.example.homedecor.exception.OrderException;

public interface OrderService {

	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException;

	public Optional<OrderByCustomer> getOrderById(Integer orderId) throws OrderException;

	public List<OrderByCustomer> getAllOrder() throws OrderException;

	public Boolean deleteOrderById(Integer orderId) throws OrderException;

	public OrderByCustomer updateOrder(OrderByCustomer orderByCustomer) throws OrderException;
}
