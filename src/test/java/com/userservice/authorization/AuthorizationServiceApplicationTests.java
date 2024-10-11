package com.userservice.authorization;

import com.userservice.authorization.model.dto.UserDTO;
import com.userservice.authorization.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AuthorizationServiceApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllUsersTest() {
		List<UserDTO> users = userService.getAllUsers();
		Assertions.assertNotNull(users);
	}


}
