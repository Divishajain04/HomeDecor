package com.homedecor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CustomerException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public Boolean adminAuthentication(Authentications authentication) throws AdminException {
		String adminEmailId=authentication.getUserEmail();
		String adminPassword = authentication.getUserPassword();
		this.adminService.login(adminEmailId, adminPassword);
		return true;
	}

	@Override
	public Boolean customerAuthentication(Authentications authentication) throws CustomerException {
		String customerEmailId=authentication.getUserEmail();
		String customerPassword = authentication.getUserPassword();
		this.customerService.login(customerEmailId, customerPassword);
		return true;
	}

	@Override
	public Boolean updateAdminPassword(Authentications authentication) throws AdminException {
		String adminEmailId=authentication.getUserEmail();
		String adminPassword = authentication.getUserPassword();
		String newPassword=authentication.getUpdatePassword();
		this.adminService.updatePassword(adminEmailId, adminPassword, newPassword);
		return true;
	}

	@Override
	public Boolean updateCustomerPassword(Authentications authentication) throws CustomerException {
		String customerEmailId=authentication.getUserEmail();
		String customerPassword = authentication.getUserPassword();
		String newPassword=authentication.getUpdatePassword();
		this.customerService.updatePassword(customerEmailId, customerPassword, newPassword);
		return true;
	}

}
