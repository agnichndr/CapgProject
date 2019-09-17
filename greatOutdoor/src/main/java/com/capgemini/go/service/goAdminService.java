package com.capgemini.go.service;

import java.util.Date;
import java.util.List;

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

public interface goAdminService {

	List<OrderReturn> sendNotification(List<OrderReturn> orderReturnDatabase, String userID) throws Exception;

	boolean retrieveReturnReport(Date start, Date end, Date startYear, Date endYear, Date Year, int mode, String userID)
			throws InvalidReturnReportModeException, SessionTimedOutException, AccessPermisionDeniedException;

	boolean addProductMaster(ProductMaster productmaster, String userID)
			throws ProductMasterNotAddedException, UserAlreadyExistsException, SessionTimedOutException,
			AccessPermisionDeniedException, InvalidPhoneNumberException, InvalidEmailException, Exception;

}
