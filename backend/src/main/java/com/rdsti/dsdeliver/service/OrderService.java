/**
 * 
 */
package com.rdsti.dsdeliver.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdsti.dsdeliver.dto.OrderDTO;
import com.rdsti.dsdeliver.dto.ProductDTO;
import com.rdsti.dsdeliver.entities.Order;
import com.rdsti.dsdeliver.entities.Product;
import com.rdsti.dsdeliver.enuns.OrderStatus;
import com.rdsti.dsdeliver.repositories.OrderRepository;
import com.rdsti.dsdeliver.repositories.ProductRepository;

/**
 * @business RDS - TI
 * @author rodrigo.siqueira
 * @system dsdeliver
 * @create 5 de jan. de 2021
 * @version 1.0
 */

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> list = repository.findOrdersWithProducts();
		
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO p : dto.getProducts()) {
			
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
			
		}
		
		order = repository.save(order);
		
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id){
		
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		
		return new OrderDTO(order);
	}
	
}
