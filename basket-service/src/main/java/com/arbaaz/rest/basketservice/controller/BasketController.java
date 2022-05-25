package com.arbaaz.rest.basketservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.basketservice.MenuProxy;
import com.arbaaz.rest.basketservice.bean.Basket;
import com.arbaaz.rest.basketservice.bean.Item;
import com.arbaaz.rest.basketservice.repository.ItemRepository;



@RestController
public class BasketController {
	
	@Autowired
	ItemRepository basket;
	
	
	@Autowired
	MenuProxy menuProxy;
	
	@PostMapping("/basket/{itemid}")
	public ResponseEntity<Object> newBasket(@PathVariable int itemid) {
		Item item = menuProxy.getMenuItem(itemid);
		Basket newBasket = new Basket();
		newBasket.setUserId(null);
		newBasket.addItem(item.getItemName(), item.getPrice());
		Basket newItem = basket.save(newBasket);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getBasketId()).toUri();
				
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/basket/{basketid}")
	public Basket getBasket(@PathVariable int basketid) {
		return basket.getById(basketid);
	}
	
	@GetMapping("/basket/all")
	public Basket getAllBaskets(@PathVariable int basketid) {
		return basket.getById(basketid);
	}
	
	@GetMapping("/basket/{basketid}/total")
	public double getBasketTotal(@PathVariable int basketid) {
//		double total = 0;
//		for(int i = 1; i<=basket.count(); i++) {
//			total += basket.getById(i).getPrice();
//		}
//		return total;
		Basket aBasket = basket.getById(basketid);
		return aBasket.getTotal();
	}
	
	@PutMapping("/basket/{basketid}/item/{menuitem}")
	public void addItem(@PathVariable int basketid, @PathVariable int menuitem) {
		if(menuProxy.exists(menuitem)) {
			Item item = menuProxy.getMenuItem(menuitem);
			Basket newBasket = basket.getById(basketid);
			newBasket.addItem(item.getItemName(), item.getPrice());
			basket.save(newBasket);
		}
		
	}
	
	
	

}
