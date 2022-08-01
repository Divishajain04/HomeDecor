package com.example.homedecor.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.homedecor.dto.Admin;
import com.example.homedecor.dto.Category;
import com.example.homedecor.dto.Wishlist;
import com.example.homedecor.exception.AdminException;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.WishlistException;
import com.example.homedecor.service.AdminService;
import com.example.homedecor.service.CategoryServiceImpl;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("admin")
	public String addAdmin(@RequestBody Admin admin) throws AdminException {
		this.adminService.addAdmin(admin);
		return "Admin added Successfully";
	}
	
	@GetMapping("admin/{adminId}")
	public Optional<Admin> getAdminById(@PathVariable ("adminId") Integer adminId) throws AdminException {
		return this.adminService.getAdminById(adminId);
	}
	
//	@PostMapping("product")
//	public String addProduct(@RequestBody Product product) {
//		this.addProduct(product);
//		return "Product added successfully";
//	}
	
//	@PostMapping("category")
//	public String addCategory(@RequestBody Category category) throws CategoryException {
//		this.adminService.addCategory(category);
//		return "Category added Successfully";
//	}
	
//	@GetMapping("product/")
//	public List<Product> getAllProduct() throws ProductException{
//		
//		return this.adminService.getAllProduct();
//	}
	
//	@GetMapping("category/")
//	public List<Category> getAllCategory() throws CategoryException{
//		
//		return this.adminService.getAllCategory();
//	}
	
	
	@GetMapping("admin/{adminId}/{adminPassword}")
	public Boolean adminLogin(@PathVariable ("adminId") Integer adminId, @PathVariable("adminPassword") String adminPassword) throws AdminException
	{
		return this.adminService.login(adminId, adminPassword);
	}
	
}
