package com.homedecor.app.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.WalletRepository;
import com.homedecor.app.dao.WishlistRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Wallet;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CustomerException;

/************************************************************************************
 * @author 		-   Divisha Jain 
 * Description: -	 It is a service class that provides the
 * 		        	services for adding a new customer, delete customer, information of
 *         			customer by Id, details of all customer, count of registered
 *         			customer.
 * @version       - 1.0        
 ************************************************************************************/

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private WishlistRepository wishlistRepository;

	/************************************************************************************
	 * Method:			    	- 	Add Customer
	 *Description: 				-	Registered Customer in the Home Decor Application.
	 * @object Customer 		-	Customer detail
	 * @returns Boolean  		-	true, if customer registered otherwise throws Customer Exception
	 * @throws CustomerException- 	It is raised due to customer details are invalid,
	 *                           	or customer id is not present.                         
	 * Created By 	 			-	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public boolean addCustomer(Customer customer) throws CustomerException {
		if (customer == null) {
			throw new CustomerException("Customer not added. please fill the mandatory feilds");
		}
		Optional<Customer> foundCustomer = this.customerRepository.findById(customer.getCustomerId());
		if (foundCustomer.isPresent()) {
			throw new CustomerException("Customer already exist");
		} else {
			Cart cart = this.cartRepository.save(new Cart(customer.getCustomerId()));
			Wishlist wishlist = this.wishlistRepository.save(new Wishlist(customer.getCustomerId()));
			Wallet wallet=this.walletRepository.save(new Wallet(customer.getCustomerId()));
			customer.setWallet(wallet);
			customer.setCart(cart);
			customer.setWishlist(wishlist);
			this.customerRepository.save(customer);
		}
		return true;
	}

	/************************************************************************************
	 * Method:					 -	Delete Customer
	 * Description: 			 -	to delete the customer from record. When customer record is delete 
	 * 							 -	then cart and wishlist is also delete.
	 * @param customerId  		 -	Customer Id
	 * @returns Optional  		 -	Present, if delete otherwise throws CustomerException
	 * @throws Customer Exception- It is raised due to invalid customer Id.
	 * Created By 				 -	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public boolean deleteCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> foundCustomer = this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) {
			throw new CustomerException("This customer is not present in record");
		}

		this.customerRepository.deleteById(customerId);
		this.cartRepository.deleteById(customerId);
		this.wishlistRepository.deleteById(customerId);
		this.walletRepository.deleteById(customerId);
		return true;
	}

	/************************************************************************************
	 * Method:                       -	getCustomerById
	 * Description: 				 -	To get the detail of particular customer by customer Id.
	 * @param customerId  			 -	CustomerId
	 * @returns Optional  			 -	Present, if detail exist otherwise throws Customer Exception
	 * @throws CustomerException  	 -	It is raised due to invalid customer Id.
	 * Created By  					 -	Divisha Jain
	 ************************************************************************************/
	@Override
	public Optional<Customer> getCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> foundCustomer = this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) {
			throw new CustomerException("This customer is not present in record");
		}
		return foundCustomer;
	}

	/************************************************************************************
	 * Method: 					 -	login
	 * Description: 			 -	To login into the application. After login customer can perform many
	 *  						 -	task like product add and delete from cart and wishlist.
	 * @param customerEmail  	 -	CustomerEmail
	 * @param password  		 -	password
	 * @returns Boolean  		 -	true, if detail exist otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised due to invalid customer email and password.
	 * Created By  				 -	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public Boolean login(String customerEmail, String password) throws CustomerException {
		Boolean isCustomerLogin = false;
		Optional<Customer> loginCustomer = this.customerRepository.findByCustomerEmailAndPassword(customerEmail,
				password);
		if (loginCustomer.isEmpty()) {
			throw new CustomerException("Invalid Id or Password");
		} else {
			isCustomerLogin = true;
		}
		return isCustomerLogin;
	}
	

	/************************************************************************************
	 * Method: 					 -	findAllCustomers
	 * Description: 			 -	To get the details of all customer.
	 * @returns List - 			 -	allCustomers, if details exist otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised if the database is empty or no one registered
	 * 								in the application.
	 * Created By  				 -	Divisha Jain
	 ************************************************************************************/
	@Override
	public List<Customer> findAllCustomers() throws CustomerException {
		List<Customer> allCustomers = this.customerRepository.findAll();
		if (allCustomers.isEmpty()) {
			throw new CustomerException("No customers found");
		} else {
			return allCustomers;
		}
	}
	
	/************************************************************************************
	 * Method 					 -	updateAddress
	 * Description	 			 -	To update the customer's address.
	 * @returns List			 -	allCustomers, if details exist otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised if the database is empty or no one registered
	 * 								in the application.
	 *Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	@Override
	public Boolean updateAddress(Integer customerId, String newAddress) throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(customerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("This customer is not present in record");
		Customer foundCustomer = getCustomer.get();
	

		foundCustomer.setCustomerAddress(newAddress);
		this.customerRepository.save(foundCustomer);
		return true;
	}
	
	/************************************************************************************
	 * Method 					 -	updateMobileNo
	 * Description	 			 -	To update the customer's mobile number.
	 * @returns Boolean			 -	true, if mobile number update otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised if customer's Id is not present in the database.
	 * Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public Boolean updateMobileNo(Integer customerId, String newMobileNo) throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(customerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("This customer is not present in record");
		Customer foundCustomer = getCustomer.get();
	
	
		foundCustomer.setCustomerPhoneNo(newMobileNo);
		this.customerRepository.save(foundCustomer);
		return true;
	}

	/************************************************************************************
	 * Method 					 -	updateEmail
	 * Description	 			 -	To update the customer's email.
	 * @returns Boolean			 -	true, if email update otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised if customer's Id is not present in the database.
	 * Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public Boolean updateEmail(Integer customerId, String newEmail) throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findById(customerId);
		if (getCustomer.isEmpty())
			throw new CustomerException("This customer is not present in record");
		Customer foundCustomer = getCustomer.get();
	
		foundCustomer.setCustomerEmail(newEmail);
		this.customerRepository.save(foundCustomer);
		return true;
	}
	
	/************************************************************************************
	 * Method 					 -	updatePassword
	 * Description	 			 -	To update the customer's password.
	 * @returns Boolean			 -	true, if password update otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised if customer's Id is not present in the database.
	 * Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	

	@Override
	public Boolean updatePassword(String customerEmail, String oldPassword, String newPassword)
			throws CustomerException {
		Optional<Customer> getCustomer = this.customerRepository.findByCustomerEmailAndPassword(customerEmail,
				oldPassword);
		if (getCustomer.isEmpty())
			throw new CustomerException("Invalid emailId or Password");
		Customer foundCustomer = getCustomer.get();
		
		foundCustomer.setPassword(newPassword);
		customerRepository.save(foundCustomer);
		return true;
	}

	/************************************************************************************
	 * Method 					 -	updateCustomer
	 * Description	 			 -	To update the customer's detail.
	 * @returns Customer		 -	customer, if detail update otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised if customer's Id is not present in the database.
	 * Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		if (customer == null)
			throw new CustomerException("Customer not updated please fill the mandatory coloumn");
		Optional<Customer> foundCustomer = this.customerRepository.findById(customer.getCustomerId());
		if (foundCustomer.isEmpty())
			throw new CustomerException("Customer not avilable for this id ");
		return this.customerRepository.save(customer);
	}

	/************************************************************************************
	 * Method 					 -	totalRegisteredCustomer
	 * Description	 			 -	To count all registered customer.
	 * @returns Long			 -	long,if more than one number are present in the database otherwise 
	 * 								throws Customer Exception
	 * @throws CustomerException - 	It is raised if nothing is present in the record
	 * Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	
	@Override
	public Long totalRegisteredCustomer() throws CustomerException {
		return this.customerRepository.count();
	}

}
