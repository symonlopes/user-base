package br.com.symon.userbase.controller;

import br.com.symon.userbase.dto.request.UserRegistration;
import br.com.symon.userbase.dto.response.UserRegistrationResponse;
import br.com.symon.userbase.service.EmailService;
import br.com.symon.userbase.service.PasswordService;
import br.com.symon.userbase.service.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@RestController
@Log4j2
public class RegisterController {
    private final EmailService emailService;
    private final PasswordService passwordService;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public RegisterController(EmailService emailService, PasswordService passwordService, UserRegistrationService userRegistrationService) {
        this.emailService = emailService;
        this.passwordService = passwordService;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserRegistrationResponse> registerUser(@RequestBody @Valid UserRegistration userRegistrationRequest) {

        return Mono.just(userRegistrationRequest)
                .doOnNext(userRegistration -> log.info("Registro de usuário: {}", userRegistration))
                .flatMap(apiResponse -> userRegistrationService.validateDuplicateUser(userRegistrationRequest))
                .then(Mono.defer(() -> userRegistrationService.saveUser(userRegistrationRequest)))
                .thenReturn(UserRegistrationResponse.builder().build());
    }

}
