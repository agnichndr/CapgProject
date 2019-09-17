package com.capgemini.go.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.model.Product;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.RetailerServiceImpl;

class RetailerViewTest {

	private static RetailerServiceImpl retailer;

	@BeforeAll
	static void createRetailer() {
		retailer = new RetailerServiceImpl();
	}

	@AfterAll
	static void freeRetailer() {
		retailer = null;
	}

	@BeforeEach
	void createDatabase() throws CloneNotSupportedException {
		Database.initiateAllDB();
	}

	@AfterEach
	void clearDatabase() {
		Database.clearAllDB();
	}

	@Test
	void sortProductTest() {
		// Database.initiateAllDB();
		// RetailerServiceImpl retailer = new RetailerServiceImpl();
		List<Product> l2 = null;
		try {
			l2 = retailer.sortProduct(Database.productDB, 1, "SR02");
		} catch (Exception ex) {

			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.sortProduct(Database.productDB, 1, "PM01");
		} catch (Exception ex) {
			assertEquals("Access Denied".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.sortProduct(Database.productDB, 1, "SR01");
		} catch (Exception ex) {
			System.out.println(l2);
			System.out.println(ex);
		}
		assertTrue(l2.get(0).getPrice() == 400.00);
		assertTrue(l2.get(5).getPrice() == 4000.00);

		try {
			l2 = retailer.sortProduct(Database.productDB, 1, "Agni");
		} catch (Exception ex) {
			assertEquals("Access Denied".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.sortProduct(Database.productDB, 2, "SR01");
		} catch (Exception ex) {

		}
		assertTrue(l2.get(0).getPrice() == 4000.00);
		assertTrue(l2.get(5).getPrice() == 400.00);

		// Database.clearAllDB();
	}

	@Test
	void getAllProductTest() {
		// Database.initiateAllDB();
		// RetailerServiceImpl retailer = new RetailerServiceImpl();
		List<Product> l1 = Database.productDB;
		List<Product> l2 = null;
		try {
			l2 = retailer.getAllProducts("SR01");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		assertTrue(l1.size() == l2.size());

		try {
			l2 = retailer.getAllProducts("SR02");
		} catch (Exception ex) {
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.getAllProducts("PM01");
		} catch (Exception ex) {
			assertEquals("Access Denied".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.getAllProducts("Agni");
		} catch (Exception ex) {
			assertEquals("Access Denied".trim(), ex.getMessage().trim());

		}
		// Database.clearAllDB();

	}

	@Test
	void searchProductTest() {
		// Database.initiateAllDB();
		// RetailerServiceImpl retailer = new RetailerServiceImpl();
		List<Product> l2 = null;
		try {
			l2 = retailer.searchProduct("Shoe", "PM01");
		} catch (Exception ex) {
			assertEquals("Access Denied".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.searchProduct("Shoe", "SR02");
		} catch (Exception ex) {
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.searchProduct("Book", "SR01");
		} catch (Exception ex) {
			assertEquals("No Results Matches".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.searchProduct("goLF", "SR01");
		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 2);

		try {
			l2 = retailer.searchProduct("goLF Ball", "SR01");
		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 1);

		try {
			l2 = retailer.searchProduct("sHoE", "SR01");
		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 1);
		// Database.clearAllDB();
	}

	@Test
	void filterProductTest() {
		// Database.initiateAllDB();
		// RetailerServiceImpl retailer = new RetailerServiceImpl();
		List<Product> l2 = null;

		try {
			l2 = retailer.filterProduct("", "", 0.0D, 0.0D, "", "", "SR02");
		} catch (Exception ex) {
			assertEquals("Session Timed Out".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.filterProduct("", "", 0.0D, 0.0D, "", "", "PM01");
		} catch (Exception ex) {
			assertEquals("Access Denied".trim(), ex.getMessage().trim());

		}

		try {
			l2 = retailer.filterProduct("", "", 0.00, 10000.00, "", "", "SR01");
			

		} catch (Exception ex) {
			

		}
		assertEquals(l2.size(), 6);

		try {
			l2 = retailer.filterProduct("", "", 1000.00, 3000.00, "", "", "SR01");

		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 3);

		try {
			l2 = retailer.filterProduct("WhiTe", "", 200.00, 450.00, "", "", "SR01");

		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 1);

		try {
			l2 = retailer.filterProduct("bRoWn", "", 00.00, 10000.00, "", "", "SR01");
			

		} catch (Exception ex) {

		}

		assertEquals(l2.size(), 2);

		try {
			l2 = retailer.filterProduct("bRoWn", "", 00.00, 10000.00, Database.manfactDB.get(4).getmId(), "", "SR01");

		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 1);

		try {
			l2 = retailer.filterProduct("bRoWn", "", 100.00, 200.00, Database.manfactDB.get(4).getmId(), "", "SR01");

		} catch (Exception ex) {

			assertEquals("No Results Matches".trim(), ex.getMessage().trim());
		}

		try {
			l2 = retailer.filterProduct("", "", 0.00, 10000.00, "", "personal", "SR01");

		} catch (Exception ex) {

		}

		assertEquals(l2.size(), 1);

		try {
			l2 = retailer.filterProduct("", "", 0.00, 10000.00, "", "GOLF", "SR01");

		} catch (Exception ex) {

		}
		assertEquals(l2.size(), 2);
		// Database.clearAllDB();

	}

}
