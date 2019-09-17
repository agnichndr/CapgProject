package com.capgemini.go.service;

import java.util.List;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.dao.ProductMasterDao;
import com.capgemini.go.dao.ProductMasterDaoImpl;
import com.capgemini.go.exceptions.AccessPermisionDeniedException;
import com.capgemini.go.exceptions.ProductNotAddedException;
import com.capgemini.go.exceptions.ProductNotDeletedException;
import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.exceptions.ProductNotUpdatedException;
import com.capgemini.go.exceptions.SessionTimedOutException;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.User;

public class ProductMasterServiceImpl implements ProductMasterService {

	private ProductMasterDaoImpl productMasterDao = new ProductMasterDaoImpl();

	public ProductMasterDaoImpl getProductMasterDao() {
		return productMasterDao;
	}

	public void setProductMasterDao(ProductMasterDaoImpl productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public List<Product> getAllProducts(String userID) throws SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub

		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}

		return productMasterDao.getAllProducts(); // calling the product Master dao to
	}

	public List<Product> searchProduct(String productName, String userID)
			throws ProductNotFoundException, SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}

		}
		return productMasterDao.searchProduct(productName);
	}

	public List<Product> filterProduct(String color, String name, double lowPrice, double highPrice,
			String manufacturerID, String cat, String userID) throws Exception {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}
			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}
		return productMasterDao.filterProduct(color, name, lowPrice, highPrice, manufacturerID, cat);
	}

	public List<Product> sortProduct(List<Product> prodList, int option, String userID)
			throws SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}
			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}
		return productMasterDao.sortProduct(prodList, option);
	}

	public boolean deleteProduct(String productID, String userID)
			throws ProductNotDeletedException, SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub\
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}
			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}

		}
		return productMasterDao.deleteProduct(productID);
	}

	public boolean addProduct(Product product, String userID)
			throws ProductNotAddedException, SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}

		/*
		 * for(Product p : Database.productDB) { if(p.getpId().equals(product.getpId()))
		 * { throw new ProductNotAddedException("Product with Same PID exists"); } }
		 */

		return productMasterDao.addProduct(product);
	}

	public boolean updateProduct(Product product, String userID)
			throws ProductNotUpdatedException, SessionTimedOutException, AccessPermisionDeniedException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 4)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}

		return productMasterDao.updateProduct(product);
	}

}
