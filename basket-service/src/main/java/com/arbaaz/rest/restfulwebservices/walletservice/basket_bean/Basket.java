package com.arbaaz.rest.restfulwebservices.walletservice.basket_bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.arbaaz.rest.basketservice.MenuProxy;
//import com.arbaaz.rest.basketservice.repository.ItemRepository;

@Entity
public class Basket {
	
	@Id
	@GeneratedValue
	private Integer basketId;

	private Integer userId;
	
	private String items = "";
	
	private double total = 0;
	
	private boolean closed = false;
	
//	@Autowired
//	private MenuProxy menuProxy;

	public Basket() {
		super();
	}

	public Basket(Integer basketId, Integer userId, String items) {
		super();
		//this.basketId = basketId;
		this.userId = userId;
		this.items = items;
	}

	public Integer getBasketId() {
		return basketId;
	}

	public void setBasketId(Integer basketId) {
		this.basketId = basketId;
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
		items+= item + "\n";
	}
	
//	public void removeItem(int item) {
//		if(items.existsById(item)) {
//			total-=items.getById(item).getPrice();
//			items.deleteById(item);
//		}
//	}
	
	

}
