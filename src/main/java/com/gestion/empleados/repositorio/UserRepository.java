package com.gestion.empleados.repositorio;

import com.gestion.empleados.modelo.User;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String emsail);

    @Query("SELECT u FROM User u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
    User findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    @Query("SELECT u, COUNT(o.id) as orderCount " +
            "FROM User u " +
            "JOIN u.orders o " +
            "WHERE u.frequentCustomer = true " +
            "GROUP BY u.id " +
            "ORDER BY COUNT(o.id) DESC")
    List<Object[]> findTopFrequentCustomers(Pageable pageable);
}