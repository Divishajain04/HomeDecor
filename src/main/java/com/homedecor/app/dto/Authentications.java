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
		System.out.println(userId);
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		System.out.println(userPassword);
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
