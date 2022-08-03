package com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("UserFilter")

public class UserTemplate {
	
	private Integer id;
	
	
	private String name;
	
	private Date birthdate;
	
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
