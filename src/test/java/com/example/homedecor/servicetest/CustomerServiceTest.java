package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
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
	void addCustomerTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(customerService.addCustomer(customer));
		assertNotNull(customer);
	}
	

	@AfterEach
	void deleteCustomerByIdTest() throws CustomerException {
		assertEquals(true,customerService.deleteCustomer(2));
	}
	
	
	
	@Test
	void getCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(2).get();
		Integer id = customer2.getCustomerId();
		assertEquals(2,id);
		assertNotNull(customerService.getCustomerById(id));
	}
	
}
