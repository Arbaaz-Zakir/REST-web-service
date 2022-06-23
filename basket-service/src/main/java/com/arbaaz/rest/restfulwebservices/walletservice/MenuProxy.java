package com.arbaaz.rest.restfulwebservices.walletservice;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




//@FeignClient(value="menu-service", url="localhost:8200")
@FeignClient(name="menu-service")
public interface MenuProxy {
	@PostMapping("/menu")
	public ResponseEntity<Object> addItem(@RequestBody Item item);
	
	@GetMapping("/menu")
	public List<Item> getMenu();
	
	@DeleteMapping("/menu/{id}")
	public void deleteItem(@PathVariable int id);
	
	@GetMapping("/menu/{itemid}")
	public Item getMenuItem(@PathVariable int itemid);
	
	@GetMapping("/menu/{id}")
	public boolean exists(@PathVariable int id);
}
