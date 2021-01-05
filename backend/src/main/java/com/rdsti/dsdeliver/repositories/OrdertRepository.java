/**
 * 
 */
package com.rdsti.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdsti.dsdeliver.entities.Order;

/**
 * @business RDS - TI
 * @author rodrigo.siqueira
 * @system dsdeliver
 * @create 5 de jan. de 2021
 * @version 1.0
 */

public interface OrdertRepository extends JpaRepository<Order, Long> {

}
