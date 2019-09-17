package com.capgemini.go.dao;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.exceptions.InvalidUserNameOrPasswordException;
import com.capgemini.go.model.User;

public class UserDaoImpl implements UserDao {

	public void userLogin(String username, String password) throws InvalidUserNameOrPasswordException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if (user.getUserId().equals(username) && user.getPassword().equals(password)) {
				user.setActiveStatus(true);
				return;
			}
		}
		throw new InvalidUserNameOrPasswordException(" Username or Password is Incorrect");

	}

}
