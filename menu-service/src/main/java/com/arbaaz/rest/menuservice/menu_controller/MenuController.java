package com.arbaaz.rest.menuservice.menu_controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.menuservice.menu_bean.Item;
//import com.arbaaz.rest.menuservice.menu_bean.Menu;
import com.arbaaz.rest.menuservice.menu_repository.MenuRepository;

@RestController
public class MenuController {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@PostMapping("/menu")
	public ResponseEntity<Object> addItem(@RequestBody Item item){
		Item newItem = menuRepository.save(item);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem
						.getItemID())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/menu")
	public List<Item> getMenu() {
		return menuRepository.findAll();
	}
	
	@DeleteMapping("/menu/delete/{id}")
	public void deleteItem(@PathVariable int id){
		menuRepository.deleteById(id);
		
	}
	
	@GetMapping("/menu/{id}")
	public Optional<Item> getMenuItem(@PathVariable int id) {
		return menuRepository.findById(id);
	}
	
	@GetMapping("/menu/exists/{id}")
	public boolean exists(@PathVariable int id) {
		return menuRepository.existsById(id);
	}
}
