package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Category;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.dto.Wallet;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.exception.WalletException;
import com.homedecor.app.service.CartService;
import com.homedecor.app.service.CategoryService;
import com.homedecor.app.service.CustomerService;
import com.homedecor.app.service.OrderService;
import com.homedecor.app.service.ProductService;
import com.homedecor.app.service.WalletService;

@SpringBootTest
 class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private WalletService walletService;
	
	@Test
	void deleteOrderByIdTest() throws OrderException {
		OrderByCustomer getOrder = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(getOrder));
		assertEquals(true, this.orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()->this.orderService.deleteOrderById(8));
	}
	
	@Test
	void addOrderTest() throws OrderException {
		assertThrows(OrderException.class,()->this.orderService.addOrder(null));
		OrderByCustomer getOrder = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(getOrder));
		assertNotNull(orderService);
		assertThrows(OrderException.class,()->this.orderService.addOrder(getOrder));
		assertEquals(true, orderService.deleteOrderById(8));
			
	}
	
	@Test
	void getOrderByIdTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		OrderByCustomer getOrder = orderService.getOrderById(8).get();
		Integer orderId = getOrder.getOrderId();
		assertEquals(8,orderId);
		assertNotNull(orderService.getOrderById(orderId));
		assertEquals(true, orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()->this.orderService.getOrderById(8));
		
	}
	
	@Test
	void getAllOrdersTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		assertNotNull(this.orderService.getAllOrders());
		assertEquals(true, this.orderService.deleteOrderById(8));
		//assertThrows(OrderException.class,()-> this.orderService.getAllOrders());
	}
	
	@Test
	void updateOrderTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		assertNotNull(this.orderService.updateOrder(new OrderByCustomer(8, "Done", null,null)));
		assertEquals(true, this.orderService.deleteOrderById(8));
		assertThrows(OrderException.class,()-> this.orderService.updateOrder(new OrderByCustomer(8, "Successfull", null,null)));
		
	}
	
	@Test
	void placeOrderTest() throws CustomerException, CategoryException, ProductException, CartException, WalletException, OrderException, PaymentException {
		Customer customer = new Customer(15, "Prince", "prince123@gmail.com", "Prince0404", "9424499512", "Ayodhya",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		OrderByCustomer order = new OrderByCustomer(8, "Pending", null,15);
		Category category = new Category(15,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		assertTrue(orderService.addOrder(order));
		Product product = new Product(15, "Sofa", "Double layer Foam", 20000.0, 20,15);
		assertTrue(this.productService.addProducts(product));
		this.cartService.addProductToCart(15, 15, 4);
		assertNotNull(this.walletService.updateWallet(new Wallet(15,1000000.0)));
		assertTrue(this.orderService.placeOrder(15,8));
		assertEquals(true, this.customerService.deleteCustomer(15));
		assertEquals(true,productService.deleteProductById(15));
		assertEquals(true, this.orderService.deleteOrderById(8));
	}

}
