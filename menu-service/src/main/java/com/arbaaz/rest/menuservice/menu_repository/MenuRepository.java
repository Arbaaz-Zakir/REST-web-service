package com.arbaaz.rest.menuservice.menu_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arbaaz.rest.menuservice.menu_bean.Item;

public interface MenuRepository extends JpaRepository<Item, Integer>{

}
