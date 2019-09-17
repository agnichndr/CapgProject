package com.capgemini.go.model;

import java.io.Serializable;

public class Address implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + (baseAddressStatus ? 1231 : 1237);
		result = prime * result + ((buildingNo == null) ? 0 : buildingNo.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((retailerId == null) ? 0 : retailerId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + zip;
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
		Address other = (Address) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (baseAddressStatus != other.baseAddressStatus)
			return false;
		if (buildingNo == null) {
			if (other.buildingNo != null)
				return false;
		} else if (!buildingNo.equals(other.buildingNo))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (retailerId == null) {
			if (other.retailerId != null)
				return false;
		} else if (!retailerId.equals(other.retailerId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}

	private String addressId;
	private String country;
	private String city;
	private String state;
	private int zip;
	private String buildingNo;
	private String retailerId;
	private boolean baseAddressStatus;

	public boolean isBaseAddressStatus() {
		return baseAddressStatus;
	}

	public void setBaseAddressStatus(boolean baseAddressStatus) {
		this.baseAddressStatus = baseAddressStatus;
	}

	private static final long serialVersionUID = 7551999649936522523L;
	private static int adid = 0;

	// Constructor
	public Address(String country, String city, String state, int zip, String buildingNo, String retailerId,
			boolean baseAddressStatus) {
		super();

		this.addressId = "addr" + Integer.toString(++adid);
		this.country = country;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.buildingNo = buildingNo;
		this.retailerId = retailerId;
		this.baseAddressStatus = baseAddressStatus;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", country=" + country + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", buildingNo=" + buildingNo + ", retailerId=" + retailerId + "]";
	}

}
