package com.homedecor.app.dto;

public class Authentications {

	private String userEmail;
	private String userPassword;
	private String updatePassword;

	public Authentications() {
		super();
	}

	public Authentications(String userEmail, String userPassword, String updatePassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.updatePassword = updatePassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUpdatePassword() {
		return updatePassword;
	}

	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}

	
}
