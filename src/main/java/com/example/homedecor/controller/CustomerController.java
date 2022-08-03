package com.example.homedecor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public String addCustomer(@RequestBody Customer customer) throws CustomerException  {
		try {
			this.customerService.addCustomer(customer);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Customer added SuccessFully";
	}
	
	@GetMapping("customer/{customerId}")
	public Optional<Customer> getCustomerById(@PathVariable("customerId")Integer customerId) throws CustomerException  {
		Optional<Customer> foundCustomer=null;
		try {
			foundCustomer=this.customerService.getCustomerById(customerId);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return foundCustomer;
	}
	
	@GetMapping("customer/")
	public List<Customer> getAllCustomers() throws CustomerException  {
		List<Customer> foundCustomers=null;
		try {
			foundCustomers=this.customerService.findAllCustomer();
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return foundCustomers;
		
	}
	@PatchMapping("customer")
	public Customer updateCustomer(@RequestBody Customer customer) throws CustomerException{
		Customer updatedCustomer=null;
		try {
		     updatedCustomer=this.customerService.updateCustomer(customer);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return updatedCustomer;
	}
	
	@GetMapping("customer/{loginId}/{password}")
	public String login(@PathVariable("loginId")Integer loginId,@PathVariable("password") String password) throws CustomerException {
		try {
			this.customerService.login(loginId, password);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Login Successfully";
	}
	
	@PutMapping("customers/{loginId}/{oldPassword}/{newPassword}")
	public String updatePassword(@PathVariable("loginId")Integer loginId,@PathVariable("oldPassword") String oldPassword,@PathVariable("newPassword") String newPassword) throws CustomerException {
		try {
			this.customerService.updatePassword(loginId, oldPassword, newPassword);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Password updated Successfully";
	}
	
	@PutMapping("Customer/{loginId}/{newEmail}")
	public String updateEmail(@PathVariable("loginId")Integer loginId,@PathVariable("newEmail") String newEmail) throws CustomerException {
		try {
			this.customerService.updateEmail(loginId, newEmail);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Email updated Successfully";
	}
	
	@PutMapping("customer/phone/{loginId}/{newPhone}")
	public String updatePhone(@PathVariable("loginId")Integer loginId,@PathVariable("newPhone") String newPhone) throws CustomerException {
		try {
			this.customerService.updateMobileNo(loginId, newPhone);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Mobile no. updated Successfully";
	}
	
	
	@PutMapping("customer/address/{loginId}/{newAddress}/")
	public String updateAddress(@PathVariable("loginId")Integer loginId,@PathVariable("newAddress") String newAddress) throws CustomerException {
		try {
			this.customerService.updateAddress(loginId, newAddress);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Address updated Successfully";
	}
	
	@DeleteMapping("customer/{customerId}")
	public String deleteCustomerById (@PathVariable("customerId")Integer customerId) throws CustomerException {
		try {
			this.customerService.deleteCustomer(customerId);
		} catch (CustomerException e) {
			throw new CustomerException(e.getMessage());
		}
		return "Customer deleted Successfully";
	}
}
