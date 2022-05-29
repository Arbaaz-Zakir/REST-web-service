package com.arbaaz.rest.orderservice.order_bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

//	public List<ItemTemplate> getBasket() {
//		return basket.findAll();
//	}
//
//	public void setBasket(List<ItemTemplate> basket) {
//		this.basket = basket;
//	}

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

//	public String getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(String timestamp) {
//		this.timestamp = timestamp;
//	}
//	
	

	

	
	
	



	
	
	
//	public void completeOrder() {
//		for(int i = 1; i< basket.count(); i++) {
//			orderTotal += basket.getById(i).getPrice();
//		}
////		for(ItemTemplate item : basket.) {
////			orderTotal += item.getPrice();
////		}
//		this.orderComplete = true;
//	}
//	
//	public void addItem(ItemTemplate item) {
//		basket.save(item);
//	}
//	
//	public void removeItem(ItemTemplate item) {
//		basket.delete(item);
//	}
	

}
