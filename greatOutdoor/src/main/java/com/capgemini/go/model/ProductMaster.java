package com.capgemini.go.model;

import java.io.Serializable;

public class ProductMaster extends User implements Serializable {

	private static final long serialVersionUID = 7552999649936529526L;

	public ProductMaster(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory) {
		super(userId, userName, userMail, userNumber, activeStatus, password, userCategory);
	}

	@Override
	public String toString() {
		return "ProductMaster [getUserCategory()=" + getUserCategory() + ", getUserId()=" + getUserId()
				+ ", getUserName()=" + getUserName() + ", getUserMail()=" + getUserMail() + ", getUserNumber()="
				+ getUserNumber() + ", isActiveStatus()=" + isActiveStatus() + ", getPassword()=" + getPassword()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
