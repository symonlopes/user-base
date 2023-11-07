package br.com.symon.userbase;

import br.com.symon.userbase.dto.request.UserRegistration;
import br.com.symon.userbase.model.api.ValidationResult;
import br.com.symon.userbase.model.api.ApiResponse;
import br.com.symon.userbase.model.api.ValidationErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class UserBaseApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        UserRegistration userRegistration = new UserRegistration("email@emal.com","abc123");
        webTestClient.post().uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(userRegistration))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void whenInvalidEmail_thenReturns400() throws Exception {
        UserRegistration userRegistration = new UserRegistration("invalidEmail","abc123");
        webTestClient.post().uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(userRegistration))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
