package com.capgemini.go.model;

import java.io.Serializable;
import java.util.*;
//Cart class

public class Cart implements Serializable {

	private String retailerId;
	private static int cartid = 0;
	private String cartId;
	Map<Product, Integer> cartList = new HashMap<Product, Integer>();
	private Double totalPrice;

	private static final long serialVersionUID = 7551995649936529526L;

	public Cart(String retailerId, Map<Product, Integer> cartList, Double totalPrice) {
		super();
		this.retailerId = retailerId;
		this.cartId = "cart" + Integer.toString(++cartid);
		this.cartList = cartList;
		this.totalPrice = totalPrice;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<Product, Integer> getCartList() {
		return cartList;
	}

	public void setCartList(Map<Product, Integer> cartList) {
		this.cartList = cartList;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Cart [retailerId=" + retailerId + ", cartId=" + cartId + ", cartList=" + cartList + ", totalPrice="
				+ totalPrice + "]";
	}

}
