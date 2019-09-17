/**
 * Time Reporter Class Implementation
 * 
 * Contributers: Kunal Roychoudhury; Vikas Singh; Sujit Kumar
 */

package com.capgemini.go.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.go.model.Order;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.Product.category;
import com.capgemini.go.model.RetailerInventoryItem;
import com.capgemini.go.DBSim.Database;

public class TimeReporterDaoImpl implements TimeReporterDao {

	@SuppressWarnings("deprecation")
	public List<RetailerInventoryItem> getMonthlyReport(String adminId, String retailerId, Date requestDate) {
		// TODO Auto-generated method stub
		if (requestDate.after(new Date(System.currentTimeMillis()))) {
			// raise InValid Date Exception
			return null;
		} else if (Database.riItemDB == null) {
			return null;
		}
		/*
		 * SELECT * FROM retailerInventory RI WHERE RI.retailerId == retailerId
		 */
		List<RetailerInventoryItem> result = new ArrayList<RetailerInventoryItem>();
		for (int i = 0; i < Database.riItemDB.size(); i++) {
			if (Database.riItemDB.get(i).getRetailerId().equals(retailerId)) {
				RetailerInventoryItem temp = new RetailerInventoryItem(Database.riItemDB.get(i).getProduct(),
						Database.riItemDB.get(i).getOrderId(), Database.riItemDB.get(i).getShelfTimeIn(),
						Database.riItemDB.get(i).getShelfTimeOut(), Database.riItemDB.get(i).getRetailerId());
				if (temp.getShelfTimeOut() != null) {
					if (temp.getShelfTimeOut().getMonth() == requestDate.getMonth()) {
						result.add(temp);
					}
				} else {
					// replace the Date ShelfTimeOut with current Date
					System.err.println("Product not yet sold | Working around....");
					temp.setShelfTimeOut(new Date(System.currentTimeMillis()));
					result.add(temp);
				}
			}
		}
		return result;
	}

