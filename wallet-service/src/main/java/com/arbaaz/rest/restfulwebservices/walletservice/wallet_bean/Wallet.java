package com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wallet {
//use generated value and userid and try to find a way of retriving userid instead to see if that works
	
//	@Id
//	@GeneratedValue
//	private Integer walletId;

	@Id
	private Integer userId;
	
	private double balance = (double) 10;
	
	
	
//	public Wallet(Integer userId) {
//		super();
//		this.userId = userId;
//	}

	protected Wallet() {
		
	}

//	public Integer getWalletId() {
//		return walletId;
//	}
//
//	public void setWalletId(Integer walletId) {
//		this.walletId = walletId;
//	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	public void minusBalance(double amount) {
		balance -= amount;
	}
	
	

}
