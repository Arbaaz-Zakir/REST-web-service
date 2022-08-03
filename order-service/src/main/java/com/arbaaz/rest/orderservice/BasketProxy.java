package com.arbaaz.rest.orderservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.arbaaz.rest.orderservice.order_bean.Basket;


//import com.arbaaz.rest.basketservice.bean.Basket;
//import com.arbaaz.rest.basketservice.bean.Item;


//@FeignClient(value="basket-service", url="localhost:8400")
@FeignClient(name="basket-service")
public interface BasketProxy {
	
	
	//generate new basket
	@PostMapping("/basket")
	public ResponseEntity<Object> generateBasket(@RequestBody Basket userid);
		
	//get all baskets
	@GetMapping("/basket/all")
	public List<Basket> getAllBaskets();
		
	//get a basket
	@GetMapping("/basket/{userid}")
	public Basket getABasket(@PathVariable Integer userid);
	
	//add item to basket
	@PutMapping("/basket/{userid}/add-item/{itemid}")
	public void addToBasket(@PathVariable Integer userid, @PathVariable Integer itemid);
	
	//get basket total
	@GetMapping("/basket/{userid}/total")
	public double getBasketTotal(@PathVariable Integer userid);
		
	//checkout
	@PutMapping("/basket/{userid}/checkout")
	public void checkout(@PathVariable Integer userid);
}
