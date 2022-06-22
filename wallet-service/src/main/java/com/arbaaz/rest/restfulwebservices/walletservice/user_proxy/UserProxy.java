package com.arbaaz.rest.restfulwebservices.walletservice.user_proxy;


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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.restfulwebservices.walletservice.wallet_bean.UserTemplate;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;



//@FeignClient(name="user-service", url = "localhost:8000")
@FeignClient(name="user-service", url="user-service:8000")
public interface UserProxy {
	
	//retrieve all users
		@GetMapping("/users/all-users")
		public MappingJacksonValue retrieveAllUsers();
		
		//retrieve a user
		@GetMapping("/users/get-user/{id}")
		public MappingJacksonValue retrieveUser(@PathVariable int id);
		
//		//create a new user
//		@PostMapping("/users")
//		public ResponseEntity<Object> createUser(@Valid @RequestBody User user);
		
		//delete a user
		@DeleteMapping("/users/delete-user/{id}")
		public void deleteUser(@PathVariable int id);
			
		@GetMapping("/users/{id}/exists")
		public boolean exists(@PathVariable Integer id);

}
