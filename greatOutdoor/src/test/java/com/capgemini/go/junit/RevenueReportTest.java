package com.capgemini.go.junit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.dao.Report_RevenueDaoImpl;

class RevenueReportTest {

	private static Report_RevenueDaoImpl rr;

	@BeforeAll
	static void createRR() {
		rr = new Report_RevenueDaoImpl();

	}

	@AfterAll
	static void freeRR() {
		rr = null;
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
	void testRetail() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewRetail(null, null);
		});
	}

	@Test
	void testSales() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewSales(null, null);
		});
	}

	@Test
	void testAllRetail() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewAllRetail(null);
		});
	}

	@Test
	void testAllSales() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewAllSales(null);
		});
	}

	@Test
	void testviewOverAll() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewOverAll(null, null);
		});
	}

	@Test
	void testviewOverAllUser() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewOverAllUser(null, null, null);
		});
	}

	@Test
	void testviewRevenueSpecificProduct() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewRevenueSpecificProduct(null, null, null);
		});
	}

	@Test
	void testviewRevenueOutdoor() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewRevenueOutdoor(null, null, null);
		});
	}

	@Test
	void testviewOverAllUserInvalidUserId() {
		Assertions.assertThrows(Exception.class, () -> {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			Date dentry = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
			Date dexit = new SimpleDateFormat("dd/MM/yyyy").parse("30/12/2019");
			rr.viewOverAllUser(dentry, dexit, "GO01");
		});
	}

	@Test
	void testviewOverAllUserValidUserId() {
		Assertions.assertThrows(Exception.class, () -> {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			Date dentry = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
			Date dexit = new SimpleDateFormat("dd/MM/yyyy").parse("30/12/2019");
			rr.viewOverAllUser(dentry, dexit, "RT01");
		});
	}

	@Test
	void testviewRetailInvalidUserId() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewRetail("GO01", Database.retailerDB);
		});
	}

	@Test
	void testviewRetailvalidUserId() {
		Assertions.assertThrows(Exception.class, () -> {
			rr.viewRetail("RT01", Database.retailerDB);
		});
	}

}
