package com.example.homedecor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.CustomerException;
import com.example.homedecor.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String greet() {
		return "Hello Gl!";
	}
	
	@PostMapping("customer")
	public String addCustomer(@RequestBody Customer customer) throws CustomerException {
		this.customerService.addCustomer(customer);
		return "Customer added SuccessFully";
	}
	
	@GetMapping("customer/{customerId}")
	public Optional<Customer> getCustomerById(@PathVariable("customerId")Integer customerId) throws CustomerException {
		return this.customerService.getCustomerById(customerId);
		
	}
	
	@GetMapping("customer/")
	public List<Customer> getCustomerById() throws CustomerException {
		return this.customerService.findAllCustomer();
		
	}

	@DeleteMapping("customer/{customerId}")
	public Boolean deleteCustomer(@PathVariable("customerId") Integer customerId) throws CustomerException{
		return this.customerService.deleteCustomer(customerId);
	}
	
	
	@GetMapping("customerLogin/{customerId}/{password}")
	public Boolean customerLogin(@PathVariable("customerId") Integer customerId, @PathVariable("password") String password) throws CustomerException{
		return this.customerService.Login(customerId, password);
	}
}
