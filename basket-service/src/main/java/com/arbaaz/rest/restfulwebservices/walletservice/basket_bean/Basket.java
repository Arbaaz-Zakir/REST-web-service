package com.arbaaz.rest.restfulwebservices.walletservice.basket_bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Basket {
	
	@Id
	private Integer userId;
	
	private String items = "";
	
	private double total = 0;
	
	private boolean closed = false;

	public Basket() {
		super();
	}

	public Basket(Integer basketId, Integer userId, String items) {
		super();
		this.userId = userId;
		this.items = items;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
	public double getTotal() {
		return total;
	}
	
	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void addItem(String item, double price) {
		total += price;
		items+= item + ", ";
	}	

}
