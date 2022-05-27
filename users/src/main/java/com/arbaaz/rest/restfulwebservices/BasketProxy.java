package com.arbaaz.rest.restfulwebservices;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="basket-service", url="localhost:8400")
public interface BasketProxy {
	
	

}
