package com.capgemini.go.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.AccessDeniedException;
import java.util.*;
import com.capgemini.go.model.*;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.goAdminServiceImpl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.exceptions.AccessPermisionDeniedException;
import com.capgemini.go.exceptions.NoWrongProductException;
import com.capgemini.go.exceptions.SessionTimedOutException;

class goAdminTest1 {
	
private static goAdminServiceImpl goAdmin;
	
	
	
	@BeforeAll
	static void createGoAdmin()
	{
		goAdmin = new goAdminServiceImpl();
	}
	
	@AfterAll
	static void freeGoAdmin()
	{
		goAdmin = null;
	}
	
	@BeforeEach
	void createDatabase() throws CloneNotSupportedException
	{
		Database.initiateAllDB();
	}
	
	@AfterEach
	void clearDatabase()
	{
		Database.clearAllDB();
	}
	

	@Test
	void sendNotificationSessionTimeOutTest() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		List<OrderReturn> orderReturnDB = Database.orderReturnDB;
		List<OrderReturn> l = null;
		
		assertThrows(SessionTimedOutException.class, () -> { goAdmin.sendNotification(orderReturnDB, "PM02");});
		}
		 
	
	
	@Test
	void sendNotificationAccessDeniedTest() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		List<OrderReturn> orderReturnDB = Database.orderReturnDB;
		List<OrderReturn> l = null;
		
		try
		{
			l =  goAdmin.sendNotification(orderReturnDB, "GA01");
		}
		catch(Exception ex)
		{
			assertEquals("Access Denied".trim(), ex.getMessage().trim());
		}
		
		
		
	}
	
	@Test
	void sendNotificationAccessDeniedTest2()  {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		List<OrderReturn> orderReturnDB = Database.orderReturnDB;
		List<OrderReturn> l = null;
		
		try
		{
			l =  goAdmin.sendNotification(orderReturnDB, "Agni");
		}
		catch(NoWrongProductException |  SessionTimedOutException | AccessPermisionDeniedException ex)
		{
			assertEquals("Access Denied".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void sendNotification() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		List<OrderReturn> orderReturnDB = Database.orderReturnDB;
		List<OrderReturn> l = null;
		
		try
		{
			l =  goAdmin.sendNotification(orderReturnDB, "PM01");
		}
		catch(Exception ex)
		{
			
		}
		
		assertTrue(l.size() == 12);
		
		Database.clearAllDB();
		
		l = Database.orderReturnDB;
		try
		{
			l =  goAdmin.sendNotification(orderReturnDB, "PM01");
		}
		catch(Exception ex)
		{
			assertEquals("No Wrong Product is Shipped".trim(), ex.getMessage().trim());
		}
	}
	
	
	@Test
	void addProductMasterSessionTimeOutTest() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		ProductMaster pm = new ProductMaster("Naba123", "Nabaneet Roy", "nabaneet@gmail.com", 9865852413L, false, "1254Naba", 4);
		ProductMaster pm2 = new ProductMaster("Angshu123", "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		boolean b = false;
		try
		{
			b =  goAdmin.addProductMaster(pm, "PM02");
		}
		catch(Exception ex)
		{
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void addProductMasterAccessDeniedTest() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		ProductMaster pm = new ProductMaster("Naba123", "Nabaneet Roy", "nabaneet@gmail.com", 9865852413L, false, "1254Naba", 4);
		ProductMaster pm2 = new ProductMaster("Angshu123", "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		boolean b = false;
		
		try
		{
			b =  goAdmin.addProductMaster(pm, "GA01");
		}
		catch(Exception ex)
		{
			assertEquals("Access Denied".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void addProductMasterAccessDeniedTest2() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		ProductMaster pm = new ProductMaster("Naba123", "Nabaneet Roy", "nabaneet@gmail.com", 9865852413L, false, "1254Naba", 4);
		ProductMaster pm2 = new ProductMaster("Angshu123", "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		boolean b = false;
		
		try
		{
			b=  goAdmin.addProductMaster(pm,"Agni");
		}
		catch(Exception ex)
		{
			assertEquals("Access Denied".trim(), ex.getMessage().trim());
		}
	}
	@Test
	void addProductMasterInvalidPhoneNumberTest() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		ProductMaster pm = new ProductMaster("Naba123", "Nabaneet Roy", "nabaneet@gmail.com", 9865852413L, false, "1254Naba", 4);
		ProductMaster pm2 = new ProductMaster("Angshu123", "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		boolean b = false;
		
		try
		{
			b=  goAdmin.addProductMaster(pm2,"PM01");
		}
		catch(Exception ex)
		{
			assertEquals("Entered Phone number is not valid".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void addProductMasterEmailValidityTest() {
		//Database.initiateAllDB();
		//goAdminServiceImpl goAdmin = new goAdminServiceImpl();
		ProductMaster pm = new ProductMaster("Naba123", "Nabaneet Roy", "nabaneet@gmail.com", 9865852413L, false, "1254Naba", 4);
		ProductMaster pm2 = new ProductMaster("Angshu123", "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		boolean b = false;
		pm2.setUserNumber(9432130163L);
		pm2.setUserMail("ahjiuahbd");
		
		try
		{
			b=  goAdmin.addProductMaster(pm2,"PM01");
		}
		catch(Exception ex)
		{
			assertEquals("Entered email is invalid".trim(), ex.getMessage().trim());
		}
	}
		
	@Test
	void addProductMasterEAlreadyExistsTest() {
		
		ProductMaster pm2 = new ProductMaster(Database.productMasterDB.get(0).getUserId(), "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		pm2.setUserNumber(9432130163L);
		boolean b = false;
		try
		{
			b=  goAdmin.addProductMaster(pm2,"PM01");
		}
		catch(Exception ex)
		{
			assertEquals("User Id already Exists".trim(), ex.getMessage().trim());
		}
		
		
	}
	
	@Test
	void addProductMasterTest() {
		
		List<ProductMaster> l = Database.productMasterDB;
		int size = l.size();
		
		ProductMaster pm2 = new ProductMaster("angshu123", "Angshu Mukherjee", "angshu@gmail.com", 9865852L, false, "aaggag", 4);
		pm2.setUserNumber(9432130163L);
		boolean b = false;
		try
		{
			b=  goAdmin.addProductMaster(pm2,"PM01");
		}
		catch(Exception ex)
		{
			
		}
		assertTrue(b);
		int sz = Database.productMasterDB.size();
		assertEquals(sz, size+1);
		
	}

	
	@Test
	void retrieveReturnReportSessionTimeOutTest()
	{
		boolean l = false;
		try
		{
			l =  goAdmin.retrieveReturnReport(null,null, null, null, null, 1, "PM02");
		}
		catch(Exception ex)
		{
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void retrieveReturnReportAccessDeniedTest()
	{
		boolean l = false;
		
		try
		{
			l =   goAdmin.retrieveReturnReport(null,null, null, null, null, 1, "GA01");
		}
		catch(Exception ex)
		{
			assertEquals("Access Denied".trim(), ex.getMessage().trim());
		}
	}
	@Test
	void retrieveReturnReportMonthlyTest()
	{
		boolean l = false;
		
		try
		{
			l =  goAdmin.retrieveReturnReport(null,null, null, null, null, 1, "PM01");
		}
		catch(Exception ex)
		{
			assertEquals("Error in generating return report".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void retrieveReturnReportQuaterlyTest()
	{
		boolean l = false;
		
		try
		{
			l =  goAdmin.retrieveReturnReport(null,null, null, null, null, 2, "PM01");
		}
		catch(Exception ex)
		{
			assertEquals("Error in generating return report".trim(), ex.getMessage().trim());
		}
	}
	
	@Test
	void retrieveReturnReportYearlyTest()
	{
		boolean l = false;
		
		try
		{
			l =  goAdmin.retrieveReturnReport(null,null, null, null, null, 3, "PM01");
		}
		catch(Exception ex)
		{
			assertEquals("Error in generating return report".trim(), ex.getMessage().trim());
		}
	}

}
