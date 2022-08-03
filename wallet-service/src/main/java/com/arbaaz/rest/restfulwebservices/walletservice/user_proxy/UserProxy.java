package com.arbaaz.rest.restfulwebservices.walletservice.user_proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="user-service", url = "localhost:8000")
@FeignClient(name="user-service")
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
