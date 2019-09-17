package com.capgemini.go.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.exceptions.InvalidReturnReportModeException;
import com.capgemini.go.exceptions.NoWrongProductException;
import com.capgemini.go.exceptions.ProductMasterNotAddedException;
import com.capgemini.go.model.Address;
import com.capgemini.go.model.FreqOrderDB;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.ProductMaster;
import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.SalesRep;
import com.capgemini.go.model.User;

public class GoAdminDaoImpl implements GoAdminDao {

	// Method to send Notification of Wrong Product Shipment
	public List<OrderReturn> sendNotification(List<OrderReturn> orderReturnDatabase) throws NoWrongProductException {
		// TODO Auto-generated method stub
		// Generating a list for wrong Product Shipped
		List<OrderReturn> wrongProductNotificationList = new ArrayList<OrderReturn>();
		for (OrderReturn order : orderReturnDatabase) {
			String reason = order.getReason();
			if ((reason.toUpperCase().contains("WRONG")) || (reason.toUpperCase().contains("WRONG PRODUCT"))
					|| reason.toUpperCase().contains("WRONG PRODUCT SHIPPED")) {
				wrongProductNotificationList.add(order);
			}
		}
		// if no product found in wrong product shipment category
		if (wrongProductNotificationList.size() == 0) {
			throw new NoWrongProductException("No Wrong Product is Shipped");
		}

		return wrongProductNotificationList;
	}

	// Method to add ProductMaster
	public boolean addProductMaster(ProductMaster productmaster) throws Exception {
		// TODO Auto-generated method stub

		try {
			// adding new product to database
			Database.userDB.add(new User(productmaster.getUserId(), productmaster.getUserName(),
					productmaster.getUserMail(), productmaster.getUserNumber(), productmaster.isActiveStatus(),
					productmaster.getPassword(), productmaster.getUserCategory()));
			Database.productMasterDB.add(productmaster);
		} catch (Exception e) {
			throw new ProductMasterNotAddedException("Error in adding Product Master");
		}

		return true;
	}

	// Method to set the bonus for sales rep
	public void setBonus(String srep_ID, double bonus, List<SalesRep> salesDB) {
		// The ID is entered and matching object is searched in the List.

		Iterator<SalesRep> iterator = salesDB.iterator();
		while (iterator.hasNext()) {
			SalesRep s1 = iterator.next();

			if (s1.getUserId().equals(srep_ID)) {
				s1.setsBonus(bonus);
				System.out.println(s1.getsBonus());
			}
		}

		// The bonus in that object is modified.

	}

	/*
	 * mode 1 = monthly mode 2 = quaterly mode 3 = yearly
	 */

	// Method to generate Monthly yearly or annual return report

