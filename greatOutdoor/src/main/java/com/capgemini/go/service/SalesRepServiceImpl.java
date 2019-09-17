package com.capgemini.go.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.SalesRep;
import com.capgemini.go.model.User;

public class SalesRepServiceImpl implements SalesRepService {

	public boolean registration(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory) {

		for (User u : Database.userDB) {
			if (u.getUserId().equals(userId)) {
				System.out.println("ser already registered");
				return false;
			}
		}
		Database.userDB.add(new User(userId, userName, userMail, userNumber, activeStatus, password, userCategory));
		Database.salesRepDB.add(new SalesRep(userId, userName, userMail, userNumber, activeStatus, password,
				userCategory, 0.0, "0.0", 0.0, 0.0));
		return true;
	}

	public boolean login(String u, String pw) {
		for (SalesRep sdb : Database.salesRepDB) {
			if (sdb.getUserId().equals(u) && sdb.getPassword().equals(pw)) {
				System.out.println("Login successful");
				return true;
			} else {
				System.out.println("Login falied");
			}
		}
		return false;
	}

	public String uploadOrders(Order o) {
		// TODO Auto-generated method stub
		if (o == null) {
			return "Invalid enter the data";
		}
		Date date = new Date();
		Date d = (Date) o.getoInitTime();
		if (d.getDate() > date.getDate()) {
			return "Date exceeded";
		}
		Database db = new Database();
		ArrayList<Order> lOrder = new ArrayList<Order>();
		lOrder = (ArrayList<Order>) Database.orderDB;
		ArrayList<Product> prodList = (ArrayList<Product>) o.getProdList();
		ArrayList<Product> prodDB = (ArrayList<Product>) Database.productDB;
		for (Product p : prodList) {
			// check each product in the order
			long temp = p.getQtyOrder();
			for (Product p1 : prodDB) {
				// check for product in the productDB
				if (p1.getpId().equals(p.getpId())) {
					p1.setQty(p.getQty() - temp);
					if (p.getQty() - temp < 0)
						return "Product not in stock so cant update order";
				}
			}

		}
		if (o instanceof Order == false) {
			return "Incompatible types";
		}
		if (lOrder.contains(o)) {
			return "Order already present so cant update";
		} else {
			db.orderDB.add(o);
			return "Order added";
		}
	}

	public String uploadReturnOrder(OrderReturn or) {
		// TODO Auto-generated method stub
		boolean orderPresent = false, orderStatus = false;
		if (or == null) {
			return "Invalid data enter details";
		}
		HashMap<Product, Integer> hm = (HashMap<Product, Integer>) or.getReturnList();
		for (Order o : Database.orderDB) {
			if ((o.getoID().equals(or.getOrderId())) == false) {
				orderPresent = false;
				break;
			}
		}
		if (orderPresent) {
			return "order not present";
		}
		for (Product p : Database.productDB) {
			if (hm.containsKey(p) && or.isOrderPendingStatus()) {
				p.setQty(p.getQty() + hm.get(p));
				orderStatus = true;
			}
		}
		if (orderStatus) {
			return "Order Return accepted";
		} else
			return "order not present";
	}

	public String updateTargetStatus(User sr) {
		// TODO Auto-generated method stub
		double sum = 0;
		ArrayList<Order> odb = (ArrayList) Database.orderDB;
		for (Order o : odb) {
			if (o.getuserID().equals(sr.getUserId())) {
				for (Product p : o.getProdList()) {
					// find his total sales
					sum += p.getPrice() * p.getQtyOrder();
				}

			}
			if (sr instanceof SalesRep == false) {
				return "Incompatible types";
			}

		}
		((SalesRep) sr).setCurrentSales(Double.parseDouble(((SalesRep) sr).getTarget()) - sum);
		return null;
	}

	public boolean cancelProduct(String prodId, String orderId, int qty) {
		Order order = null;
		for (Order o : Database.orderDB) {
			if (o.getoID().equals(orderId) && o.isDispatched_status() == false) {
				order = o;
			}
			for (Product p : order.getProdList()) {
				if (p.getpId().equals(prodId)) {
					if (p.getQtyOrder() == qty) {
						order.getProdList().remove(p);
					} else {
						long temp = p.getQtyOrder();
						p.setQtyOrder(temp - qty);
					}
				}
			}

		}

		return true;
	}

	public boolean cancelOrder(String ordrId) {
		for (Order o : Database.orderDB) {
			if (o.getoID().equals(ordrId)) {
				Database.orderDB.remove(o);
			}
		}
		return true;

	}

	public boolean getTarget(SalesRep sr) {
		// TODO Auto-generated method stub
		return false;
	}
}
