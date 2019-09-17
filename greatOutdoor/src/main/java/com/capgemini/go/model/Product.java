package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;

public class Product implements Serializable, Cloneable {

	private String pId;
	private String pName;
	private String pColor;
	private String pSpecs;
	private String pDimesions;
	private Date manfacDate;
	private static int pid = 0;
	private double price;
	private long qty;
	private long qtyOrder;
	private Manufacturer manufacturer;

	public enum category {
		CAMPING, GOLF, MOUNTAINEERING, OUTDOOR, PERSONAL
	}

	private category cat;

	public category getCat() {
		return cat;
	}

	public void setCat(category cat) {
		this.cat = cat;
	}

	public Date getManfacDate() {
		return manfacDate;
	}

	public void setManfacDate(Date manfacDate) {
		this.manfacDate = manfacDate;
	}

	private static final long serialVersionUID = 7551999649936522525L;

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpColor() {
		return pColor;
	}

	public void setpColor(String pColor) {
		this.pColor = pColor;
	}

	public String getpSpecs() {
		return pSpecs;
	}

	public void setpSpecs(String pSpecs) {
		this.pSpecs = pSpecs;
	}

	public String getpDimesions() {
		return pDimesions;
	}

	public void setpDimesions(String pDimesions) {
		this.pDimesions = pDimesions;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getQtyOrder() {
		return qtyOrder;
	}

	public void setQtyOrder(long qtyOrder) {
		this.qtyOrder = qtyOrder;
	}

	// Constructor
	public Product(String pName, String pColor, String pSpecs, String pDimesions, double price, long qty, long qtyOrder,
			Manufacturer manufacturer, Date manfacDate, String cat) {
		super();
		this.pId = "Pd" + pName.substring(0, 2) + manufacturer.getmId().substring(0, 2) + Integer.toString(++pid);
		this.pName = pName;
		this.pColor = pColor;
		this.pSpecs = pSpecs;
		this.pDimesions = pDimesions;
		this.manufacturer = manufacturer;
		this.price = price;
		this.qty = qty;
		this.qtyOrder = qtyOrder;
		this.manfacDate = manfacDate;
		this.cat = category.valueOf(cat);
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pName=" + pName + ", pColor=" + pColor + ", pSpecs=" + pSpecs
				+ ", pDimesions=" + pDimesions + ", price=" + price + ", qty=" + qty + ", qtyOrder=" + qtyOrder
				+ ", manufacturer=" + manufacturer + "category=" + cat + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
