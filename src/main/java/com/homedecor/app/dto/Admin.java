package com.homedecor.app.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Admin {

	@Id
	private Integer adminId;
	
	@NotNull(message="Please provide admin name")
	@Size(min = 3 , max = 30)
	@Pattern(regexp="[A-Za-z ]*",message="Special characters and digits are not allowed.")
	private String adminName;
	
	@NotNull(message = "Please provide password")
	@Size(min = 8 , max = 20 ,message = "Password must be minimum 8 and maximum 20 characters")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String adminPassword;

	public Admin() {
		super();
	}

	public Admin(Integer adminId, String adminName, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	

}
