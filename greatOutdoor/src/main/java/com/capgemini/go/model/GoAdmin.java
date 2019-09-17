package com.capgemini.go.model;

import java.io.Serializable;

public class GoAdmin extends User implements Serializable {

	private static final long serialVersionUID = 7551995649936529526L;

	// Constructor
	public GoAdmin(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory) {
		super(userId, userName, userMail, userNumber, activeStatus, password, userCategory);

	}

	@Override
	public String toString() {
		return "GoAdmin [getUserCategory()=" + getUserCategory() + ", getUserId()=" + getUserId() + ", getUserName()="
				+ getUserName() + ", getUserMail()=" + getUserMail() + ", getUserNumber()=" + getUserNumber()
				+ ", isActiveStatus()=" + isActiveStatus() + ", getPassword()=" + getPassword() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
