package com.arbaaz.rest.orderservice.order_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arbaaz.rest.orderservice.order_bean.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
