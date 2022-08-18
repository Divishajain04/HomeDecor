package com.homedecor.app.dto;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Customer {

	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customerId;

	@NotNull(message = "Please provide name")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Za-z ]*", message = "Special characters and digits are not allowed.")
	private String customerName;

	@NotNull(message = "Please provide email")
	@Email
	private String customerEmail;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Please provide password")
	@Size(min = 8, max = 20, message = "Password must be minimum 8 and maximum 20 characters")
	private String password;

	@NotNull(message = "Please provide phone number")
	@Pattern(regexp = "[0-9]{10}", message = "Phone munber must be 10 digits[0-9].")
	private String customerPhoneNo;

	@NotNull(message = "Please provide address")
	private String customerAddress;

	private LocalDate creationDate;

	@OneToOne
	private Wallet wallet;

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
			String customerPhoneNo, String customerAddress, Cart cart, List<OrderByCustomer> orderByCustomer,
			Wishlist wishlist, Wallet wallet) {
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
		this.wallet = wallet;
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

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
