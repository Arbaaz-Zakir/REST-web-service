package com.arbaaz.rest.orderservice.order_controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.orderservice.order_bean.Order;
import com.arbaaz.rest.orderservice.order_repository.OrderRepository;

@RestController
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/orders")
	public List<Order> AllOrders() {
		return orderRepository.findAll();
		
	}
	
	@GetMapping("/orders/{orderid}")
	public Optional<Order> getOrder(@PathVariable int orderid) {
		return orderRepository.findById(orderid);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Object> createOrder(@PathVariable Order order){
		Order newOrder = orderRepository.save(order);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOrder.getOrderId()).toUri();
				
		return ResponseEntity.created(location).build();

	}
	
	

}
