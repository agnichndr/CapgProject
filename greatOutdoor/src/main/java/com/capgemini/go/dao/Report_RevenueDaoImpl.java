package com.capgemini.go.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.SalesRep;

public class Report_RevenueDaoImpl implements Report_RevenueDao {

	public void viewSales(String user_sales_ID, List<SalesRep> salesDB) throws Exception {
		// checking if the given input is null or empty
		if (user_sales_ID == null) {
			throw new Exception("Invalid user Name or Invalid List");
		}
		// iterator operator for going through the list salesDB
		Iterator<SalesRep> iterator = salesDB.iterator();
		// Obtaining the elements of the list
		while (iterator.hasNext()) {
			SalesRep s1 = iterator.next();
			// checking if the given user ID is same as the user ID of the list salesDB
			if (s1.getUserId() == user_sales_ID) {
				// if matched printing the user ID ,his current Sales , Target Sales, Target on
				// screen
				System.out.println("  User ID   " + user_sales_ID + "   Current Sales   " + s1.getCurrentSales()
						+ "  Target Sales  " + s1.getTargetSales() + "   Target   " + s1.getTarget());
			}
		}

	}

	public void viewAllSales(List<SalesRep> salesDB) throws Exception {
		// checking if the given input is null or empty
		if (salesDB == null) {
			throw new Exception("Empty List");
		}
		// iterator operator for going through the list salesDB
		Iterator<SalesRep> iterator = salesDB.iterator();
		// Obtaining the elements of the list
		while (iterator.hasNext()) {

			// printing the user ID ,his current Sales , Target Sales, Target on screen
			SalesRep s1 = iterator.next();
			System.out.println("  User ID   " + s1.getUserId() + "   Current Sales   " + s1.getCurrentSales()
					+ "  Target Sales  " + s1.getTargetSales() + "   Target   " + s1.getTarget());
		}

	}

	public void viewRetail(String user_sales_ID, List<Retailer> retDB) throws Exception {
		boolean f = false;
		// checking if the given input is null or empty
		if (user_sales_ID == null || retDB == null) {
			throw new Exception("Invalid user Name or Invalid List");
		}

		for (Order order : Database.orderDB) {
			if (order.getuserID().contentEquals(user_sales_ID))
				f = true;
		}
		if (f == false) {
			throw new Exception("No such user Exists!!");
		}

		Iterator<Retailer> iterator = retDB.iterator();
		// Obtaining the elements of the list
		while (iterator.hasNext()) {
			Retailer s1 = iterator.next();
			if (s1.getUserId() == user_sales_ID) {
				// printing the user ID and the discount
				System.out.println("  User ID   " + user_sales_ID + "   Discount   " + s1.getDiscount());
			}
		}

	}

