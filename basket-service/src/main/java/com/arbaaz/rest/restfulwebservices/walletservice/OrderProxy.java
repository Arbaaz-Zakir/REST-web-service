package com.arbaaz.rest.restfulwebservices.walletservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



//import com.arbaaz.rest.orderservice.order_bean.Basket;
//import com.arbaaz.rest.orderservice.order_bean.Order;


//@FeignClient(value="order-service", url="localhost:8300")
@FeignClient(name="order-service")
public interface OrderProxy {
	
	
//	@GetMapping("/orders/{orderid}")
//	public Optional<Order> getAOrder(@PathVariable int orderid);
	
	//post order
	@PostMapping("/user/{basketid}/orders")
	public ResponseEntity<Object> createOrder(@PathVariable int basketid);

}
