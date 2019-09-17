package com.capgemini.go.model;

import java.io.*;

public class User implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activeStatus ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userCategory;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userMail == null) ? 0 : userMail.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + (int) (userNumber ^ (userNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (activeStatus != other.activeStatus)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userCategory != other.userCategory)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userMail == null) {
			if (other.userMail != null)
				return false;
		} else if (!userMail.equals(other.userMail))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userNumber != other.userNumber)
			return false;
		return true;
	}

	private String userId;
	private String userName;
	private String userMail;
	private long userNumber;
	private boolean activeStatus;
	private String password;
	/*
	 * 1 = Go Admin 2 = Sales Representative; 3 = Retailer 4 = Product Master
	 */
	private int userCategory;

	public int getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(int userCategory) {
		this.userCategory = userCategory;

	}

	private static final long serialVersionUID = 7551999649936522523L;

	// Constructor
	public User(String userId, String userName, String userMail, long userNumber, boolean activeStatus, String password,
			int userCategory) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userNumber = userNumber;
		this.activeStatus = activeStatus;
		this.password = password;
		this.userCategory = userCategory;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public long getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(long userNumber) {
		this.userNumber = userNumber;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userMail=" + userMail + ", userNumber="
				+ userNumber + ", activeStatus=" + activeStatus + ", password=" + password + ", userCategory="
				+ userCategory + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
