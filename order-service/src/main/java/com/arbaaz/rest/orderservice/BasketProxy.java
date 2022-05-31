package com.arbaaz.rest.orderservice;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.orderservice.order_bean.Basket;

//import com.arbaaz.rest.basketservice.bean.Basket;
//import com.arbaaz.rest.basketservice.bean.Item;

@FeignClient(value="basket-service", url="localhost:8400")
public interface BasketProxy {
	
	//get basket
	@GetMapping("/basket/{basketid}")
	public Basket getBasket(@PathVariable int basketid) ;
	
	//get all baskets
	@GetMapping("/basket/all")
	public List<Basket> getAllBaskets();
	
	//get total basket value
	@GetMapping("/basket/{basketid}/total")
	public double getBasketTotal(@PathVariable int basketid);

	
	//delete mapping
	@DeleteMapping("/user/{userid}/basket")
	public void clearBasket(@PathVariable int userid);
	
	//checkout
	@GetMapping("/user/{userid}/basket/checkout")
	public void checkout(@PathVariable int userid);

}
