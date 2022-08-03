package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.CustomerException;
import com.example.homedecor.service.CustomerService;

@SpringBootTest
 class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	void deleteCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertEquals(true, this.customerService.deleteCustomer(2));
		assertThrows(CustomerException.class, () -> this.customerService.deleteCustomer(2));
	}

	@Test
	void addCustomerTest() throws CustomerException {
		assertThrows(CustomerException.class, () -> this.customerService.addCustomer(null));
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(2));
		assertThrows(CustomerException.class, () -> this.customerService.addCustomer(customer));
		assertEquals(true, this.customerService.deleteCustomer(2));

	}

	@Test
	void getCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(2));
		assertEquals(true, this.customerService.deleteCustomer(2));
		assertThrows(CustomerException.class, () -> this.customerService.getCustomerById(2));
	}

	@Test
	void getAllCustomerTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.findAllCustomers());
		assertEquals(true, this.customerService.deleteCustomer(2));
	}
	
	@Test
	void customerLoginTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(2).get();
		Integer customerIdTest = customer2.getCustomerId();
		String customerPasswordTest = customer2.getPassword();
		assertEquals(true, this.customerService.login(customerIdTest, customerPasswordTest));
		assertEquals(true,this.customerService.deleteCustomer(2));
		assertThrows(CustomerException.class, () -> this.customerService.login(customerIdTest, customerPasswordTest));
	}
	
	
	@Test
	void updateCustomerAddressTest() throws CustomerException{
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(2));
		assertEquals(true,this.customerService.updateAddress(2,"Neemuch"));
		assertEquals(true, this.customerService.deleteCustomer(2));
	}
	
	@Test
	void updateCustomerEmailTest() throws CustomerException{
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(2));
		assertEquals(true,this.customerService.updateEmail(2,"dibu123@gmail.com"));
		assertEquals(true, this.customerService.deleteCustomer(2));
	}

	@Test
	void updateCustomerPhoneTest() throws CustomerException{
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(2));
		assertEquals(true,this.customerService.updateMobileNo(2,"7999779211"));
		assertEquals(true, this.customerService.deleteCustomer(2));
	}

}
