package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;

public class OrderReturn implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + (orderPendingStatus ? 1231 : 1237);
		result = prime * result + ((rTimeStamp == null) ? 0 : rTimeStamp.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((retailerId == null) ? 0 : retailerId.hashCode());
		result = prime * result + ((returnID == null) ? 0 : returnID.hashCode());
		result = prime * result + ((returnList == null) ? 0 : returnList.hashCode());
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
		OrderReturn other = (OrderReturn) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderPendingStatus != other.orderPendingStatus)
			return false;
		if (rTimeStamp == null) {
			if (other.rTimeStamp != null)
				return false;
		} else if (!rTimeStamp.equals(other.rTimeStamp))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (retailerId == null) {
			if (other.retailerId != null)
				return false;
		} else if (!retailerId.equals(other.retailerId))
			return false;
		if (returnID == null) {
			if (other.returnID != null)
				return false;
		} else if (!returnID.equals(other.returnID))
			return false;
		if (returnList == null) {
			if (other.returnList != null)
				return false;
		} else if (!returnList.equals(other.returnList))
			return false;
		return true;
	}

	private String returnID;
	private String retailerId;
	private String orderId;
	private Map<Product, Integer> returnList = new HashMap<Product, Integer>();
	private String reason;
	private Date rTimeStamp;
	private boolean orderPendingStatus;

	private static final long serialVersionUID = 7551990748936522523L;
	private static int rid = 0;
	// Constructor

	public OrderReturn(String retailerId, Map<Product, Integer> returnList, String reason, Date rTimeStamp,
			String orderId, boolean orderPendingStatus) {
		super();
		this.returnID = "ret" + orderId + Integer.toString(++rid);
		this.retailerId = retailerId;
		this.returnList = returnList;
		this.reason = reason;
		this.rTimeStamp = rTimeStamp;
		this.orderPendingStatus = orderPendingStatus;
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isOrderPendingStatus() {
		return orderPendingStatus;
	}

	public void setOrderPendingStatus(boolean orderPendingStatus) {
		this.orderPendingStatus = orderPendingStatus;
	}

	public String getReturnID() {
		return returnID;
	}

	public void setReturnID(String returnID) {
		this.returnID = returnID;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public Map<Product, Integer> getReturnList() {
		return returnList;
	}

	public void setReturnList(Map<Product, Integer> returnList) {
		this.returnList = returnList;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getrTimeStamp() {
		return rTimeStamp;
	}

	public void setrTimeStamp(Date rTimeStamp) {
		this.rTimeStamp = rTimeStamp;
	}

	@Override
	public String toString() {
		return "OrderReturn [returnID=" + returnID + ", retailerId=" + retailerId + ", returnList=" + returnList
				+ ", reason=" + reason + ", rTimeStamp=" + rTimeStamp + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
