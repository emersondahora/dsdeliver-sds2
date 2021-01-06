package com.emersondahora.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emersondahora.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
