package br.com.symon.userbase;

import br.com.symon.userbase.dto.request.UserRegistration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserBaseApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private ObjectMapper objectMapper; // Spring automatically configures ObjectMapper


	@Test
	public void whenValidInput_thenReturns200() throws Exception {
		UserRegistration userRegistration = new UserRegistration();
		// preencha o objeto userRegistration com detalhes válidos do usuário

		webTestClient.post().uri("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(userRegistration))
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.isEqualTo("User registered successfully...");
	}


}
