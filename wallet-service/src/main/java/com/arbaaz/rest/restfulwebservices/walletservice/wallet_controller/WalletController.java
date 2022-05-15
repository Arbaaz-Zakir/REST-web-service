package com.arbaaz.rest.restfulwebservices.walletservice.wallet_controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.Wallet;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_repository.WalletRepository;

@RestController
public class WalletController {
	
	@Autowired
	private WalletRepository walletRepository;
	
	
	@GetMapping("/wallets")
	public List<Wallet> allWallets(){
		return walletRepository.findAll();
	}
	
	@GetMapping("/wallet/{id}")
	public Double GetBalance(@PathVariable int id) {
		//Optional<Wallet> wallet = walletRepository.findById(id);
		Optional<Wallet> wallet = walletRepository.findById(id);
		return walletRepository.getById(id).getBalance();
		
//		if(wallet==null) {
//			throw new WalletNotFoundException("wallet: " + id);
//		}
		
		
		
		
	}
	
	@PutMapping("/wallet/{id}/{add}")
	public void AddBalance(@PathVariable int id,@PathVariable double add) {
		Wallet wallet = walletRepository.getById(id);
		wallet.addBalance(add);
		walletRepository.save(wallet);
		//return walletRepository.getById(id);

	}
	
	

}
