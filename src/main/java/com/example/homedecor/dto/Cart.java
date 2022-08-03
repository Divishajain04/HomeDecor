package com.example.homedecor.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	private Integer cartId;

	@OneToOne
	private Wishlist wishlist;

	@OneToOne
	private OrderByCustomer orderByCustomer;

	@ManyToMany
	private List<Product> product;

	public Cart() {
		super();
	}

	public Cart(Integer cartId, Wishlist wishlist, OrderByCustomer orderByCustomer, List<Product> product) {
		super();
		this.cartId = cartId;
		this.wishlist = wishlist;
		this.orderByCustomer = orderByCustomer;
		this.product = product;
	}



	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
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

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", wishlist=" + wishlist + ", orderByCustomer=" + orderByCustomer
				+ ", product=" + product + "]";
	}

}
