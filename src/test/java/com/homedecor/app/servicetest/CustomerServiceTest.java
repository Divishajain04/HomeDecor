package com.homedecor.app.servicetest;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Customer;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.service.CustomerService;

@SpringBootTest
 class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	void deleteCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertEquals(true, this.customerService.deleteCustomer(4));
		assertThrows(CustomerException.class, () -> this.customerService.deleteCustomer(4));
	}

	@Test
	void addCustomerTest() throws CustomerException {
		assertThrows(CustomerException.class, () -> this.customerService.addCustomer(null));
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(4));
		assertThrows(CustomerException.class, () -> this.customerService.addCustomer(customer));
		assertEquals(true, this.customerService.deleteCustomer(4));

	}

	@Test
	void getCustomerByIdTest() throws CustomerException {
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.getCustomerById(4));
		assertEquals(true, this.customerService.deleteCustomer(4));
		assertThrows(CustomerException.class, () -> this.customerService.getCustomerById(4));
	}

	@Test
	void getAllCustomerTest() throws CustomerException {
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.findAllCustomers());
		assertEquals(true, this.customerService.deleteCustomer(4));
		assertThrows(CustomerException.class, () -> this.customerService.findAllCustomers());
	}
	
	@Test
	void customerLoginTest() throws CustomerException {
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(4).get();
		Integer customerIdTest = customer2.getCustomerId();
		String customerPasswordTest = customer2.getPassword();
		assertEquals(true, this.customerService.login(customerIdTest, customerPasswordTest));
		assertEquals(true,this.customerService.deleteCustomer(4));
		assertThrows(CustomerException.class, () -> this.customerService.login(customerIdTest, customerPasswordTest));
	}
	
	
	@Test
	void updateCustomerAddress() throws CustomerException{
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);		
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(4).get();
		Integer customerIdInteger = customer2.getCustomerId();
		assertTrue(this.customerService.updateAddress(customerIdInteger, "Neemuch"));
		assertEquals(true, this.customerService.deleteCustomer(4));
	}
	
	@Test
	void updateCustomerEmail() throws CustomerException{
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(4).get();
		Integer customerIdInteger = customer2.getCustomerId();
		assertTrue(this.customerService.updateEmail(customerIdInteger, "dibu0404@gmail.com"));
		assertEquals(true, this.customerService.deleteCustomer(4));
	}
	
	@Test
	void updateCustomerPhone() throws CustomerException{
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(4).get();
		Integer customerInteger = customer2.getCustomerId();
		assertTrue(this.customerService.updateMobileNo(customerInteger,"7999779211"));
		assertEquals(true,this.customerService.deleteCustomer(4));
	}
	
	@Test
	void updatePassword() throws CustomerException{
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		Customer customer2 = customerService.getCustomerById(4).get();
		Integer customerInteger = customer2.getCustomerId();
		String customerPassword = customer2.getPassword();
		assertEquals(true, this.customerService.updatePassword(customerInteger, customerPassword, "Jain08"));
		assertEquals(true, this.customerService.deleteCustomer(4));
	}
	
	@Test
	void updateCustomerInformation() throws CustomerException{
		assertThrows(CustomerException.class, () -> this.customerService.addCustomer(null));
		Customer customer = new Customer(4,"Divisha","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null);
		assertTrue(this.customerService.addCustomer(customer));
		assertNotNull(this.customerService.updateCustomer(new Customer(4,"Rubi","divisha123@gmail.com","Dibu04","9424499512","Jawad",null,null,null)));
		assertThrows(CustomerException.class, () -> this.customerService.updateCustomer(null));
		assertEquals(true, this.customerService.deleteCustomer(4));
		assertThrows(CustomerException.class, () -> this.customerService.updateCustomer(customer));
		
	}
}
