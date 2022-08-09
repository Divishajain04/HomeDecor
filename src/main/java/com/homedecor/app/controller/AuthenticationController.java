package com.homedecor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.service.AuthenticationService;



@RestController
public class AuthenticationController {

	@Autowired 
	private AuthenticationService authenticationService;
	
	@PostMapping("authentication/admin")
	public String loginAdmin(@RequestBody Authentications authentication) throws AdminException {
		this.authenticationService.adminAuthentication(authentication);
		return "Login Successfully";
	}
	
	@PostMapping("authentication/customer")
	public String loginCustomer(@RequestBody Authentications authentication) throws CustomerException {
		this.authenticationService.customerAuthentication(authentication);
		return "Login Successfully";
	}
}
