package com.arbaaz.rest.orderservice;

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

import com.arbaaz.rest.orderservice.order_bean.ItemTemplate;

//import com.arbaaz.rest.menuservice.menu_bean.Item;

@FeignClient(value="menu-service", url = "localhost:8200")
public interface MenuProxy {
	
	@PostMapping("/menu")
	public ResponseEntity<Object> addItem(@RequestBody ItemTemplate item);
	
//	@GetMapping("/menu")
//	public List<Item> getMenu();
	
	@DeleteMapping("/menu/{id}")
	public void deleteUser(@PathVariable int id);
	
	@GetMapping("/menu/{itemid}")
	public ItemTemplate getMenuItem(@PathVariable int itemid);

}
