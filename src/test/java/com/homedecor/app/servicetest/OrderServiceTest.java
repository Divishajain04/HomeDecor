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
		OrderByCustomer getOrder = new OrderByCustomer(111, "Pending", null,null);
		assertTrue(orderService.addOrder(getOrder));
		assertEquals(true, this.orderService.deleteOrderById(111));
		assertThrows(OrderException.class,()->this.orderService.deleteOrderById(111));
	}
	
	@Test
	void addOrderTest() throws OrderException {
		assertThrows(OrderException.class,()->this.orderService.addOrder(null));
		OrderByCustomer getOrder = new OrderByCustomer(111, "Pending", null,null);
		assertTrue(orderService.addOrder(getOrder));
		assertNotNull(orderService);
		assertThrows(OrderException.class,()->this.orderService.addOrder(getOrder));
		assertEquals(true, orderService.deleteOrderById(111));
			
	}
	
	@Test
	void getOrderByIdTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(111, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		OrderByCustomer getOrder = orderService.getOrderById(111).get();
		Integer orderId = getOrder.getOrderId();
		assertEquals(111,orderId);
		assertNotNull(orderService.getOrderById(orderId));
		assertEquals(true, orderService.deleteOrderById(111));
		assertThrows(OrderException.class,()->this.orderService.getOrderById(111));
		
	}
	
	@Test
	void getAllOrdersTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(111, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		assertNotNull(this.orderService.getAllOrders());
		assertEquals(true, this.orderService.deleteOrderById(111));
	}
	
	@Test
	void updateOrderTest() throws OrderException {
		OrderByCustomer order = new OrderByCustomer(111, "Pending", null,null);
		assertTrue(orderService.addOrder(order));
		assertNotNull(this.orderService.updateOrder(new OrderByCustomer(111, "Done", null,null)));
		assertEquals(true, this.orderService.deleteOrderById(111));
		assertThrows(OrderException.class,()-> this.orderService.updateOrder(new OrderByCustomer(111, "Successfull", null,null)));
		
	}
	
	@Test
	void placeOrderTest() throws CustomerException, CategoryException, ProductException, CartException, WalletException, OrderException, PaymentException {
		Customer customer = new Customer(111, "Prince", "prince123@gmail.com", "Prince0404", "9424499512", "Ayodhya",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		assertNotNull(this.walletService.updateWallet(new Wallet(111,1000000.0)));
		OrderByCustomer order = new OrderByCustomer(111, "Pending", null,111);
		assertTrue(orderService.addOrder(order));
		
		Category category = new Category(111,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		
		
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20,111);
		assertTrue(this.productService.addProducts(product));
		
		this.cartService.addProductToCart(111, 111, 4);	
		
		assertTrue(this.orderService.placeOrder(111,111));
		assertEquals(true, this.customerService.deleteCustomer(111));
		assertEquals(true, this.orderService.deleteOrderById(111));
	    assertEquals(true,categoryService.deleteCategoryById(111));
	   
	}

}
