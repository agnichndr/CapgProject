package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;

public class RetailerInventoryItem implements Serializable {

	private Product product;
	private String orderId;
	private Date shelfTimeIn;
	private Date shelfTimeOut;
	private String retailerId;
	private static final long serialVersionUID = 7551999649936522527L;

	// Constructor
	public RetailerInventoryItem(Product product, String orderId, Date shelfTimeIn, Date shelfTimeOut,
			String retailerId) {
		super();
		this.product = product;
		this.orderId = orderId;
		this.shelfTimeIn = shelfTimeIn;
		this.shelfTimeOut = shelfTimeOut;
		this.retailerId = retailerId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getShelfTimeIn() {
		return shelfTimeIn;
	}

	public void setShelfTimeIn(Date shelfTimeIn) {
		this.shelfTimeIn = shelfTimeIn;
	}

	public Date getShelfTimeOut() {
		return shelfTimeOut;
	}

	public void setShelfTimeOut(Date shelfTimeOut) {
		this.shelfTimeOut = shelfTimeOut;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	@Override
	public String toString() {
		return "RetailerInventoryItem [product=" + product + ", orderId=" + orderId + ", shelfTimeIn=" + shelfTimeIn
				+ ", shelfTimeOut=" + shelfTimeOut + ", retailerId=" + retailerId + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
