package com.homedecor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.service.AuthenticationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AuthenticationController {

	@Autowired 
	private AuthenticationService authenticationService;
	
	@PostMapping("authentication")
	public String loginAdmin(@RequestBody Authentications authentication) throws AdminException {
		this.authenticationService.userAuthentication(authentication);
		return "Login Successfully";
	}
}
