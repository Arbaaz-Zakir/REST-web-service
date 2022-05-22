package com.arbaaz.rest.orderservice.order_bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {
	
	@Id
	@GeneratedValue
	private Integer orderId;
	
	private Integer userId;
	
	private List<ItemTemplate> basket;
	
	private Double orderTotal;
	
	private boolean orderComplete = false;
	
	

	public Order(Integer orderId, Integer userId, List<ItemTemplate> basket, Double orderTotal, boolean orderComplete) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.basket = basket;
		this.orderTotal = orderTotal;
		
	}

	public Order() {
		super();
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

	public List<ItemTemplate> getBasket() {
		return basket;
	}

	public void setBasket(List<ItemTemplate> basket) {
		this.basket = basket;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}
	
	

}
