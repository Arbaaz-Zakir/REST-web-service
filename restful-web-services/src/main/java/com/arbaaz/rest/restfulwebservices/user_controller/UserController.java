package com.arbaaz.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {
	
	@Autowired
	private UserDao service;
	
	//retrieve all users
	@GetMapping("/users")
	public MappingJacksonValue retrieveAllUsers(){
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "birthdate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(service.findAll());
		mapping.setFilters(filters);
		//return service.findAll();
		return mapping;
	}
	
	//retrieve a user
	@GetMapping("/users/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable int id){
		User user = service.findUser(id);
		if(user==null) 
			throw new UserNotFoundException("id: " + id);
		
		EntityModel <User> model = EntityModel.of(user);
		
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
		User newUser = service.save(user);
		//created
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}")
		.buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//delete a user
		@DeleteMapping("/users/{id}")
		public void deleteUser(@PathVariable int id){
			User user = service.deleteUser(id);
			if(user==null) {
				throw new UserNotFoundException("id: " + id);
			}
			
		}
}