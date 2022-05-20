package com.arbaaz.rest.restfulwebservices.walletservice;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="user-service", url = "localhost:8000")
public interface UserProxy {
	
	//retrieve all users
	@GetMapping("/users/all-users")
	public MappingJacksonValue retrieveAllUsers();
	
	//retrieve a user
	@GetMapping("/users/get-user/{id}")
	public MappingJacksonValue retrieveUser(@PathVariable int id);
	
	//create a new user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserTemplate user);
	
	//delete a user
	@DeleteMapping("/users/delete-user/{id}")
	public void deleteUser(@PathVariable int id);
		
	@GetMapping("/users/{id}")
	public boolean exists(@PathVariable Integer integer);

}
