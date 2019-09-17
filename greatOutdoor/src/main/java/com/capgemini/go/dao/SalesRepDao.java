package com.capgemini.go.dao;

import com.capgemini.go.model.Cart;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.SalesRep;

public interface SalesRepDao {

	boolean registration(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory);

	boolean login(String u,String pw);
	



	boolean uploadOrders(Order oId);

	void uploadReturnOrder(OrderReturn or);

	

	void updateTargetStatus(SalesRep sr);

	boolean cancelProduct(String prodId, String orderId, int qty);


	boolean cancelOrder(String ordrId);



	String getTarget(SalesRep sr);

}
