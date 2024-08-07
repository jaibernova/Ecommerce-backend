package com.gestion.empleados.servicios;

import com.gestion.empleados.modelo.Order;
import com.gestion.empleados.modelo.OrderItem;
import com.gestion.empleados.modelo.OrderRequest;
import com.gestion.empleados.modelo.Product;
import com.gestion.empleados.modelo.User;
import com.gestion.empleados.repositorio.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.empleados.repositorio.ProductRepository;
import com.gestion.empleados.repositorio.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderDate(orderRequest.getOrderDate());
        order.setStatus(orderRequest.getStatus());
        order.setTotal(orderRequest.getTotal()); // Set total to be calculated later

        // Set the user
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        // Calculate total and save the order
        double total = 0.0;
        for (OrderItem item : orderRequest.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            total += product.getPrice() * item.getQuantity();
        }

        Order savedOrder = orderRepository.save(order);

        // Save order products
        for (OrderItem item : orderRequest.getItems()) {
            orderRepository.addOrderProduct(savedOrder.getId(), item.getProductId());
        }

        return savedOrder;
    }
}