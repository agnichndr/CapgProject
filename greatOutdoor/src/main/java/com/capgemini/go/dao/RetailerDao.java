package com.capgemini.go.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.model.Address;
import com.capgemini.go.model.Cart;
import com.capgemini.go.model.Product;

public interface RetailerDao {

	List<Product> getAllProducts();

	List<Product> searchProduct(String productName) throws ProductNotFoundException;

	List<Product> filterProduct(String color, String name, double lowPrice, double highPrice, String manufacturerID,
			String cat) throws Exception;
	/*
	 * Option 1 : Sort Price Low to High Option 2 : Sort Price High to Low
	 */

	List<Product> sortProduct(List<Product> prodList, int option);

	boolean addProductToFreqOrderDB(String retailerID, List<Product> productList, List<Address> addressLists);

	boolean addAddress(Address address);

	boolean updateAddress(String retailerId, Address address);

	boolean deleteAddress(String retailerID, String addressID);

	boolean changeOrderAddress(String retailerID, Address address, String orderid);

	boolean changeProductAddress(String retailerID, Product product, Address address, Map<Product, Address> freqOrder);

	boolean addItemToCart(Map<Product, Integer> productList, String retailerID);

	boolean deleteItemfromCart(Map<Product, Integer> productList, Product product, Integer q, String retailerID);

	boolean checkout(Cart cart, String retailerID, Date d, Address address);

	boolean returnOrder(String retailerID, String reason, String ordId, Date d);

	/* boolean returnProduct(List<Product>List, int ProdId); */
	boolean cancelProduct(String prodId, String orderId);

	boolean cancelOrder(String ordrId);

	boolean retailerRegistration(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory);

	boolean login(String u, String pw);

}
