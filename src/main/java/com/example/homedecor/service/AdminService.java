package com.example.homedecor.service;


import java.util.Optional;

import com.example.homedecor.dto.Admin;

import com.example.homedecor.exception.AdminException;


public interface AdminService {

	public Boolean addAdmin(Admin admin) throws AdminException;

	public Optional<Admin> getAdminById(Integer adminId) throws AdminException;

	public Boolean login(Integer adminId, String password) throws AdminException;

	Boolean updatePassword(Integer adminId, String oldPassword, String newPassword) throws AdminException;
	
	public Boolean deleteAdminById(Integer adminId)throws AdminException;

}
