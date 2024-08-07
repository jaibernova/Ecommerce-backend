package com.gestion.empleados;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.gestion.empleados.modelo.User;
import com.gestion.empleados.servicios.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class GestionEmpleadosBackendApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void testFindUserById() {
		// Crear y guardar un usuario para la prueba
		User user = new User();
		user.setUsername("testuser");
		user.setEmail("testuser@example.com");
		userService.saveUser(user);

		// Recuperar el usuario por ID
		User foundUser = userService.getUserById(user.getId());

		// Verificar que el usuario encontrado es correcto
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getUsername()).isEqualTo("testuser");
		assertThat(foundUser.getEmail()).isEqualTo("testuser@example.com");
	}

}