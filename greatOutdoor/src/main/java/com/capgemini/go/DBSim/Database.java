package com.capgemini.go.DBSim;

//import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.go.model.Address;
import com.capgemini.go.model.Cart;
import com.capgemini.go.model.FreqOrderDB;
import com.capgemini.go.model.GoAdmin;
import com.capgemini.go.model.Manufacturer;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.ProductMaster;
import com.capgemini.go.model.Report_Revenue;
import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.RetailerInventoryItem;
import com.capgemini.go.model.SalesRep;
import com.capgemini.go.model.User;

public class Database {

	public static List<Address> addressDB = new ArrayList<Address>();
	public static List<Cart> cartDB = new ArrayList<Cart>();
	public static List<FreqOrderDB> freqOrderDB = new ArrayList<FreqOrderDB>();
	public static List<GoAdmin> goDB = new ArrayList<GoAdmin>();
	public static List<Manufacturer> manfactDB = new ArrayList<Manufacturer>();
	public static List<Product> productDB = new ArrayList<Product>();
	public static List<ProductMaster> productMasterDB = new ArrayList<ProductMaster>();
	public static List<Report_Revenue> reportRevenueDB = new ArrayList<Report_Revenue>();
	public static List<Retailer> retailerDB = new ArrayList<Retailer>();
	public static List<RetailerInventoryItem> riItemDB = new ArrayList<RetailerInventoryItem>();
	public static List<SalesRep> salesRepDB = new ArrayList<SalesRep>();
	public static List<User> userDB = new ArrayList<User>();
	public static List<Order> orderDB = new ArrayList<Order>();
	public static List<OrderReturn> orderReturnDB = new ArrayList<OrderReturn>();

	public void initiateAddressDB()

	{
		/*
		 * Address( String country, String city, String state, int zip, String
		 * buildingNo, String retailerId, boolean baseAddressStatus)
		 */
		addressDB.add(
				new Address("India", "Kolkata", "West Bengal", 700059, "128", retailerDB.get(0).getUserId(), true));
		addressDB.add(
				new Address("India", "Bangalore", "Karnataka", 500059, "236", retailerDB.get(0).getUserId(), false));
		addressDB.add(new Address("India", " New Delhi", "Delhi", 600021, "128", retailerDB.get(1).getUserId(), true));
		addressDB.add(new Address("India", "Chennai", "Madras", 400052, "106", retailerDB.get(1).getUserId(), false));

	}

	public void initiatecartDB() {

		// Cart(String retailerId, Map<Product, Integer> cartList, Double totalPrice)
		Map<Product, Integer> cartProd1 = new HashMap<Product, Integer>();
		cartProd1.put(productDB.get(0), new Integer(2));
		cartProd1.put(productDB.get(1), new Integer(3));
		Double TotalPrice = 0.0D;
		for (Map.Entry<Product, Integer> entry : cartProd1.entrySet()) {
			TotalPrice = TotalPrice + (entry.getKey().getPrice() * entry.getValue());
		}

		cartDB.add(new Cart(retailerDB.get(0).getUserId(), cartProd1, TotalPrice));

		Map<Product, Integer> cartProd2 = new HashMap<Product, Integer>();
		cartProd2.put(productDB.get(0), new Integer(1));
		cartProd2.put(productDB.get(1), new Integer(2));
		TotalPrice = 0.0D;
		for (Map.Entry<Product, Integer> entry : cartProd2.entrySet()) {
			TotalPrice = TotalPrice + (entry.getKey().getPrice() * entry.getValue());
		}

		cartDB.add(new Cart(retailerDB.get(0).getUserId(), cartProd2, TotalPrice));

	}

