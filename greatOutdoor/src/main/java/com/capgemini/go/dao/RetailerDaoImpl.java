package com.capgemini.go.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.go.DBSim.Database;
import com.capgemini.go.exceptions.PriceRangeMismatchException;
import com.capgemini.go.exceptions.ProductNotFoundException;
import com.capgemini.go.model.Address;
import com.capgemini.go.model.Cart;
import com.capgemini.go.model.FreqOrderDB;
import com.capgemini.go.model.Order;
import com.capgemini.go.model.OrderReturn;
import com.capgemini.go.model.Product;
import com.capgemini.go.model.Retailer;
import com.capgemini.go.model.RetailerInventoryItem;
import com.capgemini.go.model.User;

public class RetailerDaoImpl implements RetailerDao, Comparator<Product> {

	// Method to get List of all Products
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> allProducts = Database.productDB;
		return allProducts;
	}

	// Method to search Product depending upon the product name
	public List<Product> searchProduct(String productName) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		List<Product> allProducts = Database.productDB;
		List<Product> searchedProduct = new ArrayList<Product>();

		for (Product p : allProducts) {
			if (p.getpName().toUpperCase().contains(productName.toUpperCase())) {
				searchedProduct.add(p);
			}
		}
		if (searchedProduct.size() == 0) {

			throw new ProductNotFoundException(" No Results Matches");

		}

		return searchedProduct;
	}

	// Method to List Product Depends upon Product Category
	public List<Product> filterProduct(String color, String name, double lowPrice, double highPrice,
			String manufacturerID, String cat) throws Exception {

		List<Product> filteredList = new ArrayList<Product>();
		try {
			if (lowPrice > highPrice) {
				throw new PriceRangeMismatchException(" Low Range Value is greater than High Range Value ");
			}

			for (Product p : Database.productDB) {
				String x = p.getCat().name().toUpperCase();

				if (((p.getpColor()).equalsIgnoreCase(color) || color.equals(""))
						&& ((p.getpName()).toUpperCase().contains(name.toUpperCase()) || name.equals(""))
						&& ((p.getManufacturer().getmId()).equalsIgnoreCase(manufacturerID)
								|| manufacturerID.equals(""))

						&& (cat.equals("") || x.equals(cat.toUpperCase())) && p.getPrice() >= lowPrice
						&& p.getPrice() <= highPrice)

				{

					filteredList.add(p);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		if (filteredList.size() == 0) {

			throw new ProductNotFoundException(" No Results Matches");

		}
		return filteredList;
	}

	public List<Product> sortProduct(List<Product> prodList, int option) {
		// TODO Auto-generated method stub

		if (option == 1) {
			Collections.sort(prodList, new ProductMasterDaoImpl());
		} else if (option == 2) {
			Collections.sort(prodList, new ProductMasterDaoImpl());
		}
		if (option == 1) {
			Collections.sort(prodList, new RetailerDaoImpl());
		} else if (option == 2) {
			Collections.sort(prodList, new RetailerDaoImpl());
			Collections.reverse(prodList);
		}
		return prodList;
	}

	public int compare(Product arg0, Product arg1) {

		return (int) (arg0.getPrice() - arg1.getPrice());
	}

	public boolean addProductToFreqOrderDB(String retailerID, List<Product> productList, List<Address> addressLists) {
		// TODO Auto-generated method stub
		try {
			if (productList.size() != addressLists.size()) {
				return false;
			}
			int i = 0;
			Map<Product, Address> freqOrder = new HashMap<Product, Address>();
			for (Product product : productList) {
				Address add = addressLists.get(i);
				if (!(add.getRetailerId().equals(retailerID))) {
					return false;
				}
				freqOrder.put(product, add);
				i++;
			}
			Database.freqOrderDB.add(new FreqOrderDB(retailerID, freqOrder));
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public boolean addAddress(Address address) {
		try {
			Database.addressDB.add(address);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public boolean updateAddress(String retailerId, Address address) {
		int i = 0, size = Database.addressDB.size();
		for (i = 0; i < size; i++) {
			if (Database.addressDB.get(i).getRetailerId().equals(retailerId)
					&& Database.addressDB.get(i).getAddressId().equals(address.getAddressId())) {
				try {
					Database.addressDB.add(i, address);
					break;
				} catch (Exception ex) {
					return false;
				}
			}
		}
		if (i >= size) {
			return false;
		} else {
			return true;
		}
	}

	public boolean deleteAddress(String retailerID, String addressID) {
		for (Address address : Database.addressDB) {
			if (address.getRetailerId().equals(retailerID) && address.getAddressId().equals(addressID)) {
				try {
					Database.addressDB.remove(address);
				} catch (Exception ex) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean changeOrderAddress(String retailerID, Address address, String orderid) {
		int i = 0;
		for (Order order : Database.orderDB) {
			if (order.getoID().equals(orderid)) {
				try {
					Database.orderDB.get(i).setAddress(address);
				} catch (Exception ex) {
					return false;
				}
			}
			i++;
		}
		return true;
	}

	// azhar

	public boolean addItemToCart(Map<Product, Integer> productList, String retailerID) {
		try {
			for (Map.Entry<Product, Integer> cartentry : productList.entrySet()) {
				if (cartentry.getKey().getQty() < cartentry.getValue()) {
					return false;
				}
			}

			for (Cart cart : Database.cartDB) {
				if (cart.getRetailerId().equals(retailerID)) {
					Double TotalPrice = cart.getTotalPrice();
					for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
						TotalPrice += entry.getKey().getPrice() * entry.getValue();
					}
					Map<Product, Integer> retailerCart = cart.getCartList();
					retailerCart.putAll(productList);
					cart.setCartList(retailerCart);
					cart.setTotalPrice(TotalPrice);
				}
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public boolean deleteItemfromCart(Map<Product, Integer> productList, Product product, Integer q,
			String retailerID) {
		try {
			for (Cart cart : Database.cartDB) {
				if (cart.getRetailerId().equals(retailerID)) {

					for (Map.Entry<Product, Integer> entry : productList.entrySet()) {

						if ((entry.getKey().getpId().equals(product.getpId()) && (entry.getKey().getQtyOrder() < q))) {
							Integer initialQty = entry.getValue();
							Integer newQty = initialQty - q;
							Product p = entry.getKey();
							productList.remove(entry.getKey());
							productList.put(p, newQty);
							break;

						} else if ((entry.getKey().getpId().equals(product.getpId())
								&& (entry.getKey().getQtyOrder() == q))) {

							productList.remove(entry.getKey());
							break;

						}
						if ((entry.getKey().getpId().equals(product.getpId()) && (entry.getKey().getQtyOrder() > q))) {
							return false;

						}
					}
					break;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public boolean checkout(Cart cart, String retailerID, Date d, Address address) {
		// the cart items are transfered as an order

		if (!address.getRetailerId().equals(retailerID)) {
			return false;
		}

		for (Retailer rb : Database.retailerDB) {
			if (rb.getUserId().equals(retailerID)) {
				List<Product> prodList = new ArrayList<Product>();
				for (Map.Entry<Product, Integer> entry : cart.getCartList().entrySet()) {
					Product p = entry.getKey();
					p.setQtyOrder(entry.getValue());
					prodList.add(p);
				}
				Database.orderDB.add(new Order(retailerID, address, d, null, null, 1, prodList, false, null));

			}
		}
		return true;
	}

	// Cancelling Product

	public boolean cancelProduct(String prodId, String orderId) {
		Order order = null;
		for (Order o : Database.orderDB) {
			if (o.getoID().equals(orderId)) {
				order = o;
			}
		}
		for (Product p : order.getProdList()) {
			if (p.getpId().equals(prodId)) {
				order.getProdList().remove(p);
			}
		}
		return true;
	}

// cancelling Order

	public boolean cancelOrder(String ordrId) {
		for (Order o : Database.orderDB) {
			if (o.getoID().equals(ordrId)) {
				Database.orderDB.remove(o);
			}
		}
		return true;
	}

	public boolean changeProductAddress(String retailerID, Product product, Address address,
			Map<Product, Address> freqOrder) {
		try {
			for (FreqOrderDB freq : Database.freqOrderDB) {
				if (freq.getRetailerId().equals(retailerID)) {
					for (Product prod : Database.productDB) {
						if (prod.getpName().equals(product)) {
							freqOrder.replace(product, address);
							return true;
						}
						return true;
					}
					return true;
				}
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	// Retailer Registration

	public boolean retailerRegistration(String userId, String userName, String userMail, long userNumber,
			boolean activeStatus, String password, int userCategory, Address address) {
		// TODO Auto-generated method stub
		Database.userDB.add(new User(userId, userName, userMail, userNumber, activeStatus, password, userCategory));
		List<RetailerInventoryItem> retailerInventory = new ArrayList<RetailerInventoryItem>();
		Database.retailerDB.add(new Retailer(userId, userName, userMail, userNumber, activeStatus, password,
				userCategory, 0.0, retailerInventory));
		return true;
	}
	// Retailer Login

	public boolean login(String u, String pw) {
		// TODO Auto-generated method stub
		for (Retailer rtr : Database.retailerDB) {
			if (rtr.getUserId().equals(u) && rtr.getPassword().equals(pw)) {
				rtr.setActiveStatus(true);
				for (User user : Database.userDB) {
					if (user.getUserId().equals(u) && user.getPassword().equals(pw)) {
						user.setActiveStatus(true);
					}
				}
				System.out.println("Login successful");
				return true;
			} else {
				System.out.println("Login falied");
			}
		}
		return false;

	}

	// Returning Order

	public boolean returnOrder(String retailerID, String reason, String ordId, Date d) {

		boolean retailerFound = false;
		int retailerpos = 0;
		for (int i = 0; i < Database.retailerDB.size(); i++) {
			if (Database.retailerDB.get(i).getUserId().equals(retailerID)) {
				retailerFound = true;
				retailerpos = i;
				break;
			}
		}
		if (!retailerFound)
			return false;

		boolean orderFound = false;
		int orderpos = 0;
		for (int i = 0; i < Database.orderDB.size(); i++) {
			if (Database.orderDB.get(i).getoID().equals(ordId)) {
				orderFound = true;
				orderpos = i;
				break;
			}
		}
		if (!orderFound)
			return false;

		Order o = Database.orderDB.get(orderpos);
		Retailer r = Database.retailerDB.get(retailerpos);
		List<Product> prodList = new ArrayList<Product>();
		if (r.getUserId().equals(o.getuserID())) {
			if (o.isDispatched_status()) {
				prodList = o.getProdList();
			} else
				return false;
		} else
			return false;

		Map<Product, Integer> returnList = new HashMap<Product, Integer>();
		for (int i = 0; i < prodList.size(); i++) {
			returnList.put(prodList.get(i), (int) (prodList.get(i).getQty()));
		}
		Database.orderReturnDB.add(new OrderReturn(retailerID, returnList, reason, d, ordId, true));
		return true;
	}

	// return product

	public List<Product> returnProductByRetailer(String retailerID, String orderID, List<Product> acceptedProducts) {
		int orderPos = 0;
		boolean orderFound = false;
		for (int i = 0; i < Database.orderDB.size(); i++) {
			if (Database.orderDB.get(i).getuserID().equals(retailerID)
					&& Database.orderDB.get(i).getoID().equals(orderID)) {
				orderPos = i;
				orderFound = true;
				break;
			}
		}
		if (!orderFound) {
			return null;
		}

		List<Product> rejectedProducts = new ArrayList<Product>();
		for (Product p : Database.orderDB.get(orderPos).getProdList()) {
			for (Product q : acceptedProducts) {
				if (!p.equals(q)) {
					rejectedProducts.add(p);
				}
			}
		}
		return rejectedProducts;
	}

	public boolean retailerRegistration(String userId, String userName, String userMail, long userNumber,
			boolean activeStatus, String password, int userCategory) {
		// TODO Auto-generated method stub
		return false;
	}

}
