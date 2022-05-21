package com.arbaaz.rest.orderservice.order_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.arbaaz.rest.orderservice.order_repository.OrderRepository;

@RestController
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;

}
