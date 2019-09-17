package com.capgemini.go.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.model.Address;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.User;
import com.capgemini.go.service.SalesRepServiceImpl;

class SalesRepServiceImplTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Database.initiateAllDB();
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUploadOrdersIfDateExceeded() {
		Order o1=Database.orderDB.get(0);
		//assertNotNull(o);
		SalesRepServiceImpl s=new SalesRepServiceImpl();
		String s1=s.uploadOrders(o1);
		assertEquals("Date exceeded",s1);
		
	}
	
	@Test
	void testUploadOrdersIfOrderNull() {
		SalesRepServiceImpl s=new SalesRepServiceImpl();
		//Order o1=new Order(null, null, null, null, null, 0, null, false, null);
		String expectedValue="Invalid enter the data";
		assertEquals(expectedValue,s.uploadOrders(null));
		
	}
	
	@Test
	void testUploadOrderIfTypeOrder() {
		SalesRepServiceImpl s=new SalesRepServiceImpl();
		String expectedValue="Incompatible types";
		Order o=Database.orderDB.get(1);
		assertEquals(expectedValue,s.uploadOrders(o));
	}
	
	
	@Test
	void testUploadOrderIfAlreadyPresent() {
		SalesRepServiceImpl s=new SalesRepServiceImpl();
		Order o=Database.orderDB.get(1);
		String expectedValue="Order already present so cant update";
		assertEquals(expectedValue,s.uploadOrders(o));
	}
	
	@Test
	void testUploadOrder() throws ParseException {
		SalesRepServiceImpl s=new SalesRepServiceImpl();
		//Order o =new Order("O123", null, null, null, null, 1, null, false, null);
		String expectedValue="Order added";
		//Order o=new Order("RT01", null, null, null, null, 0, null, false, null);
		Order o1=new Order(Database.retailerDB.get(0).getUserId(),Database.addressDB.get(0) , new SimpleDateFormat("DD/MM/YYYY").parse("01/01/2019"), null, null, 1, null, false, null);
		o1.setoID("o12345");
		assertEquals(expectedValue,s.uploadOrders(o1));
		
	}
	

	
	/*static Order checkOrder() {
		  ArrayList<Order> o=(ArrayList<Order>) Database.orderDB;
		  for(int i=0;i<Database.freqOrderDB.size();i++) {
			  return  (o.get(i));
		  }
		return null;
		}*/
		 
		
	@Test
	void testUploadOrderIfStockExists() {
		SalesRepServiceImpl sr=new SalesRepServiceImpl();
		String expectedValue="Product not in stock so cant update order";
		Order o=Database.orderDB.get(2);
		assertEquals(expectedValue,sr.uploadOrders(o));
	}
	@Test
	void testUploadReturnOrderIfNull() {
		SalesRepServiceImpl sr=new SalesRepServiceImpl();
		//OrderReturn or=new OrderReturn(null, null, null, null, null, false);
		String expectedValue="Invalid data enter details";
		assertEquals(expectedValue,sr.uploadReturnOrder(null));		
	}
	
	@Test
	void testuploadReturnOrderIfPresent() throws ParseException {
		SalesRepServiceImpl sr=new SalesRepServiceImpl();
		String expectedValue="order not present";
		String oId="";
		Map<Product, Integer> returnList6 = new HashMap<Product, Integer>();
		returnList6.put(Database.productDB.get(3), 5);
		OrderReturn or=new OrderReturn("", returnList6, "Wrong Product Shipped", new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2018"), oId, false);
		assertEquals(expectedValue,sr.uploadReturnOrder(or));
	}
	
	@Test 
	void testuploadReturnOrderTrue() {
		SalesRepServiceImpl sr=new SalesRepServiceImpl();
		String expectedValue="Order Return accepted";
		String oId=Database.orderDB.get(0).getoID();
		OrderReturn or=Database.orderReturnDB.get(0);
		assertEquals(expectedValue,sr.uploadReturnOrder(or));
	}
	

	@Test
	void testUpdateTargetStatusIfTypeCorrect() {
		SalesRepServiceImpl sr=new SalesRepServiceImpl();
		User u=Database.retailerDB.get(0);
		String expectedValue="Incompatible types";
		assertEquals(expectedValue,sr.updateTargetStatus(u));
		
	}

}
