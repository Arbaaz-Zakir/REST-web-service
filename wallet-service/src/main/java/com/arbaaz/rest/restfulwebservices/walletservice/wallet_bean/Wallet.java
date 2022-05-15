package com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wallet {

	@Id
	@GeneratedValue
	private Integer walletId;
	
	private Integer userId;
	
	private Double balance;
	
	protected Wallet() {
		
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	

}
