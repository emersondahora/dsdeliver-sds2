package com.emersondahora.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emersondahora.dsdeliver.dto.OrderDTO;
import com.emersondahora.dsdeliver.entities.Order;
import com.emersondahora.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(item -> new OrderDTO(item)).collect(Collectors.toList());
	}
}
