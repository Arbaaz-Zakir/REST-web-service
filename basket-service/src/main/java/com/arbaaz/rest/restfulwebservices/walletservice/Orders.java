package com.arbaaz.rest.restfulwebservices.walletservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Orders {
	
	@Id
	@GeneratedValue
	private Integer orderId;
	
	private Integer userId;
	
	private String orderItems;
	
	private Double orderTotal;
	
	//private String timestamp;

	public Orders() {
		super();
	}
	
	

	public Orders(Integer userId, String orderItems, Double orderTotal) {
		super();
		this.userId = userId;
		this.orderItems = orderItems;
		this.orderTotal = orderTotal;
	}



	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(String items) {
		this.orderItems = items;
	}

}
