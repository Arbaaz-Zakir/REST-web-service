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

import com.arbaaz.rest.restfulwebservices.walletservice.UserProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.UserTemplate;
//import com.arbaaz.rest.restfulwebservices.walletservice.user_proxy.UserProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.Wallet;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_repository.WalletRepository;

@RestController
public class WalletController {
	
	private UserTemplate currentUser;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserProxy userProxy;
	
	
	@PostMapping("/wallets")
	public ResponseEntity<Object> createWallet(@RequestBody Wallet wallet){
		if(userProxy.exists(wallet.getUserId()) && !walletRepository.existsById(wallet.getUserId())) {
			Wallet newWallet = walletRepository.save(wallet);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newWallet
							.getUserId())
					.toUri();
			
			return ResponseEntity.created(location).build();
		}
//		URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(newWallet
//						.getWalletId())
//				.toUri();
		
//		return ResponseEntity.created(location).build();
		return ResponseEntity.badRequest().build();
	}
	
	
	
	@GetMapping("/wallets")
	public List<Wallet> allWallets(){
		return walletRepository.findAll();
	}
	
	@GetMapping("/wallets/{id}")
	public Double GetBalance(@PathVariable int id) {
		//Optional<Wallet> wallet = walletRepository.findById(id);
		Optional<Wallet> wallet = walletRepository.findById(id);
		return walletRepository.getById(id).getBalance();
		
//		if(wallet==null) {
//			throw new WalletNotFoundException("wallet: " + id);
//		}
	}
	
	@PutMapping("/wallets/{id}/add/{add}")
	public void AddBalance(@PathVariable int id,@PathVariable double add) {
		Wallet wallet = walletRepository.getById(id);
		wallet.addBalance(add);
		walletRepository.save(wallet);
		//return walletRepository.getById(id);

	}
	
	

}
