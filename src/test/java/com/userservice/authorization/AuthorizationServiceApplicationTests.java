package com.userservice.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

@SpringBootTest
class AuthorizationServiceApplicationTests {
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Autowired
//	RegisteredClientRepository clientRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	@Commit
//	void addEntryInClients() {
//		RegisteredClient registrarClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("registrar-client")
//				.clientSecret(bCryptPasswordEncoder.encode("secret"))
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//				.scope("client.create")
//				.scope("client.read")
//				.build();
//
//		clientRepository.save(registrarClient);
//	}

}
