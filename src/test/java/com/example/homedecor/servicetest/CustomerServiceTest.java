package com.example.homedecor.servicetest;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Admin;
import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.AdminException;
import com.example.homedecor.exception.CustomerException;
import com.example.homedecor.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Test
	void addCustomerTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(customerServiceImpl.addCustomer(customer));
		assertNotNull(customer != null);
	}
	

	@AfterEach
	void deleteCustomerByIdTest() throws CustomerException {
		assertEquals(true,customerServiceImpl.deleteCustomer(2));
	}
	
	
	
	@Test
	void getCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(2,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(customerServiceImpl.addCustomer(customer));
		Customer customer2 = customerServiceImpl.getCustomerById(2).get();
		Integer id = customer2.getCustomerId();
		assertEquals(id,2);
		assertNotNull(customerServiceImpl.getCustomerById(id));
	}
	
}