	public boolean retrieveReturnReport(Date start, Date end, Date yearStart, Date yearEnd, Date year, int mode)
			throws InvalidReturnReportModeException {
		// TODO Auto-generated method stub
		switch (mode) {
		case 1:
			if (start == null || end == null || start.after(end)) {
				throw new InvalidReturnReportModeException("Error in generating return report");
			}
			Double totalUSDWrongSipped = 0.0D, totalUSDIncompleteProduct = 0.0D, totalUSDOthers = 0.0D;
			for (OrderReturn orderReturn : Database.orderReturnDB) {
				if ((orderReturn.getrTimeStamp().after(start) || orderReturn.getrTimeStamp().equals(start))
						&& (orderReturn.getrTimeStamp().before(end)) || orderReturn.getrTimeStamp().equals(end)) {

					if (orderReturn.getReason().toUpperCase().contains("Wrong".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							// Storing the total USD value return for the given time period due to wrong
							// product shipped
							totalUSDWrongSipped += entry.getKey().getPrice() * entry.getValue();
						}
					}

					else if (orderReturn.getReason().toUpperCase().contains("Incomplete".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {

							// Storing the total USD value return for the given time period due to
							// Incomplete product shipped
							totalUSDIncompleteProduct += entry.getKey().getPrice() * entry.getValue();
						}
					}

					else {

						Map<Product, Integer> returnList = orderReturn.getReturnList();
						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							totalUSDOthers += entry.getKey().getPrice() * entry.getValue();
						}
					}

				}
			}
			System.out.println("total Usd for Wrong  :" + totalUSDWrongSipped);
			System.out.println("total Usd for Incomplete  :" + totalUSDIncompleteProduct);
			System.out.println("total Usd for Other  :" + totalUSDOthers);

			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(start);
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(end);

			int m1 = startCalendar.get(Calendar.MONTH);
			int m2 = endCalendar.get(Calendar.MONTH);
			int y1 = startCalendar.get(Calendar.YEAR);
			int y2 = endCalendar.get(Calendar.YEAR);
			int month_diff = (y2 - y1) * 12 + (m2 - m1) + 1;

			double wrongUSD[] = new double[month_diff];
			double incompleteUSD[] = new double[month_diff];

			for (int k = 0; k < month_diff; k++) {
				wrongUSD[k] = 0.0D;
				incompleteUSD[k] = 0.0D;

			}

			for (OrderReturn orderReturn : Database.orderReturnDB) {
				if ((orderReturn.getrTimeStamp().after(start) || orderReturn.getrTimeStamp().equals(start))
						&& (orderReturn.getrTimeStamp().before(end)) || orderReturn.getrTimeStamp().equals(end)) {
					Calendar cal = new GregorianCalendar();
					cal.setTime(orderReturn.getrTimeStamp());
					int m = cal.get(Calendar.MONTH);
					int y = cal.get(Calendar.YEAR);

					if (orderReturn.getReason().toUpperCase().contains("Wrong".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							// Storing the total USD value return for the given time period due to wrong
							// product shipped
							wrongUSD[(m - m1) + (y - y1) * 12] += entry.getKey().getPrice() * entry.getValue();
						}
					}

					else if (orderReturn.getReason().toUpperCase().contains("Incomplete".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {

							// Storing the total USD value return for the given time period due to
							// Incomplete product shipped
							incompleteUSD[(m - m1) + (y - y1) * 12] += entry.getKey().getPrice() * entry.getValue();
						}
					}
				}

			}

			System.out.println("Wrong Product Shipped\n");
			for (int y = 0; y < wrongUSD.length; y++) {
				System.out.print(wrongUSD[y] + "\t");
				if (y != 0) {
					System.out.println("Percentage Change : " + (wrongUSD[y] - wrongUSD[y - 1]) / 100);
				}

			}

			System.out.println("Incomplete Product Shipped\n");
			for (int y = 0; y < incompleteUSD.length; y++) {
				System.out.println(incompleteUSD[y]);
				if (y != 0) {
					System.out.println("Percentage Change : " + (incompleteUSD[y] - incompleteUSD[y - 1]) / 100);
				}
			}

			break;
		case 2:
			if (year == null) {
				throw new InvalidReturnReportModeException("Error in generating return report");
			}
			totalUSDWrongSipped = 0.0D;
			totalUSDIncompleteProduct = 0.0D;
			totalUSDOthers = 0.0D;

			Calendar cal = new GregorianCalendar();
			cal.setTime(year);

			double wrongUSDQ[] = new double[4];
			double incompleteUSDQ[] = new double[4];
			for (int k = 0; k < 4; k++) {
				wrongUSDQ[k] = 0.0D;
				incompleteUSDQ[k] = 0.0D;

			}

			for (OrderReturn orderReturn : Database.orderReturnDB) {
				Calendar orderCal = new GregorianCalendar();
				orderCal.setTime(orderReturn.getrTimeStamp());

				if (cal.get(Calendar.YEAR) == orderCal.get(Calendar.YEAR)) {

					if (orderReturn.getReason().toUpperCase().contains("Wrong".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							// Storing the total USD value return for the given time period due to wrong
							// product shipped
							totalUSDWrongSipped += entry.getKey().getPrice() * entry.getValue();
							if (orderCal.get(Calendar.MONTH) >= 0 && orderCal.get(Calendar.MONTH) < 3) {
								wrongUSDQ[0] += entry.getKey().getPrice() * entry.getValue();
							} else if (orderCal.get(Calendar.MONTH) >= 3 && orderCal.get(Calendar.MONTH) < 6) {
								wrongUSDQ[1] += entry.getKey().getPrice() * entry.getValue();
							} else if (orderCal.get(Calendar.MONTH) >= 6 && orderCal.get(Calendar.MONTH) < 9) {
								wrongUSDQ[2] += entry.getKey().getPrice() * entry.getValue();
							} else if (orderCal.get(Calendar.MONTH) >= 9 && orderCal.get(Calendar.MONTH) < 12) {
								wrongUSDQ[3] += entry.getKey().getPrice() * entry.getValue();
							}

						}
					}

					else if (orderReturn.getReason().toUpperCase().contains("Incomplete".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {

							// Storing the total USD value return for the given time period due to
							// Incomplete product shipped
							totalUSDIncompleteProduct += entry.getKey().getPrice() * entry.getValue();

							if (orderCal.get(Calendar.MONTH) >= 0 && orderCal.get(Calendar.MONTH) < 3) {
								incompleteUSDQ[0] += entry.getKey().getPrice() * entry.getValue();
							} else if (orderCal.get(Calendar.MONTH) >= 3 && orderCal.get(Calendar.MONTH) < 6) {
								incompleteUSDQ[1] += entry.getKey().getPrice() * entry.getValue();
							} else if (orderCal.get(Calendar.MONTH) >= 6 && orderCal.get(Calendar.MONTH) < 9) {
								incompleteUSDQ[2] += entry.getKey().getPrice() * entry.getValue();
							} else if (orderCal.get(Calendar.MONTH) >= 9 && orderCal.get(Calendar.MONTH) < 12) {
								incompleteUSDQ[3] += entry.getKey().getPrice() * entry.getValue();
							}

						}
					}

					else {

						Map<Product, Integer> returnList = orderReturn.getReturnList();
						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							totalUSDOthers += entry.getKey().getPrice() * entry.getValue();
						}
					}

				}
			}
			System.out.println("total Usd for Wrong  :" + totalUSDWrongSipped);
			System.out.println("total Usd for Incomplete  :" + totalUSDIncompleteProduct);
			System.out.println("total Usd for Other  :" + totalUSDOthers);

			System.out.println("Wrong Product Shipped");
			for (int k = 0; k < 4; k++) {
				System.out.print(wrongUSDQ[k] + "\t");

				if (k != 0) {
					System.out.println("Percentage Change : " + (wrongUSDQ[k] - wrongUSDQ[k - 1]) / 100);
				}
			}

			System.out.println("Incomplete Product Shipped");
			for (int k = 0; k < 4; k++) {
				System.out.print(incompleteUSDQ[k] + "\t");
				if (k != 0) {
					System.out.println("Percentage Change : " + (incompleteUSDQ[k] - incompleteUSDQ[k - 1]) / 100);
				}
			}

			break;
		case 3:

			if (yearStart == null || yearEnd == null) {
				throw new InvalidReturnReportModeException("Error in generating return report");
			}

			totalUSDWrongSipped = 0.0D;
			totalUSDIncompleteProduct = 0.0D;
			totalUSDOthers = 0.0D;

			Calendar calStart = new GregorianCalendar();
			calStart.setTime(yearStart);
			Calendar calEnd = new GregorianCalendar();
			calEnd.setTime(yearEnd);

			int yi = calStart.get(Calendar.YEAR);
			int yf = calEnd.get(Calendar.YEAR);

			int year_diff = (yf - yi) + 1;

			double wrongUSDY[] = new double[year_diff];
			double incompleteUSDY[] = new double[year_diff];
			for (int k = 0; k < year_diff; k++) {
				wrongUSDY[k] = 0.0D;
				incompleteUSDY[k] = 0.0D;

			}

			for (OrderReturn orderReturn : Database.orderReturnDB) {
				Calendar orderCal = new GregorianCalendar();
				orderCal.setTime(orderReturn.getrTimeStamp());

				if ((yi <= orderCal.get(Calendar.YEAR)) && (yf >= orderCal.get(Calendar.YEAR))) {

					if (orderReturn.getReason().toUpperCase().contains("Wrong".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							// Storing the total USD value return for the given time period due to wrong
							// product shipped
							totalUSDWrongSipped += entry.getKey().getPrice() * entry.getValue();
							int yy = orderCal.get(Calendar.YEAR);
							wrongUSDY[yy - yi] += entry.getKey().getPrice() * entry.getValue();
						}
					}

					else if (orderReturn.getReason().toUpperCase().contains("Incomplete".toUpperCase())) {

						Map<Product, Integer> returnList = orderReturn.getReturnList();

						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {

							// Storing the total USD value return for the given time period due to
							// Incomplete product shipped
							totalUSDIncompleteProduct += entry.getKey().getPrice() * entry.getValue();
							int yy = orderCal.get(Calendar.YEAR);
							incompleteUSDY[yy - yi] += entry.getKey().getPrice() * entry.getValue();
						}
					}

					else {

						Map<Product, Integer> returnList = orderReturn.getReturnList();
						for (Map.Entry<Product, Integer> entry : returnList.entrySet()) {
							totalUSDOthers += entry.getKey().getPrice() * entry.getValue();
						}
					}

				}

			}

			System.out.println("total Usd for Wrong  :" + totalUSDWrongSipped);
			System.out.println("total Usd for Incomplete  :" + totalUSDIncompleteProduct);
			System.out.println("total Usd for Other  :" + totalUSDOthers);

			System.out.println("Wrong Product Shipped");
			for (int k = 0; k < year_diff; k++) {
				System.out.print(wrongUSDY[k] + "\t");

				if (k != 0) {
					System.out.println("Percentage Change : " + (wrongUSDY[k] - wrongUSDY[k - 1]) / 100);
				}
			}

			System.out.println("Incomplete Product Shipped");
			for (int k = 0; k < year_diff; k++) {
				System.out.print(incompleteUSDY[k] + "\t");
				if (k != 0) {
					System.out.println("Percentage Change : " + (incompleteUSDY[k] - incompleteUSDY[k - 1]) / 100);
				}
			}

			break;
		default:
			throw new InvalidReturnReportModeException("Error in generating return report");
		}
		return true;
	}

	// Method to get Bonus of a searched sales person
	public void getBonus(String srep_ID, List<SalesRep> salesDB) {
		// The ID is entered and matching object is searched in the List.

		Iterator<SalesRep> iterator = salesDB.iterator();
		while (iterator.hasNext()) {
			SalesRep s1 = iterator.next();

			if (s1.getUserId().equals(srep_ID)) {
				s1.getsBonus();
			}
		}
	}

	// method to set target for a sales person
	public void setTarget(String srep_ID, double target, List<SalesRep> salesDB) {
		// The ID is entered and matching object is searched in the List.

		Iterator<SalesRep> iterator = salesDB.iterator();
		while (iterator.hasNext()) {
			SalesRep s1 = iterator.next();

			if (s1.getUserId().equals(srep_ID)) {
				s1.setTarget(Double.toString(target));
			}
		}
	}

	// Method to set Discount for a retailer
	public void setDiscount(String srep_ID, double discount, List<Retailer> retailerDB) {
		// The ID is entered and matching object is searched in the List.

		Iterator<Retailer> iterator = retailerDB.iterator();
		while (iterator.hasNext()) {
			Retailer r1 = iterator.next();

			if (r1.getUserId().equals(srep_ID)) {
				r1.setDiscount(discount);
			}
		}
	}

	// Method to get value of discount
	public void getDiscount(String srep_ID, List<Retailer> retailerDB) {
		// The ID is entered and matching object is searched in the List.

		Iterator<Retailer> iterator = retailerDB.iterator();
		while (iterator.hasNext()) {
			Retailer r1 = iterator.next();

			if (r1.getUserId().equals(srep_ID)) {
				r1.getDiscount();
			}
		}
	}

	// Method to suggest frequestly ordered item
	public void suggestfreqorderproducts(String userID, String orderDB, Map<Product, Address> freqOrder)

	{
		for (User u : Database.userDB) {
			for (Order o : Database.orderDB) {
				if (o.getuserID().equals(u.getUserId())) {
					ArrayList<Product> p1 = (ArrayList<Product>) o.getProdList();
					for (Product p : p1) {
						if (p.getQtyOrder() > 2) {
							Map<Product, Address> count = (Map<Product, Address>) new HashMap<Product, Address>();
							((HashMap<Product, Address>) count).put(p, o.getAddress());
							FreqOrderDB fo = new FreqOrderDB(o.getuserID(), (java.util.Map<Product, Address>) count);
							Database.freqOrderDB.add(fo);

						}
					}
				}
			}
		}
		return;

	}

}
