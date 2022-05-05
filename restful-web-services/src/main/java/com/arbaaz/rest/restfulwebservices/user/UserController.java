package com.arbaaz.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	//retrieve all users
	@GetMapping("/users")
	public List<User>retrieveAllUsers(){
		return service.findAll();
	}
	
	//retrieve a user
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User user = service.findUser(id);
		if(user==null) {
			throw new UserNotFoundException("id: " + id);
		}
		return user;
	}
	
	//create a new user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User newUser = service.save(user);
		//created
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}")
		.buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//retrieve a user
		@DeleteMapping("/users/{id}")
		public void deleteUser(@PathVariable int id){
			User user = service.deleteUser(id);
			if(user==null) {
				throw new UserNotFoundException("id: " + id);
			}
			
		}
}
