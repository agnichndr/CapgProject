package com.capgemini.go.dao;

import java.util.List;

import com.capgemini.go.exceptions.ProductNotAddedException;
import com.capgemini.go.exceptions.ProductNotDeletedException;
import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.exceptions.ProductNotUpdatedException;
import com.capgemini.go.model.Product;

public interface ProductMasterDao {

	List<Product> getAllProducts();

	List<Product> searchProduct(String productName) throws ProductNotFoundException;

	List<Product> filterProduct(String color, String name, double lowPrice, double highPrice, String manufacturerID,
			String cat) throws Exception;
	/*
	 * Option 1 : Sort Price Low to High Option 2 : Sort Price High to Low
	 */

	List<Product> sortProduct(List<Product> prodList, int option);

	boolean deleteProduct(String productID) throws ProductNotDeletedException;

	boolean addProduct(Product product) throws ProductNotAddedException;

	boolean updateProduct(Product product) throws ProductNotUpdatedException;

}
