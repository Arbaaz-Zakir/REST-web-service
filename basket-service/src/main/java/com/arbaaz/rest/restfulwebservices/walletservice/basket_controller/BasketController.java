package com.arbaaz.rest.restfulwebservices.walletservice.basket_controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.arbaaz.rest.restfulwebservices.walletservice.Item;
import com.arbaaz.rest.restfulwebservices.walletservice.MenuProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.OrderProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.WalletProxy;
import com.arbaaz.rest.restfulwebservices.walletservice.basket_bean.Basket;
import com.arbaaz.rest.restfulwebservices.walletservice.repository.BasketRepository;

@RestController
public class BasketController {
	
	@Autowired
	private MenuProxy menuProxy;
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private OrderProxy orderProxy;
	
	@Autowired
	private WalletProxy walletProxy;
	
	//generate new basket
	@PostMapping("/basket/{userid}")
	public ResponseEntity<Object> generateBasket(@PathVariable int userid) {
		Basket generatedBasket = new Basket();
		generatedBasket.setUserId(userid);
		basketRepository.save(generatedBasket);
		
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(generatedBasket.getUserId()).toUri();
				
		return ResponseEntity.created(location).build();
		
	}
	
	//get all baskets
	@GetMapping("/basket/all")
	public List<Basket> getAllBaskets() {
		return basketRepository.findAll();
	}
	
	//get a basket
	@GetMapping("/basket/{userid}")
	public Optional<Basket> getABasket(@PathVariable Integer userid) {
		return basketRepository.findById(userid);
	}
	
	//add item to basket
	@PutMapping("/basket/{userid}/add-item/{itemid}")
	public void addToBasket(@PathVariable Integer userid, @PathVariable Integer itemid) {
		Basket basket = new Basket();
		basket = basketRepository.getById(userid);
		
		Item item = menuProxy.getMenuItem(itemid);
		
		basket.addItem(item.getItemName(), item.getPrice());
		basketRepository.save(basket);
	}
	
	//get basket total
	@GetMapping("/basket/{userid}/total")
	public double getBasketTotal(@PathVariable Integer userid) {
		return basketRepository.getById(userid).getTotal();
	}
	
	//checkout
	@PutMapping("/basket/{userid}/checkout")
	public void checkout(@PathVariable Integer userid) {
		if(basketRepository.getById(userid).getTotal() <= walletProxy.GetBalance(userid)) {
			Basket basket = basketRepository.getById(userid);
			basket.setClosed(true);
			basketRepository.save(basket);
			orderProxy.createOrder(basket.getUserId());
			
			walletProxy.MinusBalance(userid, basket.getTotal());
			generateBasket(basket.getUserId());
			
		}
		
	}
}
