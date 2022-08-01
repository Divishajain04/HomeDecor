package com.example.homedecor.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homedecor.dao.AdminRepository;
import com.example.homedecor.dao.CategoryRepository;
import com.example.homedecor.dao.ProductRepository;
import com.example.homedecor.dto.Admin;
import com.example.homedecor.dto.Cart;
import com.example.homedecor.dto.Category;
import com.example.homedecor.dto.Customer;
import com.example.homedecor.dto.OrderByCustomer;
import com.example.homedecor.dto.Product;
import com.example.homedecor.dto.Wishlist;
import com.example.homedecor.exception.AdminException;
import com.example.homedecor.exception.CartException;
import com.example.homedecor.exception.CategoryException;
import com.example.homedecor.exception.CustomerException;
import com.example.homedecor.exception.OrderException;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.exception.WishlistException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepositary;

	@Override
	public Boolean addAdmin(Admin admin) throws AdminException {
		if (admin == null) {
			throw new AdminException("Admin not added");
		}
		Optional<Admin> addAdmin = this.adminRepositary.findById(admin.getAdminID());
		if (addAdmin.isPresent()) {
			throw new AdminException(" This Admin Id is already present! Try with new");
		} else {
			this.adminRepositary.save(admin);
		}
		return true;
	}

	@Override
	public Optional<Admin> getAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdminById = this.adminRepositary.findById(adminId);
		if (foundAdminById.isEmpty()) {
			throw new AdminException("Admin ID is not present in the record");
		}
		return foundAdminById;
	}

	@Override
	public Boolean login(Integer adminId, String password) throws AdminException {
		Boolean isLoginBoolean = false;
		Optional<Admin> foundAdmin = this.adminRepositary.findByAdminIDAndAdminPassword(adminId, password);
		if (foundAdmin.isEmpty()) {
			throw new AdminException("Invalid Id or password");
		} else {
			isLoginBoolean = true;
		}
		return isLoginBoolean;
	}

	@Override
	public Boolean updatePassword(Integer adminId, String oldPassword, String newPassword) throws AdminException {
		Admin foundCAdmin  = this.adminRepositary.findById(adminId).get();
		String savedPassword = foundCAdmin.getAdminPassword();
		Boolean isLogin = false;
		if (savedPassword.compareTo(oldPassword) == 0) {
			foundCAdmin.getAdminPassword().replaceAll(oldPassword, newPassword);
			foundCAdmin.setAdminPassword(newPassword);
			adminRepositary.save(foundCAdmin);
			isLogin=true;
		} else {
			throw new AdminException("Old password dosen't match");
		}
		return isLogin;
	}

	@Override
	public Boolean deleteAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdmin = this.adminRepositary.findById(adminId);
		if (foundAdmin.isEmpty())throw new AdminException("Admin not exist for this Id "+adminId);
		this.adminRepositary.deleteById(adminId);
		return true;
	}

}
