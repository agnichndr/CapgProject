package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;

public class FreqOrderDB implements Serializable {

	private String retailerId;
	private Map<Product, Address> freqOrderProductList = new HashMap<Product, Address>();
	private static final long serialVersionUID = 7551999648936522523L;

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public Map<Product, Address> getFreqOrderProductList() {
		return freqOrderProductList;
	}

	public void setFreqOrderProductList(Map<Product, Address> freqOrderProductList) {
		this.freqOrderProductList = freqOrderProductList;
	}

	// Constructor

	public FreqOrderDB(String retailerId, Map<Product, Address> freqOrderProductList) {
		super();
		this.retailerId = retailerId;
		this.freqOrderProductList = freqOrderProductList;
	}

	@Override
	public String toString() {
		return "FreqOrderDB [retailerId=" + retailerId + ", freqOrderProductList=" + freqOrderProductList + "]";
	}

}
