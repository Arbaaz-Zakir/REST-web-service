package com.arbaaz.rest.restfulwebservices.walletservice.basket_controller;

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

import com.arbaaz.rest.restfulwebservices.walletservice.OrderProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.basket_bean.Basket;
import com.arbaaz.rest.restfulwebservices.walletservice.basket_bean.UserTemplate;
import com.arbaaz.rest.restfulwebservices.walletservice.repository.BasketRepository;



@RestController
public class BasketController {
	
	private UserTemplate currentUser;
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private OrderProxy orderProxy;
	
	//generate new basket
	@PostMapping("/basket")
	public ResponseEntity<Object> generateBasket(@RequestBody Basket userid) {
		Basket generatedBasket = new Basket();
		generatedBasket.setUserId(userid.getUserId());
		basketRepository.save(generatedBasket);
		
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(generatedBasket.getUserId()).toUri();
				
		return ResponseEntity.created(location).build();
		
	}
	
	
	
	

	
	

}
