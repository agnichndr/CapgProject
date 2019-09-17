package com.capgemini.go.exceptions;

public class ProductNotAddedException extends Exception {
	
	private static final long serialVersionUID = 6551988649936522525L;
	public ProductNotAddedException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 

}
