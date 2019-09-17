package com.capgemini.go.exceptions;

public class UserAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 6551988640016522525L;
	 public UserAlreadyExistsException(String s) {
	
        // Call constructor of parent Exception 
        super(s); 
    } 


}
