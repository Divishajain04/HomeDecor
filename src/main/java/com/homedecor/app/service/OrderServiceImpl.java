package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.OrderRepository;
import com.homedecor.app.dao.PaymentRepository;
import com.homedecor.app.dao.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;

	Integer newOrderId;
	@Override
	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException {
		if (orderByCustomer == null) {
			throw new OrderException("Order not added! Please fill the mandatory field");
		}
		Optional<OrderByCustomer> addOrderResult = this.orderRepository.findById(orderByCustomer.getOrderId());
		if (addOrderResult.isPresent()) {
			throw new OrderException("Order Id is already present in the record");
		} else {
			newOrderId=orderByCustomer.getOrderId();
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
	public Boolean placeOrderStatus(Integer CustomerId,  Integer paymentId)
			throws OrderException, PaymentException, CartException, CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(CustomerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("Customer ID is not present in record");
		Customer foundCustomer = getCustomer.get();
		
		Optional<Cart> getCart = this.cartRepository.findById(foundCustomer.getCustomerId());
		if (getCart.isEmpty())
			throw new CartException("Cart ID is not present in record");
		Cart foundCart = getCart.get();
		
		Optional<Payment> getPayment = this.paymentRepository.findById(paymentId);
		if (getPayment.isEmpty()) 
				throw new PaymentException("Payment ID is not present in record");
		Payment foundPayment = getPayment.get();
		
		OrderByCustomer order = this.orderRepository.findById(newOrderId).get();
		String savedStatus = order.getStatus();
		String savedStatusOfPayment = foundPayment.getPaymentStatus();
		Double cartTotalAmount = this.cartService.totalAmountOfCustomerCartById(foundCart.getCartId()).get();
		Double avilableBalance = foundPayment.getPaymentAmount();
		
		List<Product> cartProduct = foundCart.getProduct();
	
		List<Product> allProduct = productRepository.findAll();
		cartProduct.forEach(r -> {
			final Optional<Product> existProduct = allProduct.stream()
					.filter(d -> d.getProductId().equals(r.getProductId())).findFirst();
			Integer newQuantity=existProduct.get().getQuantity()-1;
			if(newQuantity<0) {
				existProduct.get().setQuantity(newQuantity);
				this.productRepository.saveAll(allProduct);
			}
			
			
		});
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
				foundCustomer.setOrderByCustomer(getAllOrders());
				this.customerRepository.save(foundCustomer);
			} else {
				String newStatusOfPayment = foundPayment.getPaymentStatus().replaceAll(savedStatusOfPayment,
						"Payment unSuccessfull");
				foundPayment.setPaymentStatus(newStatusOfPayment);
				this.paymentRepository.save(foundPayment);
				String newStatus = order.getStatus().replaceAll(savedStatus, "Order Not Placed");
				order.setStatus(newStatus);
				this.orderRepository.save(order);
				order.setPayment(foundPayment);
				this.orderRepository.save(order);
				throw new PaymentException("Not having sufficent Balance to place Order");
			}
		return true;
	}
}
