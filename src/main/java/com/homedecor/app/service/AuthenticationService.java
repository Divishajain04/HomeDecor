package com.homedecor.app.service;


import com.homedecor.app.dto.Authentications;
import com.homedecor.app.exception.AdminException;

public interface AuthenticationService {

	public Boolean userAuthentication(Authentications authentication) throws AdminException;
}
