package com.arbaaz.rest.restfulwebservices.walletservice.basket_bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("UserFilter")

public class UserTemplate {
	
	private Integer id;
	
	
	private String name;
	
	private Date birthdate;
	
	//contructor below user for DAO 
//	public User(Integer id, String name, Date birthdate) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.birthdate = birthdate;
//	}
	
	protected UserTemplate() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	

}