	public void viewAllRetail(List<Retailer> retDB) throws Exception {

		// checking if the given input is null or empty

		Iterator<Retailer> iterator = retDB.iterator();
		while (iterator.hasNext()) {

			Retailer s1 = iterator.next();
			// printing the user ID and the discount and retailer inventory
			System.out.println("  User ID   " + s1.getUserId() + "   Discount   " + s1.getDiscount() + " "
					+ s1.getRetailerInventory());
		}

	}

//OVERALL for specific user
	public void viewOverAllUser(Date entry, Date exit, String user_ID) throws Exception {
		boolean f = false;
		// checking if the given input is null or empty
		if (entry == null || exit == null || user_ID == null) {
			throw new Exception("Incorrect Date or user ID");
		}
		for (Order order : Database.orderDB) {
			if (order.getuserID().equals(user_ID))
				f = true;
		}
		if (f == false) {
			throw new Exception("No such user Exists!!");
		}
		System.out.println(
				"DATE\t\t\tUSER ID\t\t\tCAMPING\t\t\tGOLF\t\t\tMOUNTAINEERING\t\t\tOUTDOOR\t\t\tPERSONAL\t\t\tTOTAL QUANTITY\t\t\tREVENUE ");
		// Initialising the variable with default values
		long p1, p2, p3, p4, p5, t, n;
		Double rev, NetRev;
		p1 = p2 = p3 = p4 = p5 = t = n = 0;
		rev = NetRev = 0.0;
		// loop for going through the order database
		for (Order order : Database.orderDB) {
			p1 = p2 = p3 = p4 = p5 = t = 0;
			rev = 0.0;
			// Obtaining the date of that particular order
			Date d = order.getoInitTime();
			if (d.after(entry) && d.before(exit) && order.getuserID().contentEquals(user_ID)) {
				// loop for going through the product list database
				for (Product p : order.getProdList()) {
					if (p.getCat().toString().equals("CAMPING")) {
						p1 = p.getQtyOrder();
						rev = rev + p1 * p.getPrice();
					} else if (p.getCat().toString().equals("GOLF")) {
						p2 = p.getQtyOrder();
						rev = rev + p2 * p.getPrice();
					} else if (p.getCat().toString().equals("MOUNTAINEERING")) {
						p3 = p.getQtyOrder();
						rev = rev + p3 * p.getPrice();
					} else if (p.getCat().toString().equals("OUTDOOR")) {
						p4 = p.getQtyOrder();
						rev = rev + p4 * p.getPrice();
					} else if (p.getCat().toString().equals("PERSONAL")) {
						p5 = p.getQtyOrder();
						rev = rev + p5 * p.getPrice();
					} else
						System.out.println(user_ID + "has no product");
					t = p1 + p2 + p3 + p4 + p5;
					n += t;
					NetRev += rev;

				}

				System.out.print(d + "\t\t\t" + user_ID + "\t\t\t" + p1 + "\t\t\t" + p2 + "\t\t\t" + p3 + "\t\t\t" + p4
						+ "\t\t\t" + p5 + "\t\t\t" + t + "\t\t\t" + rev);
			}
			System.out.println();
			System.out.println();
			System.out.println(d + "\t\t\t" + user_ID + "\t\t\tTOTAL QUANTITY:\t" + n + "TOTAL REVENUE:\t" + NetRev);
		}
	}

//VIEW THE OVERALL SALES OF RETAILER AND SALES REPRESENTATIVE	

	public void viewOverAll(Date entry, Date exit) throws Exception {
		// checking if the given input is null or empty
		if (entry == null || exit == null) {
			throw new Exception("Incorrect Date or user ID");
		}
		System.out.println(
				"DATE\t\t\tUSER ID\t\t\tCAMPING\t\t\tGOLF\t\t\tMOUNTAINEERING\t\t\tOUTDOOR\t\t\tPERSONAL\t\t\tTOTAL QUANTITY\t\t\tREVENUE ");
		// initialising the variables
		long p1, p2, p3, p4, p5, t, n;
		Double rev, NetRev;
		p1 = p2 = p3 = p4 = p5 = t = n = 0;
		rev = NetRev = 0.0;
		// loop for going through the order database
		for (Order order : Database.orderDB) {
			p1 = p2 = p3 = p4 = p5 = t = 0;
			rev = 0.0;
			// obtaining the initiation date of that order
			Date d = order.getoInitTime();
			// check the given date from the order database is between entry and exit date
			if (d.after(entry) && d.before(exit)) {
				// loop for going through the product list of that order and comparing the
				// product with default products
				for (Product p : order.getProdList()) {
					if (p.getCat().toString().equals("CAMPING")) {
						p1 = p.getQtyOrder();// setting the no of quantity of only CAMPING item in p5 for that
												// particular order
						rev = rev + p1 * p.getPrice();
					} else if (p.getCat().toString().equals("GOLF")) {
						p2 = p.getQtyOrder();// setting the no of quantity of only GOLF item in p5 for that particular
												// order
						rev = rev + p2 * p.getPrice();
					} else if (p.getCat().toString().equals("MOUNTAINEERING")) {
						p3 = p.getQtyOrder();// setting the no of quantity of only MOUNTAINEERING item in p5 for that
												// particular order
						rev = rev + p3 * p.getPrice();
					} else if (p.getCat().toString().equals("OUTDOOR")) {
						p4 = p.getQtyOrder();// setting the no of quantity of only OUTDOOR item in p5 for that
												// particular order
						rev = rev + p4 * p.getPrice();
					} else if (p.getCat().toString().equals("PERSONAL")) {
						p5 = p.getQtyOrder();// setting the no of quantity of only PERSONAL item in p5 for that
												// particular order
						rev = rev + p5 * p.getPrice();
					} else
						System.out.println(order.getuserID() + "has no product");
					t = p1 + p2 + p3 + p4 + p5;
					n += t;
					NetRev += rev;

				}

				System.out.print(d + "\t\t\t" + order.getuserID() + "\t\t\t" + p1 + "\t\t\t" + p2 + "\t\t\t" + p3
						+ "\t\t\t" + p4 + "\t\t\t" + p5 + "\t\t\t" + t + "\t\t\t" + rev);
			}
			System.out.println();
			System.out.println();
			System.out.println(
					d + "\t\t\t" + order.getuserID() + "\t\t\tTOTAL QUANTITY:\t" + n + "TOTAL REVENUE:\t" + NetRev);
		}

	}

// VIEW REVENUE OF SPECIFIC PRODUCT MENTIONED WITHIN DUE DATE