	public void initiateFreqOrderDB() {

		// FreqOrderDB(String retailerId, Map<Product, Address> freqOrderProductList)
		Map<Product, Address> freqOrder1 = new HashMap<Product, Address>();
		freqOrder1.put(productDB.get(0), addressDB.get(0));
		freqOrder1.put(productDB.get(1), addressDB.get(0));

		freqOrderDB.add(new FreqOrderDB(retailerDB.get(0).getUserId(), freqOrder1));

		Map<Product, Address> freqOrder2 = new HashMap<Product, Address>();
		freqOrder2.put(productDB.get(0), addressDB.get(1));
		freqOrder2.put(productDB.get(1), addressDB.get(1));

		freqOrderDB.add(new FreqOrderDB(retailerDB.get(0).getUserId(), freqOrder2));
	}

	public void initiateGoAdminDB() {

		/*
		 * GoAdmin(String userId, String userName, String userMail, long userNumber,
		 * boolean activeStatus, String password, int userCategory)
		 */

		User user0 = userDB.get(0);
		goDB.add(new GoAdmin(user0.getUserId(), user0.getUserName(), user0.getUserMail(), user0.getUserNumber(), false,
				user0.getPassword(), user0.getUserCategory()));
		User user1 = userDB.get(1);
		goDB.add(new GoAdmin(user1.getUserId(), user1.getUserName(), user1.getUserMail(), user1.getUserNumber(), false,
				user1.getPassword(), user1.getUserCategory()));

	}

	public void initiateManufacturerDB() {

		// Manufacturer( String mLoc, Date mEstYear, String mName, String mMail)
		try {
			manfactDB.add(new Manufacturer("Australia", new SimpleDateFormat("YYYY").parse("1975"), "Adidas",
					"info@adidas.com"));
			manfactDB.add(
					new Manufacturer("London", new SimpleDateFormat("YYYY").parse("1926"), "Bata", "info@bata.com"));
			manfactDB.add(
					new Manufacturer("India", new SimpleDateFormat("YYYY").parse("1936"), "Tata", "info@tata.com"));
			manfactDB.add(new Manufacturer("USA", new SimpleDateFormat("YYYY").parse("1955"), "Nike", "info@nike.com"));
			manfactDB.add(
					new Manufacturer("USA", new SimpleDateFormat("YYYY").parse("1988"), "Younvex", "info@younvex.com"));

		} catch (ParseException pe) {

		}
	}

