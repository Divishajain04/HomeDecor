package com.example.homedecor.service;

import java.util.List;
import java.util.Optional;

import com.example.homedecor.dto.Customer;
import com.example.homedecor.exception.CustomerException;

public interface CustomerService {


	public boolean addCustomer(Customer customer)throws CustomerException;
	
	public boolean deleteCustomer(Integer customerId)throws CustomerException;
	
	public Optional<Customer> getCustomerById(Integer customerId)throws CustomerException;
	
	public Boolean login(Integer customerId, String password)throws CustomerException;
	
	public List<Customer> findAllCustomers()throws CustomerException;
	
	public Boolean updateAddress(Integer customerId, String newAddress)throws CustomerException;
	
	public Boolean updateMobileNo(Integer customerId, String newMobileNo)throws CustomerException;
	
	public Boolean updateEmail(Integer customerId, String newEmail)throws CustomerException;
	
	public Boolean updatePassword(Integer customerId, String oldPassword, String newPassword)throws CustomerException;
	
	public Customer updateCustomer(Customer customer)throws CustomerException;
	
	

}
