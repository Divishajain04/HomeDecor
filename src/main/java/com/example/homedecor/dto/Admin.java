package com.example.homedecor.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {

	@Id
	private Integer adminID;
	private String adminName;
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

	/*
	 * @Override public String toString() { return "Admin [adminID=" + adminID +
	 * ", adminName=" + adminName + ", adminPassword=" + adminPassword + "]"; }
	 */

}