	public List<RetailerInventoryItem> getQuarterlyReport(String adminId, String retailerId, Date requestDateInitial,
			Date requestDateFinal) {
		// TODO Auto-generated method stub
		if (requestDateInitial.after(new Date(System.currentTimeMillis()))) {
			// raise InValid Date Exception
			return null;
		} else if (Database.riItemDB == null) {
			return null;
		}
		/*
		 * SELECT * FROM retailerInventory RI WHERE RI.retailerId == retailerId
		 */
		List<RetailerInventoryItem> result = new ArrayList<RetailerInventoryItem>();
		for (int i = 0; i < Database.riItemDB.size(); i++) {
			if (Database.riItemDB.get(i).getRetailerId().equals(retailerId)) {
				RetailerInventoryItem temp = new RetailerInventoryItem(Database.riItemDB.get(i).getProduct(),
						Database.riItemDB.get(i).getOrderId(), Database.riItemDB.get(i).getShelfTimeIn(),
						Database.riItemDB.get(i).getShelfTimeOut(), Database.riItemDB.get(i).getRetailerId());
				if (temp.getShelfTimeOut() != null) {
					if (temp.getShelfTimeOut().after(requestDateInitial)
							&& temp.getShelfTimeOut().before(requestDateFinal)) {
						result.add(temp);
					}
				} else {
					// replace the Date ShelfTimeOut with current Date
					System.err.println("Product not yet sold | Working around....");
					temp.setShelfTimeOut(new Date(System.currentTimeMillis()));
					if (temp.getShelfTimeOut().after(requestDateInitial)
							&& temp.getShelfTimeOut().before(requestDateFinal)) {
						result.add(temp);
					} else {
						// do not add item to list
					}
				}
			}
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public List<RetailerInventoryItem> getYearlyReport(String adminId, String retailerId, Date requestDate) {
		// TODO Auto-generated method stub
		if (requestDate.after(new Date(System.currentTimeMillis()))) {
			// raise InValid Date Exception
			return null;
		} else if (Database.riItemDB == null) {
			return null;
		}
		/*
		 * SELECT * FROM retailerInventory RI WHERE RI.retailerId == retailerId
		 */
		List<RetailerInventoryItem> result = new ArrayList<RetailerInventoryItem>();
		for (int i = 0; i < Database.riItemDB.size(); i++) {
			if (Database.riItemDB.get(i).getRetailerId().equals(retailerId)) {
				RetailerInventoryItem temp = new RetailerInventoryItem(Database.riItemDB.get(i).getProduct(),
						Database.riItemDB.get(i).getOrderId(), Database.riItemDB.get(i).getShelfTimeIn(),
						Database.riItemDB.get(i).getShelfTimeOut(), Database.riItemDB.get(i).getRetailerId());
				if (temp.getShelfTimeOut() != null) {
					if (temp.getShelfTimeOut().getYear() == requestDate.getYear()) {
						result.add(temp);
					}
				} else {
					// replace the Date ShelfTimeOut with current Date
					System.err.println("Product not yet sold | Working around....");
					temp.setShelfTimeOut(new Date(System.currentTimeMillis()));
					result.add(temp);
				}
			}
		}
		return result;
	}

	public List<RetailerInventoryItem> getOutlierProductCategoryDeliveryTime(String adminId, String retailerId,
			String pId, Product.category cat) {
		// TODO Auto-generated method stub
		List<RetailerInventoryItem> result = new ArrayList<RetailerInventoryItem>();
		for (Order order : Database.orderDB) {
			if (order.getuserID().equals(retailerId)) {
				for (Product prod : order.getProdList()) {
					for (int i = 0; i < Database.productDB.size(); i++) {
						if (prod.equals(Database.productDB.get(i))) {
							/*
							 * the ShelfTimeIn contains the Dispatch Time of the Product the ShelfTimeOut
							 * contains the Receiving Time of the Product by the retailer
							 */
							RetailerInventoryItem temp = new RetailerInventoryItem(prod,
									Database.productDB.get(i).getpId(), order.getoDisTime(), order.getoRecTime(), pId);
							result.add(temp);
						}
					}
				}
			} else {
				continue;
			}
		}
		return result;
	}

	public List<RetailerInventoryItem> getOutlierItemsDeliveryTime(String adminId, String retailerId, String orderId,
			Product.category cat) {
		// TODO Auto-generated method stub
		List<RetailerInventoryItem> result = new ArrayList<RetailerInventoryItem>();
		for (Order order : Database.orderDB) {
			if (order.getuserID().equals(retailerId)) {
				for (Product ord : order.getProdList()) {
					for (int i = 0; i < Database.riItemDB.size(); i++) {
						if (ord.equals(Database.riItemDB.get(i))) {
							/*
							 * the ShelfTimeIn contains the Dispatch Time of the Item the ShelfTimeOut
							 * contains the Receiving Time of the Item by the retailer
							 */
							RetailerInventoryItem temp = new RetailerInventoryItem(ord,
									Database.riItemDB.get(i).getOrderId(), order.getoDisTime(), order.getoRecTime(),
									orderId);
							result.add(temp);
						}
					}
				}
			} else {
				continue;
			}
		}
		return result;
	}

	public List<RetailerInventoryItem> getOutlierItemsInCategoryDeliveryTime(String adminId, String retailerId,
			category cat) {
		// TODO Auto-generated method stub
		List<RetailerInventoryItem> result = new ArrayList<RetailerInventoryItem>();
		for (Order order : Database.orderDB) {
			if (order.getuserID().equals(retailerId)) {
				for (Product prod : order.getProdList()) {
					for (int i = 0; i < Database.riItemDB.size(); i++) {
						if (prod.equals(Database.riItemDB.get(i).getProduct())) {
							/*
							 * CAUTION ***** CAUTION ***** CAUTION the below code is just a hack for
							 * simulating functionality of this function using available
							 * RetailerInventoryItem Class model. the ShelfTimeIn contains the Dispatch Time
							 * of the Product the ShelfTimeOut contains the Receiving Time of the Product by
							 * the retailer
							 */
							RetailerInventoryItem temp = new RetailerInventoryItem(
									Database.riItemDB.get(i).getProduct(), Database.riItemDB.get(i).getOrderId(),
									order.getoDisTime(), order.getoRecTime(), Database.riItemDB.get(i).getRetailerId());
							result.add(temp);
						}
					}
				}
			} else {
				continue;
			}
		}
		return result;
	}
}
