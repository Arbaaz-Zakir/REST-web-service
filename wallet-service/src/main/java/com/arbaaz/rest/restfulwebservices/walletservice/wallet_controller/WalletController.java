package com.arbaaz.rest.restfulwebservices.walletservice.wallet_controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.arbaaz.rest.restfulwebservices.walletservice.user_proxy.UserProxy;
//import com.arbaaz.rest.restfulwebservices.walletservice.user_proxy.UserProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.Wallet;
import com.arbaaz.rest.restfulwebservices.walletservice.wallet_repository.WalletRepository;

@RestController
public class WalletController {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserProxy userProxy;
	
	
	@PostMapping("/wallets/user/{user}")
	public ResponseEntity<Object> createWallet(@PathVariable int user){
		Wallet wallet = new Wallet(user);
			Wallet newWallet = walletRepository.save(wallet);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newWallet
							.getUserId())
					.toUri();
			
			return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/wallets/exists/{id}")
	public boolean exists(@PathVariable Integer id) {
		return userProxy.exists(id);
	}
	
	@GetMapping("/wallets")
	public List<Wallet> allWallets(){
		return walletRepository.findAll();
	}
	
	@GetMapping("/wallets/balance/{id}")
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
