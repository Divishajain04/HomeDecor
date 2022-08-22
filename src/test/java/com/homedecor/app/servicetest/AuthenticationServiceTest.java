package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Admin;
import com.homedecor.app.dto.Authentications;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.service.AdminService;
import com.homedecor.app.service.AuthenticationService;
import com.homedecor.app.service.CustomerService;

@SpringBootTest
class AuthenticationServiceTest {
	
	@Autowired
	private AuthenticationService authenticationService; 
	
	@Autowired 
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	void adminAuthenticationTest() throws AdminException {
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		assertTrue(this.authenticationService.adminAuthentication(new Authentications("lucky@gmail.com", "lucky@0888", null)));
		assertEquals(true, this.adminService.deleteAdminById(8));	
	}
	
	@Test
	void customerAuthenticationTest() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		assertTrue(this.authenticationService.customerAuthentication(new Authentications("divisha123@gmail.com", "Divisha0404", null)));
		assertEquals(true, this.customerService.deleteCustomer(8));
	}
	@Test
	void updateAdminPasswordTest() throws AdminException {
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		assertTrue(this.authenticationService.updateAdminPassword(new Authentications("lucky@gmail.com", "lucky@0888", "lucky@81222")));
		assertEquals(true, this.adminService.deleteAdminById(8));	
	}
	
	@Test
	void updateCustomerPasswordTest() throws CustomerException {
		Customer customer = new Customer(8, "Divisha", "divisha123@gmail.com", "Divisha0404", "9424499512", "Jawad",null, null, null, null);
		assertTrue(this.customerService.addCustomer(customer));
		assertTrue(this.authenticationService.updateCustomerPassword(new Authentications("divisha123@gmail.com", "Divisha0404", "jawed@121212")));
		assertEquals(true, this.customerService.deleteCustomer(8));
	}
	

}
