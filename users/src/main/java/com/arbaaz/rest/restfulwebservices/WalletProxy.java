package com.arbaaz.rest.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

//import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



//import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.Wallet;

//@FeignClient(value="wallet-service", url="localhost:8100")
@FeignClient(name="wallet-service")

public interface WalletProxy {
	
	@PostMapping("/wallets/user/{user}")
	public ResponseEntity<Object> createWallet(@PathVariable int user);
	
	@GetMapping("/wallets/exists/{id}")
	public boolean exists(@PathVariable Integer id);
	
	@GetMapping("/wallets")
	public List<Wallet> allWallets();
	
	@GetMapping("/wallets/balance/{id}")
	public Double GetBalance(@PathVariable int id);
	
	@PutMapping("/wallets/{id}/add/{add}")
	public void AddBalance(@PathVariable int id,@PathVariable double add);
	
	@PutMapping("/wallets/{id}/minus/{minus}")
	public void MinusBalance(@PathVariable int id, @PathVariable double minus);
	

}
