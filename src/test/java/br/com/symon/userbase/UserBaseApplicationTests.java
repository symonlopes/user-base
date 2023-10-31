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
        UserRegistration userRegistration = UserRegistration.builder()
                .email("email@emal.com")
                .password("abc123")
                .build();

        webTestClient.post().uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(userRegistration))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void whenInvalidEmail_thenReturns400() throws Exception {
        UserRegistration userRegistration = UserRegistration.builder()
                .email("invalidEmail")
                .password("abc123")
                .build();

        webTestClient.post().uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(userRegistration))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void email_not_provided_thenReturns400() throws Exception {
        UserRegistration userRegistration = UserRegistration.builder()
                .password("abc123")
                .build();

        ApiResponse response = webTestClient.post().uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(userRegistration))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ApiResponse.class)
                .returnResult()
                .getResponseBody();

        assert response != null;
        assert response.getValidationFails() != null;

        log.info("{}", response);
        Assertions.assertFalse(response.getValidationFails().contains(ValidationResult.builder().code(ValidationErrorCode.EMAIL_INVALID).build()));
    }


}
