package com.homedecor.app.dto;

public class Authentication {

	private Integer userId;
	private String userPassword;

	public Authentication() {
		super();
	}

	public Authentication(Integer userId, String userPassword) {
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

	@Override
	public String toString() {
		return "Authentication [userId=" + userId + ", userPassword=" + userPassword + "]";
	}

}
