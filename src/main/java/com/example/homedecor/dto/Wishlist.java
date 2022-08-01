package com.example.homedecor.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Wishlist {

	@Id
	private Integer wishlistId;

	@ManyToMany
	private List<Product> product;

	public Wishlist() {
		super();
	}
	
	
	public Wishlist(Integer wishlistId, List<Product> product) {
		super();
		this.wishlistId = wishlistId;
		this.product = product;
	}
	
	
	
	

	public Integer getWishlistId() {
		return wishlistId;
	}

	
	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Wishlist [wishlistId=" + wishlistId + ", product=" + product + "]";
	}

}
