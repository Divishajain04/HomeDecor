package com.homedecor.app.service;


import java.util.Optional;

import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;


public interface AdminService {

	public Boolean addAdmin(Admin admin) throws AdminException;

	public Optional<Admin> getAdminById(Integer adminId) throws AdminException;

	public Boolean login(String adminEmailId, String password) throws AdminException;

	Boolean updatePassword(String adminEmailId, String oldPassword, String newPassword) throws AdminException;
	
	public Boolean deleteAdminById(Integer adminId)throws AdminException;

}
