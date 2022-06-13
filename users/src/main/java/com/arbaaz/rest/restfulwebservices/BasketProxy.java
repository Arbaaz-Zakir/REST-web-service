package com.arbaaz.rest.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;






//import com.arbaaz.rest.basketservice.bean.Basket;
//import com.arbaaz.rest.basketservice.bean.Item;


@FeignClient(value="basket-service", url="basket-service:8400")
public interface BasketProxy {
	
	//generate new basket
	@PostMapping("/basket/{integer}")
	public ResponseEntity<Object> generateBasket(@PathVariable Integer integer);
			
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
