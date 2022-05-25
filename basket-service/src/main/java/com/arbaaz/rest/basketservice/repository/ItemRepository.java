package com.arbaaz.rest.basketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arbaaz.rest.basketservice.bean.Basket;
import com.arbaaz.rest.basketservice.bean.Item;

public interface ItemRepository extends JpaRepository<Basket, Integer>{

}
