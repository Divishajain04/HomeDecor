package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

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
	public List<Customer> findAllCustomer() throws CustomerException {
		List<Customer> allCustomer = this.customerRepositary.findAll();
		if (allCustomer.isEmpty()) {
			throw new CustomerException("Record is empty");
		}
		return allCustomer;
	}

	@Override
	public Boolean updateAddress(Integer customerId, String newAddress) throws CustomerException {
		Customer foundCustomer = this.customerRepositary.findById(customerId).get();
		if(foundCustomer==null)throw new CustomerException("customer not exist for this Id " + customerId);
		String savedAddress=foundCustomer.getCustomerAddress();
		foundCustomer.getCustomerAddress().replaceAll(savedAddress, newAddress);
		foundCustomer.setCustomerAddress(newAddress);
		this.customerRepositary.save(foundCustomer);
		return true;
	}

	@Override
	public Boolean updateMobileNo(Integer customerId, String newMobileNo) throws CustomerException {
		Customer foundCustomer = this.customerRepositary.findById(customerId).get();
		if(foundCustomer==null)throw new CustomerException("customer not exist for this Id " + customerId);
		String SavedPhoneNo=foundCustomer.getCustomerPhoneNo();
		foundCustomer.getCustomerPhoneNo().replaceAll(SavedPhoneNo, newMobileNo);
		foundCustomer.setCustomerPhoneNo(newMobileNo);
		this.customerRepositary.save(foundCustomer);
		return true;
	}

	@Override
	public Boolean updateEmail(Integer customerId, String newEmail) throws CustomerException {
		Customer foundCustomer = this.customerRepositary.findById(customerId).get();
		if(foundCustomer==null)throw new CustomerException("customer not exist for this Id " + customerId);
		String savedAddress=foundCustomer.getCustomerEmail();
		foundCustomer.getCustomerEmail().replaceAll(savedAddress, newEmail);
		foundCustomer.setCustomerEmail(newEmail);
		this.customerRepositary.save(foundCustomer);
		return true;
	}

	@Override
	public Boolean updatePassword(Integer customerId, String oldPassword, String newPassword) throws CustomerException {
		Customer foundCustomer = this.customerRepositary.findById(customerId).get();
		String savedPassword = foundCustomer.getPassword();
		Boolean isLogin = false;
		if (savedPassword.compareTo(oldPassword) == 0) {
			foundCustomer.getPassword().replaceAll(oldPassword, newPassword);
			foundCustomer.setPassword(newPassword);
			customerRepositary.save(foundCustomer);
		} else {
			throw new CustomerException("Old password dosen't match");
		}
		return isLogin;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		if (customer == null)
			throw new CustomerException("Customer not updated please fill the mandatory coloumn");
		Optional<Customer> foundCustomer = this.customerRepositary.findById(customer.getCustomerId());
		if (foundCustomer.isEmpty())
			throw new CustomerException("Customer not avilable for this id ");
		return this.customerRepositary.save(customer);
	}

}
