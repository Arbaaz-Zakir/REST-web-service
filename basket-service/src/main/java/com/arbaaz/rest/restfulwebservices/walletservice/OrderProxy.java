package com.arbaaz.rest.restfulwebservices.walletservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


//import com.arbaaz.rest.orderservice.order_bean.Basket;
//import com.arbaaz.rest.orderservice.order_bean.Order;


//@FeignClient(value="order-service", url="localhost:8300")
@FeignClient(name="order-service", url="order-service:8300")
public interface OrderProxy {
	
	
//	@GetMapping("/orders/{orderid}")
//	public Optional<Order> getAOrder(@PathVariable int orderid);
	
	//post order
	@PostMapping("/user/{basketid}/orders")
	public ResponseEntity<Object> createOrder(@PathVariable int basketid);

}
