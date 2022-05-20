package com.arbaaz.rest.menuservice.menu_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.arbaaz.rest.menuservice.menu_bean.Menu;
import com.arbaaz.rest.menuservice.menu_repository.MenuRepository;

@RestController
public class MenuController {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@PostMapping("/menu")
		public void addItem(){
		
	}
	
	@GetMapping("/menu")
	public void getMenu() {
		
	}

}
