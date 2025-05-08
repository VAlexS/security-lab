package com.ironhack.security;

import com.ironhack.security.models.User;
import com.ironhack.security.repositories.UserRepository;
import com.ironhack.security.services.JwtService;
import com.ironhack.security.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityApplicationTests {


	@Autowired
	JwtService jwtService;

	@Autowired
	UserService userService;

	User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setUsername("Victor");
		user.setPassword("abcd1234");
		System.out.println("El usuario inicial es: " + user);

		userService.saveUser(user);
	}

	/*@AfterEach
	public void tearDown() {
		userService.deleteUser(user);
	}*/


	@Test
	@DisplayName("Genera un token correctamente")
	void generateToken() {
		String token = jwtService.generateToken("John", "[ROLE_ADMIN]");

		System.out.println("ESTO ES EL TOKEN: " + token);
	}


	@Test
	@DisplayName("La encriptación de passwords es correcta")
	public void passwordEncryption() {
		assertTrue(user.getPassword().startsWith("$2a$")); // todas las strings encriptadas con bcrypt usando el algoritmo que estamos usando deberían empezar así
		System.out.println("este es el user final: " + user);
	}

}
