package com.arbaaz.rest.basketservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.basketservice.MenuProxy;
import com.arbaaz.rest.basketservice.OrderProxy;
import com.arbaaz.rest.basketservice.WalletProxy;
import com.arbaaz.rest.basketservice.bean.Basket;
import com.arbaaz.rest.basketservice.bean.Item;
import com.arbaaz.rest.basketservice.repository.ItemRepository;



@RestController
public class BasketController {
	
	@Autowired
	ItemRepository basket;
	
	@Autowired
	OrderProxy orders;
	
	@Autowired
	MenuProxy menuProxy;
	
	@Autowired
	WalletProxy walletProxy;
	
	//post new basket with 1 item
	// fix uri location
	@PostMapping("/user/{userid}/basket/{itemid}")
	private ResponseEntity<Object> addToNewBasket(@PathVariable int userid, @PathVariable int itemid) {
		Item item = menuProxy.getMenuItem(itemid);
		Basket newBasket = new Basket();
		newBasket.setUserId(userid);
		newBasket.addItem(item.getItemName(), item.getPrice());
		Basket newItem = basket.save(newBasket);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getUserId()).toUri();
				
		return ResponseEntity.created(location).build();
	}
	
	//add to existing basket
	@PutMapping("/user/{userid}/basket/{itemid}")
	private void addToExistingBasket(@PathVariable int userid, @PathVariable int itemid) {
		Item item = menuProxy.getMenuItem(itemid);
		Basket usersBasket = basket.getById(userid);
		
		usersBasket.addItem(item.getItemName(), item.getPrice());
		basket.save(usersBasket);
	}
	
	//add to basket depending if they already have basket and item exists on the menu
	@GetMapping("/user/{userid}/basket/{itemid}")
	public void basketOption(@PathVariable int userid, @PathVariable int itemid) {
		
		if(menuProxy.exists(itemid)) {
			if(basket.existsById(userid)) {
				addToExistingBasket(userid, itemid);
			}
			else {
				addToNewBasket(userid, itemid);
			}
		}
	}
	
	//get basket
	@GetMapping("/basket/{basketid}")
	public Basket getBasket(@PathVariable int basketid) {
		return basket.getById(basketid);
	}
	
	//get all baskets
	@GetMapping("/basket/all")
	public Basket getAllBaskets(@PathVariable int basketid) {
		return basket.getById(basketid);
	}
	
	//get total basket value
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
	
//	//add item to basket
//	@PutMapping("/basket/{basketid}/item/{menuitem}")
//	public void addItem(@PathVariable int basketid, @PathVariable int menuitem) {
//		if(menuProxy.exists(menuitem)) {
//			Item item = menuProxy.getMenuItem(menuitem);
//			Basket newBasket = basket.getById(basketid);
//			newBasket.addItem(item.getItemName(), item.getPrice());
//			basket.save(newBasket);
//		}
//		
//	}
	
	//delete mapping
	@DeleteMapping("/user/{userid}/basket")
	public void clearBasket(@PathVariable int userid) {
		basket.deleteById(userid);
	}
	
	//checkout
	@GetMapping("/user/{userid}/basket/checkout")
	public void checkout(@PathVariable int userid) {
		double total  = basket.getById(userid).getTotal();
		if(total <= walletProxy.GetBalance(userid)) {
			
			orders.createOrder(userid);
			
			walletProxy.MinusBalance(userid, total);
			clearBasket(userid);
		}
		//clearBasket(userid);
	}
	
	
	

}