	public void initiateOrderDB() throws CloneNotSupportedException {

		/*
		 * Order( String userID, Address address, Date oInitTime, Date oRecTime, Date
		 * oDisTime, int orderType, List<Product> prodList, boolean
		 * dispatched_status,List<Product> cancelledList)
		 */

		// creating product variable for prodlist1
		Product p1 = Database.productDB.get(0);
		Product p2 = Database.productDB.get(1);
		Product p3 = Database.productDB.get(2);
		Product p4 = Database.productDB.get(3);
		Product p5 = Database.productDB.get(4);

		// setting the value for prodlist1
		p1.setQtyOrder(1);
		p2.setQtyOrder(3);
		p3.setQtyOrder(2);
		p4.setQtyOrder(5);
		p5.setQtyOrder(7);

		// creating product variable for prodlist2
		Product p6 = (Product) p1.clone();
		Product p7 = (Product) p2.clone();
		Product p8 = (Product) p3.clone();
		Product p9 = (Product) p4.clone();
		Product p10 = (Product) p5.clone();

		// setting the value for prodlist2
		p6.setQtyOrder(4);
		p7.setQtyOrder(5);
		p8.setQtyOrder(6);
		p9.setQtyOrder(8);
		p10.setQtyOrder(9);
		// creating product variable for prodlist3
		Product p11 = (Product) p1.clone();
		Product p12 = (Product) p2.clone();
		Product p13 = (Product) p3.clone();
		Product p14 = (Product) p4.clone();
		Product p15 = (Product) p5.clone();

		// setting the value for prodlist3
		p11.setQtyOrder(6);
		p12.setQtyOrder(6);
		p13.setQtyOrder(7);
		p14.setQtyOrder(10);
		p15.setQtyOrder(12);

		// creating product variable for prodlist4
		Product p16 = (Product) p1.clone();
		Product p17 = (Product) p2.clone();
		Product p18 = (Product) p3.clone();
		Product p19 = (Product) p4.clone();
		Product p20 = (Product) p5.clone();

		// setting the value for prodlist4
		p16.setQtyOrder(9);
		p17.setQtyOrder(4);
		p18.setQtyOrder(2);
		p19.setQtyOrder(11);
		p20.setQtyOrder(2);
		// creating product variable for prodlist5
		Product p21 = (Product) p1.clone();
		Product p22 = (Product) p2.clone();
		Product p23 = (Product) p3.clone();
		Product p24 = (Product) p4.clone();
		Product p25 = (Product) p5.clone();

		// setting the value for prodlist5
		p21.setQtyOrder(2);
		p22.setQtyOrder(9);
		p23.setQtyOrder(11);
		p24.setQtyOrder(15);
		p25.setQtyOrder(7);
		// creating product variable for prodlist6
		Product p26 = (Product) p1.clone();
		Product p27 = (Product) p2.clone();
		Product p28 = (Product) p3.clone();
		Product p29 = (Product) p4.clone();
		Product p30 = (Product) p5.clone();

		// setting the value for prodlist6
		p26.setQtyOrder(10);
		p27.setQtyOrder(15);
		p28.setQtyOrder(16);
		p29.setQtyOrder(18);
		p30.setQtyOrder(10);

		// product list1
		List<Product> prodList1 = new ArrayList<Product>();
		prodList1.add(p1);
		prodList1.add(p2);
		prodList1.add(p3);
		prodList1.add(p4);
		prodList1.add(p5);
		// product list2
		List<Product> prodList2 = new ArrayList<Product>();
		prodList2.add(p6);
		prodList2.add(p7);
		prodList2.add(p8);
		prodList2.add(p9);
		prodList2.add(p10);
		// product list3
		List<Product> prodList3 = new ArrayList<Product>();
		prodList3.add(p11);
		prodList3.add(p12);
		prodList3.add(p13);
		prodList3.add(p14);
		prodList3.add(p15);
		// product list4
		List<Product> prodList4 = new ArrayList<Product>();
		prodList4.add(p16);
		prodList4.add(p17);
		prodList4.add(p18);
		prodList4.add(p19);
		prodList4.add(p20);
		// product list5
		List<Product> prodList5 = new ArrayList<Product>();
		prodList5.add(p21);
		prodList5.add(p22);
		prodList5.add(p23);
		prodList5.add(p24);
		prodList5.add(p25);
		// product list6
		List<Product> prodList6 = new ArrayList<Product>();
		prodList6.add(p26);
		prodList6.add(p27);
		prodList6.add(p28);
		prodList6.add(p29);
		prodList6.add(p30);

		List<Product> cancelledList1 = new ArrayList<Product>();
		cancelledList1.add(p1);

		List<Product> cancelledList2 = new ArrayList<Product>();
		cancelledList2.add(p2);
		try {
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("dd/MM/YYYY").parse("01/01/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("03/03/2019"), null, null, 1, prodList2, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("04/04/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("05/05/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("07/07/2019"), null, null, 1, prodList5, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2019"), null, null, 2, prodList6, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("09/09/2019"), null, null, 1, prodList3, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("11/11/2019"), null, null, 1, prodList2, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("13/01/2019"), null, null, 1, prodList2, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("14/01/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("15/02/2019"), null, null, 1, prodList1, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("16/03/2019"), null, null, 2, prodList6, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("17/01/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("18/02/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("19/03/2019"), null, null, 1, prodList2, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("21/05/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("22/06/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("23/07/2019"), null, null, 1, prodList5, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("24/08/2019"), null, null, 2, prodList6, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("25/09/2019"), null, null, 1, prodList3, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("26/10/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("27/11/2019"), null, null, 1, prodList2, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("DD/MM/yyyy").parse("28/12/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("29/01/2019"), null, null, 1, prodList2, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("30/01/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("01/02/2019"), null, null, 1, prodList1, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("02/08/2019"), null, null, 2, prodList6, false, null));

			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/yyyy").parse("17/01/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/YYYY").parse("18/02/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/yyyy").parse("19/03/2019"), null, null, 1, prodList2, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("30/05/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("14/06/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("25/07/2019"), null, null, 1, prodList5, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("25/08/2019"), null, null, 2, prodList6, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("25/09/2019"), null, null, 1, prodList3, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("16/11/2019"), null, null, 1, prodList2, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/YYYY").parse("19/12/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("13/06/2019"), null, null, 1, prodList2, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("28/02/2019"), null, null, 1, prodList1, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("17/03/2019"), null, null, 2, prodList6, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("19/08/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("14/02/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("10/03/2019"), null, null, 1, prodList2, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/YYYY").parse("21/04/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("25/05/2019"), null, null, 1, prodList3, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("01/06/2019"), null, null, 2, prodList1, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("15/07/2019"), null, null, 1, prodList1, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("02/08/2019"), null, null, 2, prodList6, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("16/05/2019"), null, null, 1, prodList3, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("03/10/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("27/12/2019"), null, null, 1, prodList5, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("28/12/2019"), null, null, 2, prodList4, false, null));
			orderDB.add(new Order(Database.retailerDB.get(0).getUserId(), Database.addressDB.get(0),
					new SimpleDateFormat("DD/MM/YYYY").parse("29/05/2019"), null, null, 1, prodList2, false,
					cancelledList2));
			orderDB.add(new Order(Database.salesRepDB.get(0).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("30/05/2019"), null, null, 2, prodList2, false, null));
			orderDB.add(new Order(Database.retailerDB.get(1).getUserId(), Database.addressDB.get(1),
					new SimpleDateFormat("DD/MM/YYYY").parse("01/12/2019"), null, null, 1, prodList1, false,
					cancelledList1));
			orderDB.add(new Order(Database.salesRepDB.get(1).getUserId(), null,
					new SimpleDateFormat("dd/MM/yyyy").parse("22/11/2019"), null, null, 2, prodList6, false, null));

		} catch (ParseException ex) {

		}

	}

	public void initiateOrderReturnDB() {
		/*
		 * OrderReturn( String retailerId, Map<Product,Integer> returnList, String
		 * reason, Date rTimeStamp, String orderId, boolean orderPendingStatus)
		 */
		// Reason : Wrong Product Shiped or Incomplete Product
		Map<Product, Integer> returnList1 = new HashMap<Product, Integer>();
		returnList1.put(Database.productDB.get(0), 1);
		returnList1.put(Database.productDB.get(1), 2);

		Map<Product, Integer> returnList2 = new HashMap<Product, Integer>();
		returnList2.put(Database.productDB.get(0), 1);
		returnList2.put(Database.productDB.get(2), 9);
		returnList2.put(Database.productDB.get(3), 2);
		returnList2.put(Database.productDB.get(1), 5);

		Map<Product, Integer> returnList3 = new HashMap<Product, Integer>();
		returnList3.put(Database.productDB.get(0), 1);
		returnList3.put(Database.productDB.get(3), 8);
		returnList3.put(Database.productDB.get(1), 6);

		Map<Product, Integer> returnList4 = new HashMap<Product, Integer>();
		returnList4.put(Database.productDB.get(0), 1);
		returnList4.put(Database.productDB.get(3), 3);
		returnList4.put(Database.productDB.get(2), 3);

		Map<Product, Integer> returnList5 = new HashMap<Product, Integer>();
		returnList5.put(Database.productDB.get(0), 1);
		returnList5.put(Database.productDB.get(2), 4);

		Map<Product, Integer> returnList6 = new HashMap<Product, Integer>();
		returnList6.put(Database.productDB.get(3), 5);

		try {
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList1,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2018"),
					Database.orderDB.get(0).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList2,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("06/02/2018"),
					Database.orderDB.get(1).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(1).getUserId(), returnList3, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("08/03/2018"), Database.orderDB.get(2).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(1).getUserId(), returnList4,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/2018"),
					Database.orderDB.get(3).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList5, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("09/05/2018"), Database.orderDB.get(4).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList6, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2018"), Database.orderDB.get(5).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList1,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("05/07/2018"),
					Database.orderDB.get(6).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList2,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("06/08/2018"),
					Database.orderDB.get(7).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(1).getUserId(), returnList3, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2018"), Database.orderDB.get(8).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(1).getUserId(), returnList4,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2018"),
					Database.orderDB.get(9).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList5, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2018"), Database.orderDB.get(10).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList6, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("13/12/2018"), Database.orderDB.get(11).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList1,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("30/01/2019"),
					Database.orderDB.get(12).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList2,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("12/02/2019"),
					Database.orderDB.get(13).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(1).getUserId(), returnList3, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("04/03/2019"), Database.orderDB.get(14).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(1).getUserId(), returnList4,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("02/04/2019"),
					Database.orderDB.get(15).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList5, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("25/05/2019"), Database.orderDB.get(16).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.retailerDB.get(0).getUserId(), returnList6, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("23/06/2019"), Database.orderDB.get(17).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList1,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019"),
					Database.orderDB.get(18).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList2,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2019"),
					Database.orderDB.get(19).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(1).getUserId(), returnList3, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2019"), Database.orderDB.get(20).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(1).getUserId(), returnList4,
					"Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("29/10/2019"),
					Database.orderDB.get(21).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList5, "Incomplete Product",
					new SimpleDateFormat("dd/MM/yyyy").parse("17/11/2019"), Database.orderDB.get(22).getoID(), true));
			orderReturnDB.add(new OrderReturn(Database.salesRepDB.get(0).getUserId(), returnList6, "Delivery Delayed",
					new SimpleDateFormat("dd/MM/yyyy").parse("17/12/2019"), Database.orderDB.get(23).getoID(), true));

		} catch (ParseException pe) {

		}
	}

