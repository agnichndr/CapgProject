package com.capgemini.go.service;

public interface UserService {

	boolean userLogin(String username, String password);

	boolean userRegistration(String uid, String uname, String umail, long unumber, boolean activeStatus, String pass,
			int userCat);
}
