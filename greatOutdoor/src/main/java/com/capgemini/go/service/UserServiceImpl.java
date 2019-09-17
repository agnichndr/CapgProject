package com.capgemini.go.service;

import com.capgemini.go.dao.ProductMasterDao;
import com.capgemini.go.dao.UserDao;

public class UserServiceImpl implements UserService {

	// private static final UserDao UserDao = null;
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(ProductMasterDao productMasterDao) {
		this.userDao = userDao;
	}

	public boolean userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean userRegistration(String uid, String uname, String umail, long unumber, boolean activeStatus,
			String pass, int userCat) {
		// TODO Auto-generated method stub
		return false;
	}

}
