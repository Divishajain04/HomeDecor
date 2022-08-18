package com.homedecor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CustomerException;

/************************************************************************************
 *          @author          Lucky Rathore
 *          Description      It is a service class that provides login services for admin and customer  and updating their login password.             
 *         Version           1.
 *         Created Date      16-AUG-2022
 ************************************************************************************/


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;

	/************************************************************************************
	 * Method: adminAuthentication
     * Description: To login into an existing admin account
     * 
     * @Object                     - authentication
	 * @param adminEmailId         - admin's Email 
	 * @param password             - admin's password
	 * @returns Boolean            - true, if Admin logged in successfully otherwise throws AdminException 
	 * @throws AdminException      - It is raised due to incorrect Admin Id or password                                                      
     * Created By                  - Lucky Rathore
     * Created Date                - 16-AUG-2022                         
	 
	 ************************************************************************************/

	
	@Override
	public Boolean adminAuthentication(Authentications authentication) throws AdminException {
		String adminEmailId=authentication.getUserEmail();
		String adminPassword = authentication.getUserPassword();
		this.adminService.login(adminEmailId, adminPassword);
		return true;
	}
	
	/************************************************************************************
	 * Method: customerAuthentication
	 * Description: login service for existing customer
	 *  	
	 * @Object                   -  authentication					 
	 * @param customerEmail  	 -	CustomerEmail
	 * @param password  		 -	password
	 * @returns Boolean  		 -	true, if detail exist otherwise throws Customer Exception
	 * @throws CustomerException - 	It is raised due to invalid customer email and password.
	 * Created By  				 -	Lucky Rathore
	 * Created Date              -  16-AUG-2022
	 ************************************************************************************/
	

	@Override
	public Boolean customerAuthentication(Authentications authentication) throws CustomerException {
		String customerEmailId=authentication.getUserEmail();
		String customerPassword = authentication.getUserPassword();
		this.customerService.login(customerEmailId, customerPassword);
		return true;
	}
	 
	/************************************************************************************
	 * Method: updateAdminPassword
     * Description: To update an admin account password
     * 
	 * @param adminEmailId      - Admin's EmailId
	 * @param userPassword      - old adminPassword
	 * @param newPassword       - new password to be updated
	 * @returns Boolean         - true, if password for that adminId updated successfully otherwise throws AdminException
	 * @throws AdminException   - It is raised if we provide invalid admin Email or password                                                        
     * Created By               - Lucky Rathore
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@Override
	public Boolean updateAdminPassword(Authentications authentication) throws AdminException {
		String adminEmailId=authentication.getUserEmail();
		String adminPassword = authentication.getUserPassword();
		String newPassword=authentication.getUpdatePassword();
		this.adminService.updatePassword(adminEmailId, adminPassword, newPassword);
		return true;
	}
	
	/************************************************************************************
	 * Method: updateCustomerPassword
     * Description: To update an customer account password
     * 
	 * @param customerEmailId   - Customer's EmailId
	 * @param oldPassword       - old customerPassword
	 * @param newPassword       - new password to be updated
	 * @returns Boolean         - true, if password for that customerId updated successfully otherwise throws CustomerException
	 * @throws AdminException   - It is raised if we provide invalid customer Email or password                                                        
     * Created By               - Lucky Rathore
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@Override
	public Boolean updateCustomerPassword(Authentications authentication) throws CustomerException {
		String customerEmailId=authentication.getUserEmail();
		String customerPassword = authentication.getUserPassword();
		String newPassword=authentication.getUpdatePassword();
		this.customerService.updatePassword(customerEmailId, customerPassword, newPassword);
		return true;
	}

}
