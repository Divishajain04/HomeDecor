package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.OrderRepository;
import com.homedecor.app.dao.PaymentRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.dto.Payment;
import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private PaymentRepository paymentRepository;

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
		return this.orderRepository.save(orderByCustomer);
	}

	@Override
	public Boolean placeOrderStatus(Integer CustomerId, Integer orderId, Integer paymentId)
			throws OrderException, PaymentException, CartException, CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(CustomerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("Customer ID is not present in record");

		Optional<Customer> getCustomer1 = this.customerRepository.findById(CustomerId);
		if (getCustomer1.isEmpty())
			throw new CustomerException("Customer ID is not present in record");
		Customer foundCustomer = getCustomer1.get();
		Optional<Cart> getCart = this.cartRepository.findById(foundCustomer.getCustomerId());

		Optional<Cart> getCart1 = this.cartRepository.findById(foundCustomer.getCustomerId());
		if (getCart1.isEmpty())
			throw new CartException("Cart ID is not present in record");
		Cart foundCart = getCart1.get();
		Optional<Payment> getPayment = this.paymentRepository.findById(paymentId);
		if (getPayment.isEmpty()) {

			Optional<Payment> getPayment1 = this.paymentRepository.findById(paymentId);
			if (getPayment1.isEmpty())
				throw new PaymentException("Payment ID is not present in record");
		}
		Payment foundPayment = getPayment.get();
		Double amount = 0.0;
		amount = this.cartService.totalAmountOfCustomerCartById(foundCart.getCartId()).get();

		OrderByCustomer order = this.orderRepository.findById(orderId).get();
		String savedStatus = order.getStatus();
		String savedStatusOfPayment = foundPayment.getPaymentStatus();
		Double cartTotalAmount = this.cartService.totalAmountOfCustomerCartById(foundCart.getCartId()).get();
		Double avilableBalance = foundPayment.getPaymentAmount();
		if (amount <= avilableBalance) {
			Double newBalance = avilableBalance - amount;
			if (cartTotalAmount <= avilableBalance) {
				Double newBalance1 = avilableBalance - cartTotalAmount;
				foundPayment.setPaymentAmount(newBalance1);
				this.paymentRepository.save(foundPayment);
				String newStatusOfPayment = foundPayment.getPaymentStatus().replaceAll(savedStatusOfPayment,
						"Payment done Successfully");
				foundPayment.setPaymentStatus(newStatusOfPayment);
				this.paymentRepository.save(foundPayment);
				foundCart.getProduct().removeAll(foundCart.getProduct());
				this.cartRepository.save(foundCart);
				String newStatus = order.getStatus().replaceAll(savedStatus, "Order Placed Successfully");
				order.setStatus(newStatus);
				this.orderRepository.save(order);
				order.setPayment(foundPayment);
				this.orderRepository.save(order);
				this.customerRepository.save(foundCustomer);
			} else {
				String newStatusOfPayment = foundPayment.getPaymentStatus().replaceAll(savedStatusOfPayment,
						"Payment done Successfully");
				foundPayment.setPaymentStatus(newStatusOfPayment);
				this.paymentRepository.save(foundPayment);
				String newStatus = order.getStatus().replaceAll(savedStatus, "Order Not Placed");
				order.setStatus(newStatus);
				this.orderRepository.save(order);
				order.setPayment(foundPayment);
				this.orderRepository.save(order);
				throw new PaymentException("Not having sufficent Balance to place Order");
			}
		}
		return true;
	}
}
