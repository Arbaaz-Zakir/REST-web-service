package com.arbaaz.rest.restfulwebservices.walletservice.wallet_controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.restfulwebservices.walletservice.user_proxy.UserProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.UserTemplate;
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
	public ResponseEntity<Object> createWallet(@Valid @RequestBody Wallet wallet){
		Integer id = wallet.getUserId();
//		boolean bool = userProxy.exists(id);
		//userProxy.exists(wallet.getUserId());
		if(exists(id) && !walletRepository.existsById(wallet.getUserId())) {
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
		//return null;
	}
	
	@GetMapping("/users/{id}")
	public boolean exists(@PathVariable Integer id) {
		return userProxy.exists(id);
		
		
	}
	
	@GetMapping("/wallets")
	public List<Wallet> allWallets(){
		return walletRepository.findAll();
	}
	
	@GetMapping("/wallets/{id}")
	public Double GetBalance(@PathVariable int id) {
		//Optional<Wallet> wallet = walletRepository.findById(id);
		Wallet wallet = walletRepository.getById(id);
		return wallet.getBalance();
		
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
	
	@PutMapping("/wallets/{id}/minus/{minus}")
	public void MinusBalance(@PathVariable int id, @PathVariable double minus) {
		Wallet wallet = walletRepository.getById(id);
		wallet.minusBalance(minus);
		walletRepository.save(wallet);
		//return walletRepository.getById(id);

	}
	
	

}
