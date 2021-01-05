/**
 * 
 */
package com.rdsti.dsdeliver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdsti.dsdeliver.dto.OrderDTO;
import com.rdsti.dsdeliver.entities.Order;
import com.rdsti.dsdeliver.repositories.OrdertRepository;

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
	private OrdertRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> list = repository.findOrdersWithProducts();
		
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
}
