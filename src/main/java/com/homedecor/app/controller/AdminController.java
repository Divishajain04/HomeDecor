package com.homedecor.app.controller;

import java.util.Optional;

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
	public String addAdmin(@RequestBody Admin admin) throws AdminException {
		try {
			this.adminService.addAdmin(admin);
		} catch (AdminException e) {
			throw new AdminException(e.getMessage());
		}
		return "Admin added Successfully";
	}

	@GetMapping("admin/{adminId}")
	public Optional<Admin> getAdminById(@PathVariable("adminId") Integer adminId) throws AdminException {
		Optional<Admin> foundAdmin;
		try {
			foundAdmin = this.adminService.getAdminById(adminId);
		} catch (AdminException e) {
			throw new AdminException(e.getMessage());
		}
		return foundAdmin;
	}

	@GetMapping("admin/{adminId}/{password}/")
	public Boolean adminLogin(@PathVariable("adminId") Integer adminId, @PathVariable("password") String password)
			throws AdminException {
		Boolean isLogin = false;
		try {
			isLogin = this.adminService.login(adminId, password);
		} catch (AdminException e) {
			throw new AdminException(e.getMessage());
		}
		return isLogin;
	}

	@GetMapping("admin/{adminId}/{oldPassword}/{newPassword}")
	public String updatePassword(@PathVariable("adminId") Integer adminId,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword)
			throws AdminException {
		try {
			this.adminService.updatePassword(adminId, oldPassword, newPassword);
		} catch (AdminException e) {
			throw new AdminException(e.getMessage());
		}
		return "Password updated Successfully";
	}

	@DeleteMapping("admin/{adminId}")
	public String deleteAdminById(@PathVariable("adminId") Integer adminId) throws AdminException {
		try {
			this.adminService.deleteAdminById(adminId);
		} catch (AdminException e) {
			throw new AdminException(e.getMessage());
		}
		return "Admin deleted SuccessFully";
	}

}
