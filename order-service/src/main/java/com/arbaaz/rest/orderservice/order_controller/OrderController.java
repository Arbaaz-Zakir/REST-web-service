package com.arbaaz.rest.orderservice.order_controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arbaaz.rest.orderservice.BasketProxy;
import com.arbaaz.rest.orderservice.order_bean.Basket;
import com.arbaaz.rest.orderservice.order_bean.ItemTemplate;
import com.arbaaz.rest.orderservice.order_bean.Orders;
import com.arbaaz.rest.orderservice.order_repository.OrderRepository;

@RestController
public class OrderController {
	
	@Autowired
	private BasketProxy basketsProxy;
//	private MenuProxy menuProxy;
	
	@Autowired
	private OrderRepository orders;
	
	@GetMapping("/orders")
	public List<Orders> AllOrders() {
		return orders.findAll();
		
	}
	
	@GetMapping("/orders/{orderid}")
	public Optional<Orders> getAOrder(@PathVariable int orderid) {
		return orders.findById(orderid);
	}
	
//	//post order
	@PostMapping("/user/{basketid}/orders")
	public ResponseEntity<Object> createOrder(@PathVariable int basketid){
		Basket basket = basketsProxy.getBasket(basketid);
		Orders newOrder = new Orders();
		newOrder.setUserId(basket.getUserId());
		newOrder.setOrderTotal(basket.getTotal());
		newOrder.setOrderItems(basket.getItems());
		//newOrder.setTimestamp(LocalDateTime.now().toString());
		
		Orders order = orders.save(newOrder);
		//orders.save(newOrder);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getOrderId()).toUri();
				
		return ResponseEntity.created(location).build();

	}
//	
//	@PutMapping("/orders/{orderid}/add-basket/item/{itemid}")
//	public void addToOrder (@PathVariable int orderid, @PathVariable int item) {
//		Order order = orderRepository.getById(orderid);
//		order.addItem(menuProxy.getMenuItem(item));
//		orderRepository.save(order);
//	}
//	
//	@PutMapping("/orders/{orderid}/remove-from-basket/item/{itemid}")
//	public void removeFromOrder (@PathVariable int orderid, @PathVariable int item) {
//		Order order = orderRepository.getById(orderid);
//		order.removeItem(menuProxy.getMenuItem(item));
//		orderRepository.save(order);
//	}
//	
	

}
