package com.homedecor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.service.AuthenticationService;

/************************************************************************************
 *          @author          Lucky Rathore
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/



@RestController
public class AuthenticationController {

	@Autowired 
	private AuthenticationService authenticationService;
	
	
	/************************************************************************************
	 * Method: loginAdmin
     * Description: To handle a Http POST request
     * 
     * @Object authentication        - to authenticate admin logging details
	 * @returns String               - Login Successfully
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("authentication/admin")
	public String loginAdmin(@RequestBody Authentications authentication) throws AdminException {
		this.authenticationService.adminAuthentication(authentication);
		return "Login Successfully";
	}
	/************************************************************************************
	 * Method: loginCustomer
     * Description: To handle a Http POST request
     * 
     * @Object authentication        - to authenticate customer logging details
	 * @returns String               - Login Successfully
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("authentication/customer")
	public String loginCustomer(@RequestBody Authentications authentication) throws CustomerException {
		this.authenticationService.customerAuthentication(authentication);
		return "Login Successfully";
	}
	
	/************************************************************************************
	 * Method: updateAdminPassword
     * Description: To handle a Http PUT request
     * 
     * @Object authentication        - to update admin password
	 * @returns String               - Password Updated Successfully
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PutMapping("authentication/admin")
	public String updateAdminPassword(@RequestBody Authentications authentication) throws AdminException {
		this.authenticationService.updateAdminPassword(authentication);
		return "Password Updated Successfully";
	}
	
	/************************************************************************************
	 * Method: updateCustomerPassword
     * Description: To handle a Http PUT request
     * 
     * @Object authentication        - to update customer password
	 * @returns String               - Password Updated Successfully
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PutMapping("authentication/customer")
	public String updateCustomerPassword(@RequestBody Authentications authentication) throws CustomerException {
		this.authenticationService.updateCustomerPassword(authentication);
		return "Password Updated Successfully";
	}
}
