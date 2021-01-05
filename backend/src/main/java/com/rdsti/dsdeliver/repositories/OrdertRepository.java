/**
 * 
 */
package com.rdsti.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rdsti.dsdeliver.entities.Order;

/**
 * @business RDS - TI
 * @author rodrigo.siqueira
 * @system dsdeliver
 * @create 5 de jan. de 2021
 * @version 1.0
 */

public interface OrdertRepository extends JpaRepository<Order, Long> {

	@Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products " + " WHERE obj.status = 0 ORDER BY obj.moment ASC")
	List<Order> findOrdersWithProducts();
	
}
