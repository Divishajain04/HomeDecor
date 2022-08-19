package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.OrderByCustomer;
import com.homedecor.app.dto.Payment;
import com.homedecor.app.dto.Wallet;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.OrderException;
import com.homedecor.app.exception.PaymentException;
import com.homedecor.app.exception.WalletException;
import com.homedecor.app.exception.WishlistException;
import com.homedecor.app.service.CartService;
import com.homedecor.app.service.CustomerService;
import com.homedecor.app.service.WalletService;
import com.homedecor.app.service.WishlistService;

@SpringBootTest
class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	
	
	
	@Test
	void deleteCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		assertEquals(true, this.customerService.deleteCustomer(8));
		assertThrows(CustomerException.class, () -> this.customerService.deleteCustomer(8));
	}

	@Test
	void addCustomerTest() throws CustomerException {
		assertThrows(CustomerException.class,()->this.customerService.addCustomer(null));
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		assertNotNull(customer);
		assertThrows(CustomerException.class,()->this.customerService.addCustomer(customer));
		assertEquals(true, customerService.deleteCustomer(8));		
	}
	
	@Test
	void getCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		Customer getCustomer = customerService.getCustomerById(8).get();
		Integer customerId = getCustomer.getCustomerId();
		assertEquals(8, customerId);
		assertNotNull(customerService.getCustomerById(customerId));
		assertEquals(true, customerService.deleteCustomer(8));
		assertThrows(CustomerException.class, () -> this.customerService.getCustomerById(8));
	}
	

	@Test
	void getAllCustomerTest() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.findAllCustomers());
		assertEquals(true, this.customerService.deleteCustomer(8));
	}
/*
	@Test
	void customerLoginTest() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(8).get();
		String customerEmailIdTest = customer2.getCustomerEmail();
		String customerPasswordTest = customer2.getPassword();
		assertEquals(true, this.customerService.login(customerEmailIdTest, customerPasswordTest));
		assertEquals(true, this.customerService.deleteCustomer(8));
		assertThrows(CustomerException.class, () -> this.customerService.login(customerEmailId, customerPasswordTest));
	}

	*/
	@Test
	void updateCustomerAddress() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer getCustomer = customerService.getCustomerById(8).get();
		Integer customerId = getCustomer.getCustomerId();
		assertEquals(8, customerId);
		assertEquals(true ,customerService.updateAddress(customerId, "Neemuch"));
		assertEquals(true, this.customerService.deleteCustomer(8));
	}

	@Test
	void updateCustomerEmail() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer getCustomer = customerService.getCustomerById(8).get();
		Integer customerId = getCustomer.getCustomerId();
		assertEquals(8, customerId);
		assertEquals(true, customerService.updateEmail(customerId, "dibu0404@gmail.com"));
		assertEquals(true, this.customerService.deleteCustomer(8));
	}

	@Test
	void updateCustomerPhone() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(8).get();
		Integer customerInteger = customer2.getCustomerId();
		assertEquals(8, customerInteger);
		assertEquals(true,customerService.updateMobileNo(customerInteger, "7999779211"));
		assertEquals(true, this.customerService.deleteCustomer(8));
	}

/*
	@Test
	void updatePassword() throws CustomerException {
		Customer customer = new Customer(6, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",
				null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(6).get();
		String customerEmailId = customer2.getCustomerEmail();
		String customerPassword = customer2.getPassword();
		assertEquals(true, this.customerService.updatePassword(customerEmailId, customerPassword, "Jain08"));
		assertEquals(true, this.customerService.deleteCustomer(6));
	}
*/
	

	@Test
	void updateCustomerInformation() throws CustomerException {
		assertThrows(CustomerException.class, () -> this.customerService.addCustomer(null));
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(8).get();
		Integer customerInteger = customer2.getCustomerId();
		assertEquals(8, customerInteger);
		assertThrows(CustomerException.class, () -> this.customerService.updateCustomer(null));
		assertNotNull(customerService.updateCustomer(new Customer(8, "Divisha Jain", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null)));
		assertEquals(true, this.customerService.deleteCustomer(8));
		assertThrows(CustomerException.class, () -> this.customerService.updateCustomer(customer));

	}

	@Test
	void totalRegisterCustomerTest() throws CustomerException{
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		assertEquals(1, customerService.totalRegisteredCustomer());
		assertEquals(true, this.customerService.deleteCustomer(8));
	}
}
