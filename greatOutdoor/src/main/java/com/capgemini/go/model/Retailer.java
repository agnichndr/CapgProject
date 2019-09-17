package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;

public class Retailer extends User implements Serializable {

	private double Discount;

	private List<RetailerInventoryItem> retailerInventory = new ArrayList<RetailerInventoryItem>();

	public List<RetailerInventoryItem> getRetailerInventory() {
		return retailerInventory;
	}

	public void setRetailerInventory(List<RetailerInventoryItem> retailerInventory) {
		this.retailerInventory = retailerInventory;
	}

	private static final long serialVersionUID = 7551999649936522526L;

	// Constructor
	public Retailer(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory, double discount, List<RetailerInventoryItem> retailerInventory) {
		super(userId, userName, userMail, userNumber, activeStatus, password, userCategory);
		Discount = discount;
		this.retailerInventory = retailerInventory;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	@Override
	public String toString() {
		return "Retailer [Discount=" + Discount + ", retailerInventory=" + retailerInventory + ", getUserCategory()="
				+ getUserCategory() + ", getUserId()=" + getUserId() + ", getUserName()=" + getUserName()
				+ ", getUserMail()=" + getUserMail() + ", getUserNumber()=" + getUserNumber() + ", isActiveStatus()="
				+ isActiveStatus() + ", getPassword()=" + getPassword() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
