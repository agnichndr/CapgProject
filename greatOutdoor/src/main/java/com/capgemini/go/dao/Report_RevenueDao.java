package com.capgemini.go.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.SalesRep;

public interface Report_RevenueDao {

	void viewSales(String user_sales_ID, List<SalesRep> salesDB) throws Exception;// Displays the Sales Rep ID , his
																					// bonus, target status , current
																					// target ,

	void viewAllSales(List<SalesRep> salesDB) throws Exception;

	void viewRetail(String user_sales_ID, List<Retailer> retDB) throws Exception;

	void viewAllRetail(List<Retailer> retDB) throws Exception;

	void viewOverAllUser(Date entry, Date exit, String user_ID) throws Exception;

	void viewOverAll(Date entry, Date exit) throws Exception;

	void viewRevenueSpecificProduct(Date entry, Date exit, String p) throws Exception;

	void viewRevenueOutdoor(Date entry, Date exit, String prod) throws Exception;
//mode 1: month to month
//mode 2: quarter to quarter
//mode 3: year to year

}
