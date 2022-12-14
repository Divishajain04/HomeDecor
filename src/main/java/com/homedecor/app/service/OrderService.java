package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.exception.WalletException;

public interface OrderService {

	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException;

	public Optional<OrderByCustomer> getOrderById(Integer orderId) throws OrderException;

	public List<OrderByCustomer> getAllOrders() throws OrderException;

	public Boolean deleteOrderById(Integer orderId) throws OrderException;

	public OrderByCustomer updateOrder(OrderByCustomer orderByCustomer) throws OrderException;

	public Boolean placeOrder(Integer customerId,Integer orderId)
			throws OrderException, PaymentException, CartException, CustomerException, WalletException;
}
