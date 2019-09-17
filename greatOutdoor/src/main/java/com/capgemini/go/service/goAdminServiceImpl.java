package com.capgemini.go.service;

import java.util.Date;
import java.util.List;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.dao.GoAdminDao;
import com.capgemini.go.dao.GoAdminDaoImpl;
import com.capgemini.go.exceptions.AccessPermisionDeniedException;
import com.capgemini.go.exceptions.InvalidEmailException;
import com.capgemini.go.exceptions.InvalidPhoneNumberException;
import com.capgemini.go.exceptions.InvalidReturnReportModeException;
import com.capgemini.go.exceptions.NoWrongProductException;
import com.capgemini.go.exceptions.ProductMasterNotAddedException;
import com.capgemini.go.exceptions.SessionTimedOutException;
import com.capgemini.go.exceptions.UserAlreadyExistsException;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.ProductMaster;
import com.capgemini.go.model.User;
import com.capgemini.go.utility.GoUtility;

public class goAdminServiceImpl implements goAdminService {

	private GoAdminDaoImpl goAdminDao = new GoAdminDaoImpl();

	public GoAdminDaoImpl getGoAdminDao() {
		return goAdminDao;
	}

	public void setGoAdminDaoImpl(GoAdminDaoImpl goAdminDao) {
		this.goAdminDao = goAdminDao;
	}

	public List<OrderReturn> sendNotification(List<OrderReturn> orderReturnDatabase, String userID)
			throws SessionTimedOutException, AccessPermisionDeniedException, NoWrongProductException {
		// TODO Auto-generated method stub
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 1)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}

		return goAdminDao.sendNotification(orderReturnDatabase);
	}
	/*
	 * mode 1 = monthly mode 2 = quaterly mode 3 = yearly
	 */
	// Method to generate a return report File;

	public boolean retrieveReturnReport(Date start, Date end, Date startYear, Date endYear, Date year, int mode,
			String userID)
			throws InvalidReturnReportModeException, SessionTimedOutException, AccessPermisionDeniedException

	{
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}
			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 1)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}
		// TODO Auto-generated method stub
		return goAdminDao.retrieveReturnReport(start, end, startYear, endYear, year, mode);
	}

	public boolean addProductMaster(ProductMaster productmaster, String userID) throws Exception {
		for (User user : Database.userDB) {
			if ((user.getUserId().equals(userID)) && user.isActiveStatus() == false) {
				throw new SessionTimedOutException("Session Timed Out");
			}

			if ((user.getUserId().equals(userID)) && (user.getUserCategory() != 1)) {
				throw new AccessPermisionDeniedException(" Access Denied");
			}
		}

		if (!GoUtility.validatePhoneNumber(productmaster.getUserNumber())) {
			throw new InvalidPhoneNumberException("Entered Phone number is not valid");
		}

		if (!GoUtility.isValid(productmaster.getUserMail())) {
			throw new InvalidEmailException("Entered email is invalid");
		}

		for (ProductMaster productMaster : Database.productMasterDB) {

			if (productMaster.getUserId().equals(productmaster.getUserId())) {
				throw new UserAlreadyExistsException("User Id already Exists");
			}
		}
		return goAdminDao.addProductMaster(productmaster);
	}

}
