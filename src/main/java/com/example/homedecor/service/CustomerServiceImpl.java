package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import javax.management.loading.PrivateClassLoader;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.CustomerRepository;
import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepositary;

	@Override
	public boolean addCustomer(Customer customer) throws CustomerException {
		if (customer == null) {
			throw new CustomerException("Customer not added");
		}
		Optional<Customer> foundCustomer = this.customerRepositary.findById(customer.getCustomerId());
		if (foundCustomer.isPresent()) {
			throw new CustomerException("Customer already exist");
		} else {
			this.customerRepositary.save(customer);
		}
		return true;
	}

	@Override
	public Boolean updateAddress(Integer customerId) throws CustomerException {
		return null;
	}

	@Override
	public Boolean updateMobileNo(Integer customerId) throws CustomerException {
		return null;
	}

	@Override
	public Boolean updateEmail(Integer customerId) throws CustomerException {
		return null;
	}

	@Override
	public boolean deleteCustomer(Integer customerId) throws CustomerException {
		this.customerRepositary.deleteById(customerId);
		return true;
	}

	@Override
	public Optional<Customer> getCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> foundCustomer = this.customerRepositary.findById(customerId);
		if (foundCustomer.isEmpty()) {
			throw new CustomerException("This customer is not present in record");
		}
		return foundCustomer;
	}

	@Override
	public Boolean Login(Integer customerId, String password) throws CustomerException {
		Boolean isCustomerLogin = false;
		Optional<Customer> loginCustomer = this.customerRepositary.findByCustomerIdAndPassword(customerId, password);
		if (loginCustomer.isEmpty()) {
			throw new CustomerException("Invalid Id or Password");
		}
		else {
			isCustomerLogin = true;
		}
		return isCustomerLogin;
	}

	@Override
	public Boolean updatePassword(Integer customerId, String password) throws CustomerException {
		return null;
	}

	@Override
	public List<Customer> findAllCustomer() throws CustomerException {
		List<Customer> allCustomer = this.customerRepositary.findAll();
		if (allCustomer.isEmpty()) {
			throw new CustomerException("Record is empty");
		}
		return allCustomer;
	}

}
