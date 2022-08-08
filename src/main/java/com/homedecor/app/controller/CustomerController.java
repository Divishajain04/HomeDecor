package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Customer;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String greet() {
		return "Hello Gl!";
	}

	@PostMapping("customer")
	public String addCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		this.customerService.addCustomer(customer);
		return "Customer added SuccessFully";
	}

	@GetMapping("customer/{customerId}")
	public Optional<Customer> getCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException {
		Optional<Customer> foundCustomer = this.customerService.getCustomerById(customerId);
		return foundCustomer;
	}

	@GetMapping("customer/")
	public List<Customer> getAllCustomers() throws CustomerException {
		List<Customer> foundCustomers = this.customerService.findAllCustomers();
		return foundCustomers;
	}

	@PatchMapping("customer")
	public Customer updateCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		Customer updatedCustomer = this.customerService.updateCustomer(customer);
		return updatedCustomer;
	}

	@GetMapping("customer/{loginId}/{password}")
	public String login(@PathVariable("loginId") Integer loginId, @PathVariable("password") String password)
			throws CustomerException {
		this.customerService.login(loginId, password);
		return "Login Successfully";
	}

	@PutMapping("customers/{loginId}/{oldPassword}/{newPassword}")
	public String updatePassword(@PathVariable("loginId") Integer loginId,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword)
			throws CustomerException {
		this.customerService.updatePassword(loginId, oldPassword, newPassword);
		return "Password updated Successfully";
	}

	@PutMapping("Customer/{loginId}/{newEmail}")
	public String updateEmail(@PathVariable("loginId") Integer loginId, @PathVariable("newEmail") String newEmail)
			throws CustomerException {
		this.customerService.updateEmail(loginId, newEmail);
		return "Email updated Successfully";
	}

	@PutMapping("customer/phone/{loginId}/{newPhone}")
	public String updatePhone(@PathVariable("loginId") Integer loginId, @PathVariable("newPhone") String newPhone)
			throws CustomerException {
		this.customerService.updateMobileNo(loginId, newPhone);
		return "Mobile no. updated Successfully";
	}

	@PutMapping("customer/address/{loginId}/{newAddress}/")
	public String updateAddress(@PathVariable("loginId") Integer loginId, @PathVariable("newAddress") String newAddress)
			throws CustomerException {
		this.customerService.updateAddress(loginId, newAddress);
		return "Address updated Successfully";
	}

	@DeleteMapping("customer/{customerId}")
	public String deleteCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException {
		this.customerService.deleteCustomer(customerId);
		return "Customer deleted Successfully";
	}
}
