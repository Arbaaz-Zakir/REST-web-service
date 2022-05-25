package com.arbaaz.rest.basketservice.bean;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;


public class Item {

	private Integer itemID;
	
	private String itemName;
	
	private String Description;
	
	private double price;

	public Item() {
		super();
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
