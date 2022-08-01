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
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepositary;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Boolean addAdmin(Admin admin) throws AdminException {
		if (admin == null) {
			throw new AdminException("Admin not added");
		}
		Optional<Admin> addAdmin = this.adminRepositary.findById(admin.getAdminID());
		if (addAdmin.isPresent()) {
			throw new AdminException(" This Admin Id is already present! Try with new");
		}
		else {
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
		}
		else {
			isLoginBoolean = true;
		}
		return isLoginBoolean;
	}

	@Override
	public Boolean updatePassword(Integer adminId, String password) throws AdminException {
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return null;
	}

	@Override
	public List<Product> getAllProduct() throws ProductException {
		List<Product> products = this.productRepository.findAll();
		if (products.isEmpty()) {
			throw new ProductException("Products are not present in the record");
		}
		return products;
	}

	@Override
	public List<OrderByCustomer> getAllOrder() throws OrderException {
		return null;
	}

	@Override
	public List<Cart> getAllCart() throws CartException {
		return null;
	}

	@Override
	public List<Wishlist> getAllWishlist() throws WishlistException {
		return null;
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> resultList = this.categoryRepository.findAll();
		if (resultList.isEmpty()) {
			throw new CategoryException("Category is not present in the database");
		}
		return resultList;
	}
	
	public boolean addCategory(Category category)throws CategoryException{
		if (category == null) {
			throw new CategoryException("Category not added");
		}
		Optional<Category> addCategoryResult = this.categoryRepository.findById(category.getCategoryId());
		if (addCategoryResult.isPresent()) {
			throw new CategoryException("Category Id is already present in the record");
		}
		else {
		this.categoryRepository.save(category);
		}
		return true;
	}

	@Override
	public Boolean addProduct(Product product) throws ProductException {
		if (product == null) {
			throw new ProductException("Product not added");
		}
		Optional<Product> addProductRecord = this.productRepository.findById(product.getProductId());
		if (addProductRecord.isPresent()) {
			throw new ProductException("Product Id is already present in the record");
		}
		else {
			this.productRepository.save(product);
		}
		return true;
	}

}
