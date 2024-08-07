package com.gestion.empleados.controlador;

import com.gestion.empleados.modelo.User;
import com.gestion.empleados.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
    }

    @GetMapping("/id")
    public Long getUserId(@RequestParam String usernameOrEmail) {
        return userService.findUserIdByUsernameOrEmail(usernameOrEmail);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/top-customers")
    public List<Object[]> getTopCustomers() {
        return userService.getTopFrequentCustomers();
    }

    // Verificar si el usuario es frecuente
    @GetMapping("/isFrequent/{usernameOrEmail}")
    public boolean isFrequentCustomer(@PathVariable String usernameOrEmail) {
        User user = userService.findByUsernameOrEmail(usernameOrEmail);
        return user != null && user.isFrequentCustomer();
    }
}
