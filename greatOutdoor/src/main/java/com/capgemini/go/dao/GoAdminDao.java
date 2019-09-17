package com.capgemini.go.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capgemini.go.exceptions.InvalidReturnReportModeException;
import com.capgemini.go.exceptions.NoWrongProductException;
import com.capgemini.go.exceptions.ProductMasterNotAddedException;
import com.capgemini.go.exceptions.UserAlreadyExistsException;
import com.capgemini.go.model.Address;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.ProductMaster;
import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.SalesRep;

public interface GoAdminDao {

	List<OrderReturn> sendNotification(List<OrderReturn> orderReturnDatabase) throws NoWrongProductException;

	boolean retrieveReturnReport(Date start, Date end, Date yearStart, Date yearEnd, Date year, int mode)
			throws InvalidReturnReportModeException;

	boolean addProductMaster(ProductMaster productmaster)
			throws ProductMasterNotAddedException, UserAlreadyExistsException, Exception;

	void setBonus(String srep_ID, double bonus, List<SalesRep> salesDB);

	void getBonus(String srep_ID, List<SalesRep> salesDB);

	void setTarget(String srep_ID, double target, List<SalesRep> salesDB);

	void setDiscount(String srep_ID, double discount, List<Retailer> retailerDB);

	void getDiscount(String srep_ID, List<Retailer> retailerDB);

	void suggestfreqorderproducts(String userID, String orderDB, Map<Product, Address> freqOrder);
}
