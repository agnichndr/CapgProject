package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.exceptions.AccessPermisionDeniedException;
import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.exceptions.SessionTimedOutException;
import com.capgemini.go.model.Product;

public interface RetailerService {

	List<Product> getAllProducts(String userId) throws Exception;

	List<Product> searchProduct(String productName, String userID)
			throws ProductNotFoundException, SessionTimedOutException, AccessPermisionDeniedException;

	List<Product> filterProduct(String color, String name, double lowPrice, double highPrice, String manufacturerID,
			String cat, String userID) throws Exception;
	/*
	 * Option 1 : Sort Price Low to High Option 2 : Sort Price High to Low
	 */

	List<Product> sortProduct(List<Product> prodList, int option, String userID) throws Exception;

}
