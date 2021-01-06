package com.emersondahora.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emersondahora.dsdeliver.dto.OrderDTO;
import com.emersondahora.dsdeliver.dto.ProductDTO;
import com.emersondahora.dsdeliver.entities.Order;
import com.emersondahora.dsdeliver.entities.OrderStatus;
import com.emersondahora.dsdeliver.entities.Product;
import com.emersondahora.dsdeliver.repositories.OrderRepository;
import com.emersondahora.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository; 
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(item -> new OrderDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		for(ProductDTO p :dto.getProducts() ) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
}