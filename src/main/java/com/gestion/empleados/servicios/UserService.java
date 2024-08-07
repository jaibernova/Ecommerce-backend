package com.gestion.empleados.servicios;

import com.gestion.empleados.modelo.User;
import com.gestion.empleados.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }

    public Long findUserIdByUsernameOrEmail(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        return user != null ? user.getId() : null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null; // or throw an exception
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Object[]> getTopFrequentCustomers() {
        Pageable pageable = PageRequest.of(0, 5); // Pág. 0, tamaño 5
        return userRepository.findTopFrequentCustomers(pageable);
    }

    public static class TopCustomer {
        private User user;
        private Long orderCount;

        public TopCustomer(User user, Long orderCount) {
            this.user = user;
            this.orderCount = orderCount;
        }

        public User getUser() {
            return user;
        }

        public Long getOrderCount() {
            return orderCount;
        }
    }
}
