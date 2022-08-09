package com.homedecor.app.dto;

public class Authentications {

	private Integer userId;
	private String userPassword;

	public Authentications() {
		super();
	}

	public Authentications(Integer userId, String userPassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
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
