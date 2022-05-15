package com.arbaaz.rest.restfulwebservices.walletservice.wallet_controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.Wallet;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_repository.WalletRepository;

@RestController
public class WalletController {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@PostMapping("/wallets")
	public ResponseEntity<Object> createWallet(@RequestBody Wallet wallet){
		Wallet newWallet = walletRepository.save(wallet);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newWallet
						.getWalletId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
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
