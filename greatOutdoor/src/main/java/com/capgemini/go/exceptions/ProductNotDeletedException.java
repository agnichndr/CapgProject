package com.capgemini.go.exceptions;

public class ProductNotDeletedException extends Exception {
	
	private static final long serialVersionUID = 6651988649936522525L;
	public ProductNotDeletedException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 

}
