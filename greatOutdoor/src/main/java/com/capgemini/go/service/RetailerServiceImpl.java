package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.dao.RetailerDao;
import com.capgemini.go.dao.RetailerDaoImpl;
import com.capgemini.go.exceptions.AccessPermisionDeniedException;
import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.exceptions.SessionTimedOutException;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.User;

public class RetailerServiceImpl implements RetailerService {

	private RetailerDaoImpl retailerDao = new RetailerDaoImpl();

	public RetailerDaoImpl getRetailerDao() {
		return retailerDao;
	}

	public void setRetailerDao(RetailerDaoImpl retailerDao) {
		this.retailerDao = retailerDao;
	}

	public List<Product> getAllProducts(String userID) throws Exception {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}
			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 3)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}

		return retailerDao.getAllProducts();
	}

	public List<Product> searchProduct(String productName, String userID)
			throws ProductNotFoundException, SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}
			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 3)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}
		return retailerDao.searchProduct(productName);

	}

	public List<Product> filterProduct(String color, String name, double lowPrice, double highPrice,
			String manufacturerID, String cat, String userID) throws Exception {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 3)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}
		return retailerDao.filterProduct(color, name, lowPrice, highPrice, manufacturerID, cat);
	}

	public List<Product> sortProduct(List<Product> prodList, int option, String userID) throws Exception {
		// TODO Auto-generated method stub
		int i = 0;
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 3)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}

		}
		return retailerDao.sortProduct(prodList, option);
	}

}
