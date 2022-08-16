package com.homedecor.app.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.WishlistRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	

	@Override
	public boolean addCustomer(Customer customer) throws CustomerException {
		if (customer == null) {
			throw new CustomerException("Customer not added please fill the mandatory feilds");
		}
		Optional<Customer> foundCustomer = this.customerRepository.findById(customer.getCustomerId());
		if (foundCustomer.isPresent()) {
			throw new CustomerException("Customer already exist");
		} else {
			Cart cart =	this.cartRepository.save(new Cart(customer.getCustomerId()));
			Wishlist wishlist =	this.wishlistRepository.save(new Wishlist(customer.getCustomerId()));
			customer.setCart(cart);
			customer.setWishlist(wishlist);
			this.customerRepository.save(customer);
		}
		return true;
	}

	@Override
	public boolean deleteCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> foundCustomer = this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) {
			throw new CustomerException("This customer is not present in record");
		}
		
		this.customerRepository.deleteById(customerId);
		this.cartRepository.deleteById(customerId);
		this.wishlistRepository.deleteById(customerId);
		return true;
	}

	@Override
	public Optional<Customer> getCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> foundCustomer = this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) {
			throw new CustomerException("This customer is not present in record");
		}
		return foundCustomer;
	}

	@Override
	public Boolean login(String customerEmail, String password) throws CustomerException {
		Boolean isCustomerLogin = false;
		Optional<Customer> loginCustomer = this.customerRepository.findByCustomerEmailAndPassword(customerEmail, password);
		if (loginCustomer.isEmpty()) {
			throw new CustomerException("Invalid Id or Password");
		}
		else {
			isCustomerLogin = true;
		}
		return isCustomerLogin;
	}

	@Override
	public List<Customer> findAllCustomers() throws CustomerException {
		List<Customer> allCustomers = this.customerRepository.findAll();
		if (allCustomers.isEmpty()) {
			throw new CustomerException("No customers found");
		}
		else {
			return allCustomers;
		}
	}

	@Override
	public Boolean updateAddress(Integer customerId, String newAddress) throws CustomerException {
		Optional<Customer> getCustomer=this.customerRepository.findById(customerId);
		if(getCustomer.isEmpty())throw new CustomerException("This customer is not present in record");
		Customer foundCustomer = getCustomer.get();
		String savedAddress=foundCustomer.getCustomerAddress();
		foundCustomer.getCustomerAddress().replaceAll(savedAddress, newAddress);
		foundCustomer.setCustomerAddress(newAddress);
		this.customerRepository.save(foundCustomer);
		return true;
	}

	@Override
	public Boolean updateMobileNo(Integer customerId, String newMobileNo) throws CustomerException {
		Optional<Customer> getCustomer=this.customerRepository.findById(customerId);
		if(getCustomer.isEmpty())throw new CustomerException("This customer is not present in record");
		Customer foundCustomer = getCustomer.get();
		String savedPhoneNo=foundCustomer.getCustomerPhoneNo();
		foundCustomer.getCustomerPhoneNo().replaceAll(savedPhoneNo, newMobileNo);
		foundCustomer.setCustomerPhoneNo(newMobileNo);
		this.customerRepository.save(foundCustomer);
		return true;
	}

	@Override
	public Boolean updateEmail(Integer customerId, String newEmail) throws CustomerException {
		Optional<Customer> getCustomer=this.customerRepository.findById(customerId);
		if(getCustomer.isEmpty())throw new CustomerException("This customer is not present in record");
		Customer foundCustomer = getCustomer.get();
		String savedAddress=foundCustomer.getCustomerEmail();
		foundCustomer.getCustomerEmail().replaceAll(savedAddress, newEmail);
		foundCustomer.setCustomerEmail(newEmail);
		this.customerRepository.save(foundCustomer);
		return true;
	}

	@Override
	public Boolean updatePassword(String customerEmail, String oldPassword, String newPassword) throws CustomerException {
		Optional<Customer> getCustomer=this.customerRepository.findByCustomerEmailAndPassword(customerEmail, oldPassword);
		if(getCustomer.isEmpty())throw new CustomerException("Invalid emailId or Password");
		Customer foundCustomer = getCustomer.get();
			foundCustomer.getPassword().replaceAll(oldPassword, newPassword);
			foundCustomer.setPassword(newPassword);
			customerRepository.save(foundCustomer);
		return true;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		if (customer == null)
			throw new CustomerException("Customer not updated please fill the mandatory coloumn");
		Optional<Customer> foundCustomer = this.customerRepository.findById(customer.getCustomerId());
		if (foundCustomer.isEmpty())
			throw new CustomerException("Customer not avilable for this id ");
		return this.customerRepository.save(customer);
	}

	@Override
	public Long totalRegisteredCustomer() throws CustomerException {
		return this.customerRepository.count();
	}

}
