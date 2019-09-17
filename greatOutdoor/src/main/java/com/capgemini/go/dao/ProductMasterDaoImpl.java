package com.capgemini.go.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import java.io.*;
import com.capgemini.go.DBSim.Database;
import com.capgemini.go.exceptions.PriceRangeMismatchException;
import com.capgemini.go.exceptions.ProductNotAddedException;
import com.capgemini.go.exceptions.ProductNotDeletedException;
import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.exceptions.ProductNotUpdatedException;
import com.capgemini.go.model.Product;

public class ProductMasterDaoImpl implements ProductMasterDao, Comparator<Product> {

	// Method to get List of All Products
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub

		List<Product> allProducts = Database.productDB; // Entire Product Database is obtained
		return allProducts;
	}

	// Method to search Product depends upon product name
	public List<Product> searchProduct(String productName) throws ProductNotFoundException {
		// TODO Auto-generated method stub

		List<Product> allProducts = Database.productDB;
		List<Product> searchedProduct = new ArrayList<Product>();

		for (Product p : allProducts) {
			// Search algorithm to match entered keywords with product name
			if (p.getpName().toUpperCase().contains(productName.toUpperCase().trim())) {
				searchedProduct.add(p);
			}
		}
		// If no product matches with keyword
		if (searchedProduct.size() == 0) {

			throw new ProductNotFoundException(" No Results Matches");

		}

		return searchedProduct;
	}

	// Method to display product depends upon category
	public List<Product> filterProduct(String color, String name, double lowPrice, double highPrice,
			String manufacturerID, String cat) throws Exception {

		List<Product> filteredList = new ArrayList<Product>();
		try {
			
			// if low range is greater than high range of price
			if (lowPrice > highPrice) {
				throw new PriceRangeMismatchException(" Low Range Value is greater than High Range Value ");
			}

			for (Product p : Database.productDB) {
				String x = p.getCat().name().toUpperCase();
				
				// Filter depended upon several filter parameters like color, price, manufacturer, product category
				if (((p.getpColor()).equalsIgnoreCase(color) || color.equals(""))
						&& ((p.getpName()).toUpperCase().contains(name.toUpperCase()) || name.equals(""))
						&& ((p.getManufacturer().getmId()).equalsIgnoreCase(manufacturerID)
								|| manufacturerID.equals(""))

						&& (cat.equals("") || x.equals(cat.toUpperCase())) && p.getPrice() >= lowPrice
						&& p.getPrice() <= highPrice)

				{

					filteredList.add(p);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		// if no product matches according to the filter category
		if (filteredList.size() == 0) {

			throw new ProductNotFoundException(" No Results Matches");

		}
		return filteredList;
	}

	// Method to delete Product

	public boolean deleteProduct(String productID) throws ProductNotDeletedException {
		// TODO Auto-generated method stub
		try {
			for (Product p : Database.productDB) {
				if (p.getpId().equals(productID)) {
					Database.productDB.remove(p);
					return true;
				}
			}
		} catch (Exception ex) {
			throw new ProductNotDeletedException("Error occur in deleting Product");
		}
		return false;
	}

	// Method to add Product
	public boolean addProduct(Product product) throws ProductNotAddedException {
		// TODO Auto-generated method stub

		try {
			Database.productDB.add(product);
		} catch (Exception ex) {

			throw new ProductNotAddedException("Error occured in adding a product");
		}
		return true;
	}

	// Method to update Product
	public boolean updateProduct(Product product) throws ProductNotUpdatedException {
		// TODO Auto-generated method stub
		try {
			int i = 0;
			for (Product p : Database.productDB) {

				if (p.getpId().equals(product.getpId())) {

					Database.productDB.set(i, product);
					return true;

				}
				i++;
			}
		} catch (Exception ex) {
			throw new ProductNotUpdatedException("Error occured in updating Product");
		}
		return false;
	}

	// Method to sort the list
	public List<Product> sortProduct(List<Product> prodList, int option) {
		// TODO Auto-generated method stub
		// sorting from low to high price
		if (option == 1) {
			Collections.sort(prodList, new ProductMasterDaoImpl());
		}
		// sorting from high to low price
		else if (option == 2) {
			Collections.sort(prodList, new ProductMasterDaoImpl());
			Collections.reverse(prodList);
		}
		return prodList;
	}

	// compare method to compare the pricing
	public int compare(Product arg0, Product arg1) {

		return (int) (arg0.getPrice() - arg1.getPrice());
	}

}
