package com.homedecor.app.service;

import java.util.ArrayList;
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

/************************************************************************************
 *   @author           Prince Verma
 *   Description       It is a service class that provides the services for adding a new OrderByCustomer, 
                       updating OrderByCustomer, view one OrderByCustomer by orderId, view all OrderByCustomer,
                       delete a OrderByCustomer by order Id and place Order
                      
 *   Version          1.0
 *   Created Date     16-AUG-2022
 ************************************************************************************/
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

	/************************************************************************************
	 * Method:                  - addOrder
     * Description:             - To add a new OrderByCustomer into the database
	 * @param OrderByCustomer   - OrderByCustomer's object
	 * @returns Boolean         - true, if OrderByCustomer added otherwise throws OrderException
	 * @throws OrderException   - It is raised due to mandatory details are not filled or OrderByCustomer already exist
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Boolean addOrder(OrderByCustomer orderByCustomer) throws OrderException {
		if (orderByCustomer == null) {
			throw new OrderException("Order not added! Please fill the mandatory field");
		}
		Optional<OrderByCustomer> addOrderResult = this.orderRepository.findById(orderByCustomer.getOrderId());
		if (addOrderResult.isPresent()) {
			throw new OrderException("Order Id is already present in the record");
		} else {
			orderByCustomer.getOrderId();
			this.orderRepository.save(orderByCustomer);
		}
		return true;
	}

	/************************************************************************************
	 * Method:                  - orderId
     * Description:             - View orderByCustomer details through order Id
	 * @param orderId           - order's Id
	 * @returns Optional        - Present, if OrderByCustomer exist otherwise throws OrderException
	 * @throws OrderException   - It is raised due to OrderByCustomer not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Optional<OrderByCustomer> getOrderById(Integer orderId) throws OrderException {
		Optional<OrderByCustomer> foundOrder = this.orderRepository.findById(orderId);
		if (foundOrder.isEmpty()) {
			throw new OrderException("Invalid Order Id");
		}
		return foundOrder;
	}

	/************************************************************************************
	 * Method:                  - getAllOrders
     * Description:             - View all orderByCustomer and it's details
	 * @returns List            - Present, if OrderByCustomer exist otherwise throws OrderException
	 * @throws OrderException   - It is raised due to OrderByCustomers not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public List<OrderByCustomer> getAllOrders() throws OrderException {
		List<OrderByCustomer> orderList = this.orderRepository.findAll();
		if (orderList.isEmpty()) {
			throw new OrderException("Order List is empty");
		}
		return orderList;
	}

	/************************************************************************************
	 * Method:                  - deleteOrderById
     * Description:             - Delete orderByCustomer through order Id
	 * @param orderId           - order's Id
	 * @returns Boolean         - true, if orderId deleted otherwise throws OrderException
	 * @throws OrderException   - It is raised due to orderByCustomer not exist for this Id
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Boolean deleteOrderById(Integer orderId) throws OrderException {
		Optional<OrderByCustomer> foundOrder = this.orderRepository.findById(orderId);
		if (foundOrder.isEmpty()) {
			throw new OrderException("Order not exist for this Id");
		}
		this.orderRepository.deleteById(orderId);
		return true;
	}

	/************************************************************************************
	 * Method:                  - updateOrder
     * Description:             - Update orderByCustomer details through order Id
	 * @param OrderByCustomer   - OrderByCustomer object
	 * @returns OrderByCustomer - OrderByCustomer object, if OrderByCustomer exist otherwise throws OrderException
	 * @throws OrderException   - It is raised due to OrderByCustomer not exist for this Id
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public OrderByCustomer updateOrder(OrderByCustomer orderByCustomer) throws OrderException {
		Optional<OrderByCustomer> foundOrder = this.orderRepository.findById(orderByCustomer.getOrderId());
		if (foundOrder.isEmpty()) {
			throw new OrderException("Order not exist for this Id");
		}
		return this.orderRepository.save(orderByCustomer);
	}

	/************************************************************************************
	 * Method:                  - placeOrder
     * Description:             - Place order By Customer
     * @param customerId        - Customer's Id
     * @param orderId           - Order's Id
	 * @param paymentId         - Payments's Id
	 * @returns Boolean         - true, if Customer exist, OrderByCustomer exist, Payment exist otherwise throws CustomerException, OrderException, PaymentException
	 * @throws OrderException   - It is raised due to OrderByCustomer Id not found in database
	 * @throws CustomerException- It is raised due to Customer Id not found in database
	 * @throws PaymentException - It is raised due to Payment Id not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Boolean placeOrder(Integer customerId, Integer orderId, Integer paymentId)
			throws OrderException, PaymentException, CartException, CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(customerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("Customer Id is not present in record");
		Customer foundCustomer = getCustomer.get();

		Optional<Cart> getCart = this.cartRepository.findById(foundCustomer.getCustomerId());
		if (getCart.isEmpty())
			throw new CartException("Cart Id is not present in record");
		Cart foundCart = getCart.get();

		Optional<Payment> getPayment = this.paymentRepository.findById(paymentId);
		if (getPayment.isEmpty())
			throw new PaymentException("Payment Id is not present in record");
		Payment foundPayment = getPayment.get();

		OrderByCustomer order = this.orderRepository.findById(orderId).get();
		String savedStatus = order.getStatus();
		String savedStatusOfPayment = foundPayment.getPaymentStatus();
		Double cartTotalAmount = this.cartService.totalAmountOfCustomerCartById(foundCart.getCartId()).get();
		Double avilableBalance = foundPayment.getPaymentAmount();

		List<Product> cartProduct = foundCart.getProduct();

		List<Product> allProduct = productRepository.findAll();

		cartProduct.forEach(r -> {
			final Optional<Product> existProduct = allProduct.stream()
					.filter(d -> d.getProductId().equals(r.getProductId())).findFirst();
			Integer newQuantity = existProduct.get().getQuantity() - 1;
			if (newQuantity > 0) {
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
			// foundCustomer.setOrderByCustomer();
			this.customerRepository.save(foundCustomer);
			//this.customerRepository.save(foundCustomer);
			if (order.getCustomerId().equals(customerId)) {
				List<OrderByCustomer> getAllOrders = new ArrayList<>();
				getAllOrders.addAll(foundCustomer.getOrderByCustomer());
				getAllOrders.add(order);
				foundCustomer.setOrderByCustomer(getAllOrders);
				this.orderRepository.save(order);
				this.customerRepository.save(foundCustomer);		
			}
			else {
				order.setCustomerId(customerId);
				List<OrderByCustomer> getAllOrders = new ArrayList<>();
				getAllOrders.addAll(foundCustomer.getOrderByCustomer());
				getAllOrders.add(order);
				foundCustomer.setOrderByCustomer(getAllOrders);
				this.orderRepository.save(order);
				this.customerRepository.save(foundCustomer);
			}
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
