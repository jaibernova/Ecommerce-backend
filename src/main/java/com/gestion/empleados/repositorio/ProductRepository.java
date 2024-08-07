package com.gestion.empleados.repositorio;

import com.gestion.empleados.modelo.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActive(Boolean active);

    @Query("SELECT p FROM Product p JOIN p.orders o GROUP BY p.id ORDER BY COUNT(o.id) DESC")
    Page<Product> findTopSoldProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE " +
            "(?1 IS NULL OR p.name LIKE %?1%) AND " +
            "(?2 IS NULL OR p.price >= ?2) AND " +
            "(?3 IS NULL OR p.price <= ?3)")
    List<Product> searchProducts(String name, Double minPrice, Double maxPrice);
}