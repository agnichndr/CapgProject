package com.capgemini.go.exceptions;

public class ProductNotFoundException extends Exception {
	
	private static final long serialVersionUID = 7551988649936522525L;
	public ProductNotFoundException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 

}
