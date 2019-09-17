package com.capgemini.go.dao;

import com.capgemini.go.exceptions.InvalidUserNameOrPasswordException;

public interface UserDao {

	void userLogin(String username, String password) throws InvalidUserNameOrPasswordException;

}
