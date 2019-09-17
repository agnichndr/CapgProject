package com.capgemini.go.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.exceptions.SessionTimedOutException;
import com.capgemini.go.model.Product;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.*;

class ProductMasterTest {
	
	private static ProductMasterServiceImpl productMaster;
	
	
	
	@BeforeAll
	static void createProductMaster()
	{
		productMaster = new ProductMasterServiceImpl();
	}
	
	@AfterAll
	static void freeProductMaster()
	{
		productMaster = null;
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
	void sortProductSessionTimedOutTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		try
		{
			l2 = productMaster.sortProduct(Database.productDB, 1, "GA02");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
	@Test
	void sortProductAccessDeniedTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.sortProduct(Database.productDB, 1, "PM01");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	@Test
	void sortProductAccessDenied2Test() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.sortProduct(Database.productDB, 1, "Agni");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void sortProductAscendingTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.sortProduct(Database.productDB, 1, "GA01");
		}
		catch(Exception  ex)
		{	
	
			
		}
		assertTrue(l2.get(0).getPrice() == 400.00 );
		assertTrue(l2.get(5).getPrice() == 4000.00 );
	}
	
	@Test
	void sortProductDescendingTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.sortProduct(Database.productDB, 2, "GA01");
		}
		catch(Exception  ex)
		{	
	
			
		}
		assertTrue(l2.get(0).getPrice() == 4000.00 );
		assertTrue(l2.get(5).getPrice() == 400.00 );
		
		//Database.clearAllDB();
	}
	
	
	@Test
	void getAllProductTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		List<Product> l2 = null;
		try
		{
			l2 = productMaster.getAllProducts("GA01");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		assertTrue(l1.size() == l2.size());
	}
	@Test
	void getAllProductSessionTimedOutTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.getAllProducts("GA02");
		}
		catch(Exception  ex)
		{	
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void getAllProductAccessDeniedTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.getAllProducts("PM01");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void getAllProductAccessDenied2Test() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.getAllProducts("Agni");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
		//Database.clearAllDB();
		
	}
	
	
	@Test
	void searchProductAccessDeniedTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		try
		{
			l2 = productMaster.searchProduct("Shoe", "PM01");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void searchProductSessionTimedOutTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.searchProduct("Shoe", "GA02");
		}
		catch(Exception  ex)
		{	
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void searchProductNoProductTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.searchProduct("Book", "GA01");
		}
		catch(Exception  ex)
		{	
			assertEquals("No Results Matches".trim(), ex.getMessage().trim());	
			
		}
	}
		
	@Test
	void searchProductHalfKeywordTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		try
		{
			l2 = productMaster.searchProduct("goLF", "GA01");
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(),2);	
	}
	@Test
	void searchProductFullProductNameTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.searchProduct("goLF Ball", "GA01");
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(),1);	
	}
	
	@Test
	void searchProductCaseSensativeTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.searchProduct("sHoE", "GA01");
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(),1);	
		Database.clearAllDB();
	}
	
	@Test
	void filterProductSessionTimedoutTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("", "", 0.0D, 0.0D, "", "", "GA02");
		}
		catch(Exception  ex)
		{	
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
		
		@Test
		void filterProductAccessDeniedTest() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("", "", 0.0D, 0.0D, "", "", "PM01");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());
			
		}
		}
		
		@Test
		void filterProductPriceTest() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("", "", 0.00, 10000.00, "", "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(), 6);
		}
		
		@Test
		void filterProductPriceRangeMismatchTest() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("", "", 1000.00, 100.00, "", "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			assertEquals("Low Range Value is greater than High Range Value".trim(), ex.getMessage().trim());
			
		}
		
		}
		
		@Test
		void filterProductPriceRangeTest() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("", "", 1000.00, 3000.00, "", "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(), 3);
		
		}
		
		@Test
		void filterProductMultiFilterTest() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("WhiTe", "", 200.00, 450.00, "", "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(), 1);
		}
		
		@Test
		void filterProductMultiFilter2Test() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("bRoWn", "", 00.00, 10000.00, "", "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			
		}
		
		assertEquals(l2.size(), 2);
		}
		
		@Test
		void filterProductMultiFilter3Test() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("bRoWn", "", 00.00, 10000.00, Database.manfactDB.get(4).getmId(), "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			
		}
		assertEquals(l2.size(), 1);
		}
		@Test
		void filterProductMultiFilter4Test() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		{
			l2 = productMaster.filterProduct("bRoWn", "", 100.00, 200.00, Database.manfactDB.get(4).getmId(), "", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			assertEquals("No Results Matches".trim(), ex.getMessage().trim());
		}
		}
		
		@Test
		void filterProductTesCategoryt() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		
		{
			l2 = productMaster.filterProduct("", "", 0.00, 10000.00,"", "GOLF", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			System.out.println(ex);
		}
		
		assertEquals(l2.size(), 2);
		}
		@Test
		void filterProductCategory2Test() {
			//Database.initiateAllDB();
			//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
			List<Product> l2 = null;
		
		try
		
		{
			l2 = productMaster.filterProduct("", "", 0.00, 10000.00,"", "outdoor", "GA01");
			
		}
		catch(Exception  ex)
		{	
			
			System.out.println(ex);
		}
		
		assertEquals(l2.size(), 1);
		
		
		//Database.clearAllDB();
		
	}
	
	
		
	@Test
	void deleteProductSessionTimedOutTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		
		boolean b = false;
		
		
		try
		{
			b = productMaster.deleteProduct("", "GA02");
		}
		catch(Exception  ex)
		{	
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void deleteProductAccessDeniedTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		
		boolean b = false;
		
		try
		{
			b = productMaster.deleteProduct("","PM01");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void deleteProductAccessDenied2Test() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		
		boolean b = false;
		
		try
		{
			b = productMaster.deleteProduct("", "Agni");
		}
		catch(Exception  ex)
		{	
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
		
	@Test
	void deleteProductErrorTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		
		boolean b = false;
		
		try
		{
			b = productMaster.deleteProduct("Agni123", "GA01");
		}
		catch(Exception  ex)
		{	
			assertEquals("Error occur in deleting Product".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void deleteProductNotExistsTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		
		boolean b = false;
		
		try
		{
			b = productMaster.deleteProduct("adsgydh", "GA01");
		}
		catch(Exception  ex)
		{	
				
			
		}
		
		assertFalse(b);
	}
	@Test
	void deleteProductTest() {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		
		boolean b = false;
		
		int size = l1.size();
		try
		{
			b = productMaster.deleteProduct(Database.productDB.get(0).getpId(), "GA01");
		}
		catch(Exception  ex)
		{	
				
			
		}
		assertTrue(b);
		assertTrue(size == Database.productDB.size()+1 );
		//Database.clearAllDB();
		
	}
	
	@Test
	void addProductSessionTimedTest() throws ParseException {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "30 inch", 4000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId("AgniP");
		
		
		try
		{
			
			
			b = productMaster.addProduct(p, "GA02");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void addProductAccessDeniedTest() throws ParseException {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "30 inch", 4000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId("AgniP");
		
		try
		{
			
			
			b = productMaster.addProduct(p, "PM01");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void addProductAccessDenied2Test() throws ParseException {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "30 inch", 4000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId("AgniP");
		try
		{
			
			
			b = productMaster.addProduct(p, "Agni");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void addProductTest() throws ParseException {
		//Database.initiateAllDB();
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "30 inch", 4000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId("AgniP");
		int size = l1.size();

		try
		{
			
			
			b = productMaster.addProduct(p, "GA01");
		}
		catch(Exception  ex)
		{	
				System.out.println(ex);
			
		}
		assertTrue(b);
		assertTrue(Database.productDB.size() == size+1);
		assertTrue(Database.productDB.get(6).getpId().equalsIgnoreCase("AgniP"));
		//Database.clearAllDB();
	}
	
	
	@Test
	void updateProductSessionTimeTest() throws ParseException {
		//Database.initiateAllDB();
		double dim = Database.productDB.get(0).getPrice();
		
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "6 inch", 1000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId(Database.productDB.get(0).getpId());
		
		
		try
		{
			
			
			b = productMaster.updateProduct(p, "GA02");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void updateProductAccessDeniedTest() throws ParseException {
		//Database.initiateAllDB();
		double dim = Database.productDB.get(0).getPrice();
		
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "6 inch", 1000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId(Database.productDB.get(0).getpId());
		
		
		
		try
		{
			
			
			b = productMaster.updateProduct(p, "PM01");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void updateProductAccessDenied2Test() throws ParseException {
		//Database.initiateAllDB();
		double dim = Database.productDB.get(0).getPrice();
		
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "6 inch", 1000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId(Database.productDB.get(0).getpId());
		try
		{
			
			
			b = productMaster.updateProduct(p, "Agni");
		}
		catch(Exception  ex)
		{	
			
			assertEquals("Access Denied".trim(), ex.getMessage().trim());	
			
		}
	}
	
	@Test
	void updateProductTest() throws ParseException {
		//Database.initiateAllDB();
		double dim = Database.productDB.get(0).getPrice();
		
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "6 inch", 1000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId(Database.productDB.get(0).getpId());
		
		int size = l1.size();
		
		
		
		
		
		try
		{
			
			
			b = productMaster.updateProduct(p, "GA01");
		}
		catch(Exception  ex)
		{	
				
			
		}
		assertTrue(b);
		assertTrue(size == Database.productDB.size());
		assertFalse(Database.productDB.get(0).getPrice() == dim);
		
		
		//Database.clearAllDB();
	}
		
	@Test
	void updateProductNotExistsTest() throws ParseException {
		//Database.initiateAllDB();
		double dim = Database.productDB.get(0).getPrice();
		
		//ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();
		List<Product> l1 = Database.productDB;
		boolean b = false; 
		Product p =		new Product("Mountaineering Stick", "Blue", "Light Weight", "6 inch", 1000.00, 100, 0, Database.manfactDB.get(1),
				new SimpleDateFormat("DD/MM/YY").parse("20/08/2000"), "MOUNTAINEERING");
		p.setpId("XXX");
		
		
		
		
		try
		{
			
			
			b = productMaster.updateProduct(p, "GA01");
		}
		catch(Exception  ex)
		{	
				
			
		}
		assertFalse(b);
		
		
		//Database.clearAllDB();
	}
		
		
	

}
