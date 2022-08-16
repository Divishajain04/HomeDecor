package com.homedecor.app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
	private Integer productId;

	@NotNull(message = "Please provide product name")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Za-z0-9 ]*", message = "Special characters are not allowed.")
	private String productName;

	@NotNull(message = "Please provide product description")
	@Size(max = 500)
	private String productDescription;

	@NotNull(message = "Please provide product price")
	private Double productPrice;

	@NotNull(message = "Please provide product quantity")
	private Integer quantity;

	@ManyToOne
	private Category category;

	public Product() {
		super();
	}

	public Product(Integer productId, String productName, String productDescription, Double productPrice,
			Integer quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
