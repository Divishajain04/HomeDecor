package com.example.homedecor.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	private Integer customerId;
	private String customerName;
	private String customerEmail;
	private String password;
	private String customerPhoneNo;
	private String customerAddress;
	private LocalDate creationDate;

	
	@OneToOne
	private Cart cart;

	@OneToMany
	private List<OrderByCustomer> orderByCustomer;

	@OneToOne
	private Wishlist wishlist;

	public Customer() {
		super();
		this.creationDate = LocalDate.now();
	}
	public Customer(Integer customerId, String customerName, String customerEmail, String password,
			String customerPhoneNo, String customerAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.password = password;
		this.customerPhoneNo = customerPhoneNo;
		this.customerAddress = customerAddress;
		this.creationDate = LocalDate.now();
	}
	
	

	public Customer(Integer customerId, String customerName, String customerEmail, String password,
			String customerPhoneNo, String customerAddress, Cart cart, List<OrderByCustomer> orderByCustomer,
			Wishlist wishlist) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.password = password;
		this.customerPhoneNo = customerPhoneNo;
		this.customerAddress = customerAddress;
		this.creationDate = LocalDate.now();
		this.cart = cart;
		this.orderByCustomer = orderByCustomer;
		this.wishlist = wishlist;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<OrderByCustomer> getOrderByCustomer() {
		return orderByCustomer;
	}

	public void setOrderByCustomer(List<OrderByCustomer> orderByCustomer) {
		this.orderByCustomer = orderByCustomer;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPhoneNo=" + customerPhoneNo + ", customerAddress=" + customerAddress
				+ ", creationDate=" + creationDate + "]";
	}


}
