package com.arbaaz.rest.orderservice.order_bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import com.arbaaz.rest.orderservice.order_repository.ItemRepository;

@Entity
public class Order {
	
	@Id
	@GeneratedValue
	private Integer orderId;
	
	private Integer userId;
	
	//private List<ItemTemplate> basket;
//	@Autowired
//	private ItemRepository basket;
	
	private Double orderTotal;
	
	private boolean orderComplete = false;
	
	

//	public Order(Integer orderId, Integer userId, Double orderTotal, boolean orderComplete) {
//		super();
//		this.orderId = orderId;
//		this.userId = userId;
//		//this.basket = basket;
//		this.orderTotal = orderTotal;
//		
//	}

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

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}
	
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
