package com.example.homedecor.service;

import java.util.List;

import java.util.Optional;

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
import com.example.homedecor.exception.OrderException;
import com.example.homedecor.exception.ProductException;
import com.example.homedecor.exception.WishlistException;

public interface AdminService {
	
public Boolean addAdmin(Admin admin)throws AdminException;
	
	public Optional<Admin> getAdminById(Integer adminId)throws AdminException;
	
	public Boolean login(Integer adminId, String password)throws AdminException;
	
	public Boolean updatePassword(Integer adminId, String password)throws AdminException;
	
	public List<Customer> getAllCustomer()throws ProductException;
	
	public List<Product> getAllProduct()throws ProductException;
	
	public List<OrderByCustomer> getAllOrder()throws OrderException;
	
	public List<Cart> getAllCart()throws CartException;
	
	public List<Wishlist> getAllWishlist()throws WishlistException;
	
	public List<Category> getAllCategory()throws CategoryException;
	
	public Boolean addProduct(Product product)throws ProductException;
	
	public boolean addCategory(Category category)throws CategoryException;
	
	

}
