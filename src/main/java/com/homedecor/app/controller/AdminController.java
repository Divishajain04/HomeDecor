package com.homedecor.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("admin")
	public String addAdmin(@Valid @RequestBody Admin admin) throws AdminException {
		this.adminService.addAdmin(admin);
		return "Admin added Successfully";
	}

	@GetMapping("admin/{adminId}")
	public Optional<Admin> getAdminById(@PathVariable("adminId") Integer adminId) throws AdminException {
		Optional<Admin> foundAdmin = this.adminService.getAdminById(adminId);
		return foundAdmin;
	}
/*
	@GetMapping("adminLogin/{adminId}/{adminPassword}")
	public Boolean adminLogin(@PathVariable Integer adminId, @PathVariable String adminPassword) throws AdminException {
		Boolean isLogin = this.adminService.login(adminId, adminPassword);
		return isLogin;
	}
*/
	@GetMapping("admin/{adminId}/{oldPassword}/{newPassword}")
	public String updatePassword(@PathVariable("adminId") Integer adminId,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword)
			throws AdminException {
		this.adminService.updatePassword(adminId, oldPassword, newPassword);
		return "Password updated Successfully";
	}

	@DeleteMapping("admin/{adminId}")
	public String deleteAdminById(@PathVariable("adminId") Integer adminId) throws AdminException {
		this.adminService.deleteAdminById(adminId);
		return "Admin deleted SuccessFully";
	}

}
