package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;
import java.text.*;

public class Order implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cancelledList == null) ? 0 : cancelledList.hashCode());
		result = prime * result + (dispatched_status ? 1231 : 1237);
		result = prime * result + ((oDisTime == null) ? 0 : oDisTime.hashCode());
		result = prime * result + ((oID == null) ? 0 : oID.hashCode());
		result = prime * result + ((oInitTime == null) ? 0 : oInitTime.hashCode());
		result = prime * result + ((oRecTime == null) ? 0 : oRecTime.hashCode());
		result = prime * result + orderType;
		result = prime * result + ((prodList == null) ? 0 : prodList.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cancelledList == null) {
			if (other.cancelledList != null)
				return false;
		} else if (!cancelledList.equals(other.cancelledList))
			return false;
		if (dispatched_status != other.dispatched_status)
			return false;
		if (oDisTime == null) {
			if (other.oDisTime != null)
				return false;
		} else if (!oDisTime.equals(other.oDisTime))
			return false;
		if (oID == null) {
			if (other.oID != null)
				return false;
		} else if (!oID.equals(other.oID))
			return false;
		if (oInitTime == null) {
			if (other.oInitTime != null)
				return false;
		} else if (!oInitTime.equals(other.oInitTime))
			return false;
		if (oRecTime == null) {
			if (other.oRecTime != null)
				return false;
		} else if (!oRecTime.equals(other.oRecTime))
			return false;
		if (orderType != other.orderType)
			return false;
		if (prodList == null) {
			if (other.prodList != null)
				return false;
		} else if (!prodList.equals(other.prodList))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	private String oID; // order ID
	private String userID; // Retailer or sales ID
	private Address address; // Retailer Address, if orderType = 2(sales) then then null
	private Date oInitTime; // time at which the product is ordered from App
	private Date oRecTime; // time at which the product is recieved at retailer inventory from GO warehouse
	private Date oDisTime; // time at which the product is dispatched from GO warehouse
	private int orderType; // 1 for Retailer and 2 for sales
	private List<Product> prodList = new ArrayList<Product>();
	private List<Product> cancelledList = new ArrayList<Product>();

	private boolean dispatched_status;
	private static final long serialVersionUID = 7551999649936822526L;
	private static int oid = 0;

	public List<Product> getCancelledList() {
		return cancelledList;
	}

	public void setCancelledList(List<Product> cancelledList) {
		this.cancelledList = cancelledList;
	}

	public String getoID() {
		return oID;
	}

	public void setoID(String oID) {
		this.oID = oID;
	}

	public String getuserID() {
		return userID;
	}

	public void setuserID(String rID) {
		this.userID = rID;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getoInitTime() {
		return oInitTime;
	}

	public void setoInitTime(Date oInitTime) {
		this.oInitTime = oInitTime;
	}

	public Date getoRecTime() {
		return oRecTime;
	}

	public void setoRecTime(Date oRecTime) {
		this.oRecTime = oRecTime;
	}

	public Date getoDisTime() {
		return oDisTime;
	}

	public void setoDisTime(Date oDisTime) {
		this.oDisTime = oDisTime;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public List<Product> getProdList() {
		return prodList;
	}

	public void setProdList(List<Product> prodList) {
		this.prodList = prodList;
	}

	public boolean isDispatched_status() {
		return dispatched_status;
	}

	public void setDispatched_status(boolean dispatched_status) {
		this.dispatched_status = dispatched_status;
	}

	// Constructor for Order
	public Order(String userID, Address address, Date oInitTime, Date oRecTime, Date oDisTime, int orderType,
			List<Product> prodList, boolean dispatched_status, List<Product> cancelledList) {
		super();
		this.oID = "oid" + Integer.toString(++oid);
		this.userID = userID;
		this.address = address;
		this.oInitTime = oInitTime;
		this.oRecTime = oRecTime;
		this.oDisTime = oDisTime;
		this.orderType = orderType;
		this.prodList = prodList;
		this.dispatched_status = dispatched_status;
		this.cancelledList = cancelledList;
	}

	@Override
	public String toString() {
		return "Order [oID=" + oID + ", userID=" + userID + ", address=" + address + ", oInitTime=" + oInitTime
				+ ", oRecTime=" + oRecTime + ", oDisTime=" + oDisTime + ", orderType=" + orderType + ", prodList="
				+ prodList + ", cancelledList=" + cancelledList + ", dispatched_status=" + dispatched_status + "]";
	}

}
