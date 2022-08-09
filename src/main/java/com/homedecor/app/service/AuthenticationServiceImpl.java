package com.homedecor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AdminService adminService;

	@Override
	public Boolean userAuthentication(Authentications authentication) throws AdminException {
		Integer adminId = authentication.getUserId();
		String adminPassword = authentication.getUserPassword();
		this.adminService.login(adminId, adminPassword);
		return true;
	}

}
