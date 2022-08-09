package com.homedecor.app.service;


import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.exception.CustomerException;

public interface AuthenticationService {

	public Boolean adminAuthentication(Authentications authentication) throws AdminException;
	
	public Boolean customerAuthentication(Authentications authentication) throws CustomerException;
}
