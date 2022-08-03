package com.arbaaz.rest.restfulwebservices.walletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arbaaz.rest.restfulwebservices.walletservice.basket_bean.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer>{

}

