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
		Integer adminId = authentication.getUserId();
		String adminPassword = authentication.getUserPassword();
		this.adminService.login(adminId, adminPassword);
		return true;
	}

	@Override
	public Boolean customerAuthentication(Authentications authentication) throws CustomerException {
		Integer customerId = authentication.getUserId();
		String customerPassword = authentication.getUserPassword();
		this.customerService.login(customerId, customerPassword);
		return true;
	}

	@Override
	public Boolean updateAdminPassword(Authentications authentication) throws AdminException {
		Integer adminId = authentication.getUserId();
		String adminPassword = authentication.getUserPassword();
		String newPassword=authentication.getUpdatePassword();
		this.adminService.updatePassword(adminId, adminPassword, newPassword);
		return true;
	}

	@Override
	public Boolean updateCustomerPassword(Authentications authentication) throws CustomerException {
		Integer customerId = authentication.getUserId();
		String customerPassword = authentication.getUserPassword();
		String newPassword=authentication.getUpdatePassword();
		this.customerService.updatePassword(customerId, customerPassword, newPassword);
		return true;
	}

}
