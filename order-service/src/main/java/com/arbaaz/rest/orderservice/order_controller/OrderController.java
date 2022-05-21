package com.arbaaz.rest.orderservice.order_controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.arbaaz.rest.orderservice.order_repository.OrderRepository;

public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;

}
