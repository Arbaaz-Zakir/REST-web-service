package com.arbaaz.rest.restfulwebservices.walletservice.wallet_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
