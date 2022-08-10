package com.homedecor.app.dto;

public class Authentications {

	private Integer userId;
	private String userPassword;
	private String updatePassword;

	public Authentications() {
		super();
	}

	public Authentications(Integer userId, String userPassword, String updatePassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.updatePassword = updatePassword;
	}

	public String getUpdatePassword() {
		return updatePassword;
	}

	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
