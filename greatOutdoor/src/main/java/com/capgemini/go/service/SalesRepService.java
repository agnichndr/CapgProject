package com.capgemini.go.service;

import com.capgemini.go.model.Cart;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.SalesRep;
import com.capgemini.go.model.User;

public interface SalesRepService {

	boolean registration(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory);

	boolean login(String u, String pw);

	String uploadOrders(Order oId);

	String uploadReturnOrder(OrderReturn or);

	String updateTargetStatus(User sr);

	boolean cancelProduct(String prodId, String orderId, int qty);

	boolean cancelOrder(String ordrId);

	boolean getTarget(SalesRep sr);

}
