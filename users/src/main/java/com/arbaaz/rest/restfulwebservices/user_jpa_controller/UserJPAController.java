package com.arbaaz.rest.restfulwebservices.user_jpa_controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.restfulwebservices.Basket;
import com.arbaaz.rest.restfulwebservices.BasketProxy;
import com.arbaaz.rest.restfulwebservices.WalletProxy;
import com.arbaaz.rest.restfulwebservices.exception.UserNotFoundException;
import com.arbaaz.rest.restfulwebservices.user_bean.User;
import com.arbaaz.rest.restfulwebservices.user_repository.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserJPAController {
	
	@Autowired
	private WalletProxy walletProxy;
	//private Logger logger = LoggerFactory.getLogger(UserJPAController.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BasketProxy basketProxy;
	
	//retrieve all users
	@GetMapping("/users/all-users")
	public MappingJacksonValue retrieveAllUsers(){
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthdate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(userRepository.findAll());
		mapping.setFilters(filters);
		//return service.findAll();
		return mapping;
	}
	
	//retrieve a user
	@GetMapping("/users/get-user/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) 
			throw new UserNotFoundException("id: " + id);
		
		EntityModel<Optional<User>> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		model.add(linkToUsers.withRel("all-users"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthdate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(model);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	//create a new user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User newUser = userRepository.save(user);
		walletProxy.createWallet(user.getId());
		//created
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}")
		.buildAndExpand(newUser.getId()).toUri();
		//generate basket
		basketProxy.generateBasket(user.getId());
		return ResponseEntity.created(location).build();
	}
	
	//delete a user
		@DeleteMapping("/users/delete-user/{id}")
		public void deleteUser(@PathVariable int id){
			userRepository.deleteById(id);
			
		}
		
		@GetMapping("/users/{id}/exists")
		public boolean exists(@PathVariable Integer id){
			//logger.info("does user " + id +" exist");
			return userRepository.existsById(id);
		}
		
		/////////wallet stuff/////////
		@GetMapping("/users/{id}/wallet/balance")
		public Double GetBalance(@PathVariable int id) {
			return walletProxy.GetBalance(id);
		}
		
		@PutMapping("/users/{id}/wallet/add/{add}")
		public void AddBalance(@PathVariable int id,@PathVariable double add) {
			walletProxy.AddBalance(id, add);
		}
		
		
		/////////basket stuff/////////
		//add item to basket
		@PutMapping("/users/{userid}/basket/{itemid}")
		public void addItem(@PathVariable int userid, @PathVariable int itemid) {
			basketProxy.addToBasket(userid, itemid);
		}

		//get basket
		@GetMapping("/users/{userid}/basket")
		public Basket getBasket(@PathVariable int userid) {
			return basketProxy.getABasket(userid);
		}
		
		//get total basket value
		@GetMapping("/users/{userid}/basket/total")
		public double getBasketTotal(@PathVariable int userid) {
			return basketProxy.getBasketTotal(userid);
		}

		//checkout
		@PutMapping("/users/{userid}/basket/checkout")
		public void checkout(@PathVariable int userid) {
			basketProxy.checkout(userid);
		}		
}
