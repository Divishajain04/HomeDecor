package com.homedecor.app.dto;



import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.v3.oas.annotations.Parameter;

@Entity
public class Admin {

	@Id
	private Integer adminID;
	private String adminName;
	//@EncryptedValue("${adminPassword.secret}")
	//@Value("${db.adminPassword}")
	//@Parameter(schema=@Schema(type="string",format="password"))
	private String adminPassword;

	public Admin() {
		super();
	}

	public Admin(Integer adminID, String adminName, String adminPassword) {
		super();
		this.adminID = adminID;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
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
