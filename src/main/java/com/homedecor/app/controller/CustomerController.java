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

/************************************************************************************
 *          @author          Divisha Jain
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/************************************************************************************
	 * Method: addCustomer
     * Description: 				 - To add or create the customer in application
     * 
     * @Object customer              - Customer's object
	 * @returns String               - Customer added Successfully
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("customer")
	public String addCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		this.customerService.addCustomer(customer);
		return "Customer added Successfully";
	}
	
	/************************************************************************************
	 * Method: getCustomerById
     * Description: 				  -	To get the customer by their Customer Id.
     * 
     * @Param customerId              - customerId
	 * @returns Optional<Customer>    - foundCustomer
     * Created By                     - Divisha Jain
     * Created Date                   - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@GetMapping("customer/{customerid}")
	public Optional<Customer> getCustomerById(@PathVariable("customerid") Integer customerId) throws CustomerException {
		return this.customerService.getCustomerById(customerId);
	}
	
	/************************************************************************************
	 * Method: 						 - getAllCustomers
     * Description:                  - To get all the customers.
     * 
	 * @returns List<Customer>       - foundCustomers
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@GetMapping("customers")
	public List<Customer> getAllCustomers() throws CustomerException {
		return this.customerService.findAllCustomers();
	}
	
	/************************************************************************************
	 * Method: updateCustomer
     * Description: 				 - To update the customer details.
     * 
     * @Object customer              - Customer's object
	 * @returns Customer             - updatedCustomer
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@PutMapping("customer")
	public Customer updateCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		return this.customerService.updateCustomer(customer);
	}

	/************************************************************************************
	 * Method: updateCustomerEmail
     * Description: 				 - Update customer email.
     * 
     * @Param loginId                - Customer's Id
     * @Param newEmail               - Customer's new Email
	 * @returns String               - Email updated Successfully
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 //enchancement
	 ************************************************************************************/

	@PatchMapping("customer/{loginid}/{newemail}")
	public String updateEmail(@PathVariable("loginid") Integer loginId, @PathVariable("newemail") String newEmail)
			throws CustomerException {
		this.customerService.updateEmail(loginId, newEmail);
		return "Email updated Successfully";
	}

	/************************************************************************************
	 * Method: updatePhone
     * Description:                  - Update customer Phone Number
     * 
     * @Param loginId                - Customer's Id
     * @Param newPhone               - Customer's new Phone number
	 * @returns String               - Mobile no. updated Successfully
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PatchMapping("customer/phone/{loginid}/{newphone}")
	public String updatePhone(@PathVariable("loginid") Integer loginId, @PathVariable("newphone") String newPhone)
			throws CustomerException {
		this.customerService.updateMobileNo(loginId, newPhone);
		return "Mobile no. updated Successfully";
	}

	/************************************************************************************
	 * Method: updateAddress
     * Description:                  - To update new address for the customer
     * 
     * @Param loginId                - Customer's Id
     * @Param newAddress             - Customer's new address
	 * @returns String               - Address updated Successfully
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PatchMapping("customer/address/{loginid}/{newaddress}/")
	public String updateAddress(@PathVariable("loginId") Integer loginId, @PathVariable("newaddress") String newAddress)
			throws CustomerException {
		this.customerService.updateAddress(loginId, newAddress);
		return "Address updated Successfully";
	}

	/************************************************************************************
	 * Method: deleteCustomerById
     * Description: 				  - To delete the customer
     * 
     * @Param customerId              - Customer's Id
	 * @returns String                - Customer deleted Successfully
     * Created By                     - Divisha Jain
     * Created Date                   - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@DeleteMapping("customer/{customerid}")
	public String deleteCustomerById(@PathVariable("customerid") Integer customerId) throws CustomerException {
		this.customerService.deleteCustomer(customerId);
		return "Customer deleted Successfully";
	}
	
	/************************************************************************************
	 * Method: countRegisteredCustomer
     * Description:					  - To count all the customer that is present in application
     * 
   	 * @returns long                  - count of total no. of registered customers
     * Created By                     - Divisha Jain
     * Created Date                   - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("count/customers")
	public long countRegisteredCustomer() throws CustomerException{
		return this.customerService.totalRegisteredCustomer();
	}
}