	public void initiateProductDB() {
		/*
		 * Product( String pName, String pColor, String pSpecs, String pDimesions,
		 * double price, long qty, long qtyOrder, Manufacturer manufacturer,Date
		 * manfacDate,String cat)
		 */
		try {
			productDB.add(new Product("Mountaineering Shoe", "Blue", "Light Weight", "6 inch", 4000.00, 100, 0,
					manfactDB.get(0), new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING"));
			productDB.add(new Product("Golf Ball", "White", "Spotted", "2cm dia", 400.00, 100, 0, manfactDB.get(3),
					new SimpleDateFormat("DD/MM/YY").parse("10/08/2007"), "GOLF"));
			productDB.add(new Product("Tent", "Brown", "Durable", "30 m", 2500.00, 100, 0, manfactDB.get(3),
					new SimpleDateFormat("DD/MM/YY").parse("10/02/2003"), "CAMPING"));
			productDB.add(new Product("Golf stick", "White", "Alluminium Body", "36 inch", 500.00, 100, 0,
					manfactDB.get(4), new SimpleDateFormat("DD/MM/YY").parse("01/01/2007"), "GOLF"));
			productDB.add(new Product("UV-Protected Glass", "Black", "Light Weight", "3 inch", 1500.00, 100, 0,
					manfactDB.get(0), new SimpleDateFormat("DD/MM/YY").parse("20/05/2000"), "OUTDOOR"));
			productDB.add(new Product("Watch", "Brown", "Light Weight", "2 inch", 1000.00, 100, 0, manfactDB.get(4),
					new SimpleDateFormat("DD/MM/YY").parse("10/01/2000"), "PERSONAL"));
		} catch (ParseException pe) {

		}

	}

	public void initiateProductMasterDB() {

		/*
		 * ProductMaster(String userId, String userName, String userMail, long
		 * userNumber, boolean activeStatus, String password, int userCategory)
		 */

		productMasterDB.add(new ProductMaster(userDB.get(6).getUserId(), userDB.get(6).getUserName(),
				userDB.get(6).getUserMail(), userDB.get(6).getUserNumber(), false, userDB.get(6).getPassword(), 4));
		productMasterDB.add(new ProductMaster(userDB.get(7).getUserId(), userDB.get(7).getUserName(),
				userDB.get(7).getUserMail(), userDB.get(7).getUserNumber(), false, userDB.get(7).getPassword(), 4));
	}

	public void initiateReport_revenue() {
		/*
		 * Report_Revenue(double totalMonth, double totalQuarter, double totalYear,
		 * double prevMonth, double prevQuarter, double prevYear, double
		 * percentGrowthMonthly, double percentGrowthQuarter, double percentGrowthYear)
		 */
		reportRevenueDB.add(new Report_Revenue(12.00, 3.00, 22.0, 56.0, 555.0, 82.0, 65.0, 45.0, 63.0));
		reportRevenueDB.add(new Report_Revenue(978.00, 325.00, 145.0, 564.0, 5755.0, 84.0, 78.0, 75.0, 13.0));
		reportRevenueDB.add(new Report_Revenue(982.00, 789.00, 23212.0, 5456.0, 5785.0, 42.0, 69.0, 55.0, 46.0));
		reportRevenueDB.add(new Report_Revenue(445.00, 93052.00, 96632.0, 3121.0, 5785.0, 42.0, 85.0, 96.0, 98.0));
		reportRevenueDB.add(new Report_Revenue(4635.00, 96548.00, 5642.0, 36891.0, 4585.0, 45.0, 87.0, 46.0, 96.0));

	}

	public void initiateRetailerDB() {

		/*
		 * Retailer(String userId, String userName, String userMail, long userNumber,
		 * boolean activeStatus, String password, int userCategory, double discount,
		 * Address address, List<RetailerInventoryItem> retailerInventory)
		 */

		try {
			List<RetailerInventoryItem> rii = new ArrayList<RetailerInventoryItem>();
			rii.add(new RetailerInventoryItem(productDB.get(0), "oid1",
					new SimpleDateFormat("HH:mm:ss").parse("12:45:20"),
					new SimpleDateFormat("HH:mm:ss").parse("12:45:20"), userDB.get(4).getUserId()));
			List<RetailerInventoryItem> rii1 = new ArrayList<RetailerInventoryItem>();
			rii1.add(new RetailerInventoryItem(productDB.get(1), "oid2",
					new SimpleDateFormat("HH:mm:ss").parse("08:45:20"),
					new SimpleDateFormat("HH:mm:ss").parse("11:45:20"), userDB.get(5).getUserId()));
			retailerDB.add(new Retailer(userDB.get(2).getUserId(), userDB.get(2).getUserName(),
					userDB.get(2).getUserMail(), userDB.get(2).getUserNumber(), userDB.get(2).isActiveStatus(),
					userDB.get(2).getPassword(), userDB.get(2).getUserCategory(), 15.0, rii));
			retailerDB.add(new Retailer(userDB.get(3).getUserId(), userDB.get(3).getUserName(),
					userDB.get(3).getUserMail(), userDB.get(3).getUserNumber(), userDB.get(3).isActiveStatus(),
					userDB.get(3).getPassword(), userDB.get(3).getUserCategory(), 25.0, rii1));
		} catch (ParseException pe) {

		}
	}

	public void initiateRetailerInventoryDB() {

		/*
		 * RetailerInventoryItem(Product product, String orderId, Date shelfTimeIn, Date
		 * shelfTimeOut, String retailerId)
		 */
		try {
			riItemDB.add(new RetailerInventoryItem(productDB.get(0), "oid2",
					new SimpleDateFormat("HH:mm:ss").parse("12:45:20"),
					new SimpleDateFormat("HH:mm:ss").parse("02:45:20"), userDB.get(4).getUserId()));
			riItemDB.add(new RetailerInventoryItem(productDB.get(1), "oid1",
					new SimpleDateFormat("HH:mm:ss").parse("04:45:30"),
					new SimpleDateFormat("HH:mm:ss").parse("02:55:24"), userDB.get(5).getUserId()));
		} catch (ParseException e) {

		}
	}
	/*
	 * String userId, String userName, String userMail, long userNumber, boolean
	 * activeStatus, String password, int userCategory, Double sBonus, String
	 * target, Double currentSales, Double targetSales) { super(userId, userName,
	 * userMail, userNumber, activeStatus, password, userCategory
	 */

	public void initiateSalesRepDB() {
		salesRepDB.add(new SalesRep(userDB.get(4).getUserId(), userDB.get(4).getUserName(), userDB.get(4).getUserMail(),
				userDB.get(4).getUserNumber(), userDB.get(4).isActiveStatus(), userDB.get(4).getPassword(),
				userDB.get(4).getUserCategory(), 100.0, " MET ", 200.0, 200.0));
		salesRepDB.add(new SalesRep(userDB.get(8).getUserId(), userDB.get(8).getUserName(), userDB.get(8).getUserMail(),
				userDB.get(8).getUserNumber(), userDB.get(8).isActiveStatus(), userDB.get(8).getPassword(),
				userDB.get(8).getUserCategory(), 50.0, " NOT MET ", 100.0, 200.0));
		salesRepDB.add(new SalesRep(userDB.get(5).getUserId(), userDB.get(5).getUserName(), userDB.get(5).getUserMail(),
				userDB.get(5).getUserNumber(), userDB.get(5).isActiveStatus(), userDB.get(5).getPassword(),
				userDB.get(5).getUserCategory(), 20.0, " EXCEEDED ", 200.0, 100.0));

	}

	public void initiateUserDB() {

		/*
		 * User(String userId, String userName, String userMail, long userNumber,
		 * boolean activeStatus, String password, int userCategory)
		 */
		userDB.add(new User("PM01", "Ayushi", "ayushi@gmail.com", 9432560163L, true, "1234", 1));
		userDB.add(new User("PM02", "Agnibha", "agnibha@gmail.com", 8017354644L, false, "5649", 1));
		userDB.add(new User("RT01", "Rintu", "rintu@yahoo.com", 9985362451L, true, "2534", 2));
		userDB.add(new User("RT02", "Aniket", "aniket@hotmail.com", 943578210L, true, "1784", 2));
		userDB.add(new User("SR01", "Kunal", "kunal@yahoo.com", 9782563250L, true, "8796", 3));
		userDB.add(new User("SR02", "Anshu", "anshu@gmail.com", 2342234251L, false, "7849", 3));
		userDB.add(new User("GA01", "Charu", "charu@gmail.com", 4514451400L, true, "5689", 4));
		userDB.add(new User("GA02", "Shalu", "shalu@gmail.com", 9432218050L, false, "1897", 4));
		userDB.add(new User("SR03", "Vikas", "vikas@yahoo.com", 9782563255L, true, "8716", 3));

	}

	// Initiate all Database
	public static void initiateAllDB() throws CloneNotSupportedException {
		Database db = new Database();
		db.initiateUserDB();
		db.initiateSalesRepDB();
		db.initiateProductMasterDB();
		db.initiateManufacturerDB();
		db.initiateProductDB();
		db.initiateRetailerInventoryDB();
		db.initiateRetailerDB();
		db.initiateAddressDB();
		db.initiatecartDB();
		db.initiateOrderDB();
		db.initiateOrderReturnDB();
		db.initiateGoAdminDB();
		db.initiateFreqOrderDB();
		db.initiateReport_revenue();
	}

	// Empty the Databse
	public static void clearAllDB() {
		userDB.clear();
		salesRepDB.clear();
		productMasterDB.clear();
		manfactDB.clear();
		productDB.clear();
		riItemDB.clear();
		retailerDB.clear();
		addressDB.clear();
		cartDB.clear();
		orderDB.clear();
		orderReturnDB.clear();
		goDB.clear();
		freqOrderDB.clear();
		reportRevenueDB.clear();
	}

}