	public void viewRevenueSpecificProduct(Date entry, Date exit, String prod) throws Exception {

		// checking if the given input is null or empty
		if (entry == null || exit == null || prod == null) {
			throw new Exception("Incorrect Date or product name");
		}
		System.out.println("DATE\t\t\tUSER ID\t\t\tPRODUCT\t\t\tQUANTITY\t\t\tREVENUE ");
		// Initialising the particular quantity,net quantity,Revenue,Net Revenue by zero
		long m, Qty;
		Double rev, NetRev;
		m = Qty = 0;
		rev = NetRev = 0.0;
		// loop for going through order list
		for (Order order : Database.orderDB) {

			Date d = order.getoInitTime();
			// check the given date from the order database is between entry and exit date
			if (d.after(entry) && d.before(exit)) {

				// loop for going through the product database
				for (Product p : order.getProdList()) {
					rev = 0.0;
					// check if the input product name is equal to the product name from the product
					// list
					if (p.getCat().toString().equals(prod)) {
						// finding out the quantity of that product
						m = p.getQtyOrder();
						// finding out the net price of the m quantity of product
						rev = m * p.getPrice();
						System.out.print(d + "\t\t\t" + order.getuserID() + "\t\t\t" + p.getCat() + "\t\t\t" + m
								+ "\t\t\t" + rev);
						// summing up all the product quantity and revenue
						Qty += m;
						NetRev += rev;
					}
				}
			}
		}
		// printing the net revenue and quantity
		System.out.print("\t\t\t\t\t\t" + prod + "\t\t\tTOTAL QUANTITY:\t" + Qty + "\t\t\tTOTAL REVENUE:\t" + NetRev);

	}

//IMPORTANT DATA FOR OUTDOOR PROTECTION UNIT COLOR CODE,PERCENTAGE GROWTH
	public void viewRevenueOutdoor(Date entry, Date exit, String prod) throws Exception {
		// checking if the given input is null or empty
		if (entry == null || exit == null || prod == null) {
			throw new Exception("Incorrect Date or product");
		}
		// INITIALISING THE START YEAR AND END YEAR IN INTEGER FORMAT
		int startYear = entry.getYear();
		int endYear = exit.getYear();
		int j = 0;
		// CONSIDERING THE REVENUE DATA FOR PREVIOUS MONTH,QUARTER,YEAR IS ZERO
		double prevM = 0.0, prevQ = 0.0, prevY;

		// DECLARING AMOUNT CHANGE, GROWTH , COLOR CODE ARRAY FOR MONTH TO MONTH BASIS
		double[] amtM = new double[12];
		double[] perChngM = new double[12];
		String[] codeM = new String[12];

		// DECLARING AMOUNT CHANGE, GROWTH , COLOR CODE ARRAY FOR QUARTER TO QUARTER
		// BASIS
		double[] amtQ = new double[4];
		double[] perChngQ = new double[4];
		String[] codeQ = new String[4];

		// DECLARING AMOUNT CHANGE, GROWTH , COLOR CODE ARRAY FOR YEAR TO YEAR BASIS
		int[] amtY = new int[endYear - startYear];
		int[] perChngY = new int[endYear - startYear];
		String[] codeY = new String[endYear - startYear];

		long[] arrQty = new long[12];
		double[] arrRev = new double[12];

		// loop from start year to end year
		for (int i = startYear; i <= endYear; i++) {
			// loop for going through order list
			for (Order order : Database.orderDB) {
				// finding out the date of that order
				Date d = order.getoInitTime();
				// checking if the date is within the entry and exit
				if (d.after(entry) && d.before(exit)) {
					// loop through product list in the given order which may contain different
					// products with certain quantity
					for (Product p : order.getProdList()) {
						// checking if the product matches the given product name
						if (p.getCat().toString().equals(prod)) {
							// loop for going through all month
							for (j = 0; j <= 11; j++) {
								// finding out revenue data,quantity for all the 12 months
								if (j == d.getMonth()) {
									arrQty[j] += p.getQtyOrder();
									arrRev[j] += p.getQtyOrder() * p.getPrice();
								}
							}
						}
					}
				}
			}
			// displaying year
			System.out.println("YEAR:\t" + (startYear + 1900));
			// month to month change for this year
			System.out
					.println("MONTH\t\t\tREVENUE\t\t\t(M-M)AMOUNT CHANGE\t\t\t(M-M)PERCENTAGE GROWTH\t\t\tCOLOR CODE");

			amtM[0] = arrRev[0] - prevM;
			// loop for going from January to December
			for (j = 1; j <= 11; j++) {
				// initialising the amount change of current month and previous month
				amtM[j] = arrRev[j] - arrRev[j - 1];
				perChngM[j] = 100 * (arrRev[j] - arrRev[j - 1]) / arrRev[j - 1];
				// checking the necessary condition for color code
				if (perChngM[j] >= 10.0)
					codeM[j] = "GREEN";
				else if (perChngM[j] >= 2.0 && perChngM[j] <= 10)
					codeM[j] = "AMBER";
				else
					codeM[j] = "RED";
			}
			for (j = 0; j <= 11; j++)
				System.out.println(j + "        " + arrRev[j] + "        " + amtM[j] + "        "
						+ Math.round(perChngM[j]) + "       " + codeM[j]);
			prevM = arrRev[11];

			// quarter to quarter change for this year
			amtQ[0] = (arrRev[0] + arrRev[1] + arrRev[2]);
			amtQ[1] = (arrRev[3] + arrRev[4] + arrRev[5]);
			amtQ[2] = (arrRev[6] + arrRev[7] + arrRev[8]);
			amtQ[3] = (arrRev[9] + arrRev[10] + arrRev[11]);

			perChngQ[0] = 100 * (amtQ[0] - prevQ) / amtQ[0];
			perChngQ[1] = 100 * (amtQ[1] - amtQ[0]) / amtQ[0];
			perChngQ[2] = 100 * (amtQ[2] - amtQ[1]) / amtQ[1];
			perChngQ[3] = 100 * (amtQ[3] - amtQ[2]) / amtQ[2];

			for (j = 0; j <= 3; j++) {
				if (perChngQ[j] >= 10.0)
					codeQ[j] = "GREEN";
				else if (perChngQ[j] >= 2.0 && perChngQ[j] <= 10)
					codeQ[j] = "AMBER";
				else
					codeQ[j] = "RED";
			}

			System.out.println();
			System.out.println();
			System.out.println("QUARTER 1" + "\t\t\t" + (amtQ[0]) + "\t\t\t" + (amtQ[0] - prevQ) + "\t\t\t"
					+ perChngQ[0] + "%    " + codeQ[0]);
			System.out.println("QUARTER 2" + "\t\t\t" + (amtQ[1]) + "\t\t\t" + (amtQ[1] - amtQ[0]) + "\t\t\t  "
					+ Math.round(perChngQ[1]) + "%      " + codeQ[1]);
			System.out.println("QUARTER 3" + "\t\t\t" + (amtQ[2]) + "\t\t\t" + (amtQ[2] - amtQ[1]) + "\t\t\t  "
					+ Math.round(perChngQ[2]) + "%      " + codeQ[2]);
			System.out.println("QUARTER 4" + "\t\t\t" + (amtQ[3]) + "\t\t\t" + (amtQ[3] - amtQ[2]) + "\t\t\t  "
					+ Math.round(perChngQ[3]) + "%      " + codeQ[3]);
			prevQ = amtQ[3];

			// year to year basis

		}

	}

}
/*
 * public void amtchange() {
 * System.out.println("CURRENT MONTH     PREVIOUS MONTH   AMOUNT CHANGE");
 * System.out.println(Database.reportRevenueDB.totalMonth+"        "+obj.
 * prevMonth+"      "+(obj.totalMonth-obj.prevMonth));
 * 
 * System.out.println("CURRENT QUARTER    PREVIOUS QUARTER  AMOUNT CHANGE");
 * System.out.println(totalQuarter+"    "+prevQuarter+"      "+(totalQuarter-
 * prevQuarter));
 * 
 * System.out.println("CURRENT YEAR     PREVIOUS YEAR   AMOUNT CHANGE");
 * System.out.println(totalYear+"        "+prevYear+"      "+(totalYear-prevYear
 * )); } public void percentageChange() {
 * System.out.println("CURRENT MONTH     PREVIOUS MONTH   PERCENTAGE CHANGE");
 * System.out.println(totalMonth+"        "+prevMonth+"      "+(totalMonth-
 * prevMonth)/totalMonth);
 * 
 * System.out.println("CURRENT QUARTER    PREVIOUS QUARTER   PERCENTAGE CHANGE"
 * ); System.out.println(totalQuarter+"    "+prevQuarter+"      "+(totalQuarter-
 * prevQuarter)/totalQuarter);
 * 
 * System.out.println("CURRENT YEAR     PREVIOUS YEAR   PERCENTAGE CHANGE");
 * System.out.println(totalYear+"        "+prevYear+"      "+(totalYear-prevYear
 * )/totalYear);
 * 
 * 
 * } public void colorCode() { System.out.println("MONTH COLOR CODE"); Double
 * changeM=(totalMonth-prevMonth)/totalMonth; if(changeM>=10.0)
 * System.out.println("GREEN"); else if(changeM>=2.0 && changeM<=10)
 * System.out.println("AMBER"); else System.out.println("RED");
 * 
 * System.out.println("QUARTER COLOR CODE"); Double
 * changeQ=(totalQuarter-prevQuarter)/totalQuarter; if(changeQ>=10.0)
 * System.out.println("GREEN"); else if(changeQ>=2.0 && changeQ<=10)
 * System.out.println("AMBER"); else System.out.println("RED");
 * 
 * System.out.println("YEAR COLOR CODE"); Double
 * changeY=(totalYear-prevYear)/totalYear; if(changeY>=10.0)
 * System.out.println("GREEN"); else if(changeY>=2.0 && changeY<=10)
 * System.out.println("AMBER"); else System.out.println("RED"); }
 */
