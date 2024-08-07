package com.gestion.empleados.repositorio;

import com.gestion.empleados.modelo.Order;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_products (order_id, product_id) VALUES (:orderId, :productId)", nativeQuery = true)
    void addOrderProduct(Long orderId, Long productId);
}