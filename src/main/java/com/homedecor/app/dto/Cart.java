package com.homedecor.app.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	private Integer cartId;

	@OneToOne
	private OrderByCustomer orderByCustomer;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> product;

	public Cart() {
		super();
	}

	public Cart(Integer cartId) {
		super();
		this.cartId = cartId;
	}

	public Cart(Integer cartId, OrderByCustomer orderByCustomer, List<Product> product) {
		super();
		this.cartId = cartId;
		this.orderByCustomer = orderByCustomer;
		this.product = product;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public OrderByCustomer getOrderByCustomer() {
		return orderByCustomer;
	}

	public void setOrderByCustomer(OrderByCustomer orderByCustomer) {
		this.orderByCustomer = orderByCustomer;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
