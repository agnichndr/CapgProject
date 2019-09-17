package com.capgemini.go.service;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.dao.GoAdminDaoImpl;
import com.capgemini.go.dao.ProductMasterDaoImpl;
import com.capgemini.go.exceptions.AccessPermisionDeniedException;
import com.capgemini.go.exceptions.SessionTimedOutException;

import java.text.SimpleDateFormat;
import java.util.*;
import com.capgemini.go.model.*;

//import com.capgemini.go.model.*;

public class Driver {

	public static void main(String[] args) throws Exception {

		Database.initiateAllDB();
		GoAdminDaoImpl go1 = new GoAdminDaoImpl();
		/* go1.setBonus("SR01", 20.0, Database.salesRepDB); */

		ProductMasterServiceImpl productMaster = new ProductMasterServiceImpl();

		/*
		 * List<Product> productList = productMaster.getAllProducts("PM01"); for(Product
		 * p : productList ) { System.out.println(p.getpName()); }
		 */

		/*
		 * try { List<Product> productList = productMaster.searchProduct("sHoE",
		 * "GA01"); for(Product p : productList ) { System.out.println(p.getpName()); }
		 * 
		 * } catch(Exception e) { System.out.println(e.getMessage()); }
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
		sdf.setLenient(false);
		sdf2.setLenient(false);
		go1.retrieveReturnReport(sdf.parse("03/01/2018"), sdf.parse("17/12/2019"), null, null, null, 1);
		go1.retrieveReturnReport(null, null, null, null, sdf2.parse("2018"), 2);

		go1.retrieveReturnReport(null, null, sdf2.parse("2018"), sdf2.parse("2019"), null, 3);

	}

}
