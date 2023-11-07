package br.com.symon.userbase.controller;

import br.com.symon.userbase.dto.request.UserRegistration;
import br.com.symon.userbase.dto.response.UserRegistrationResponse;
import br.com.symon.userbase.model.User;
import br.com.symon.userbase.service.EmailService;
import br.com.symon.userbase.service.PasswordService;
import br.com.symon.userbase.service.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.EntityResponse;
import reactor.core.publisher.Mono;

@Validated
@RestController
@Log4j2
public class RegisterController {
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public RegisterController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserRegistrationResponse>> registerUser(@RequestBody @Valid UserRegistration userRegistrationRequest) {
        return Mono.just(userRegistrationRequest)
                .doOnNext(userRegistration -> log.info("Registro de usuário: {}", userRegistration))
                // Assuming validateDuplicateUser returns Mono<UserRegistration>
                .flatMap(userRegistrationService::validateDuplicateUser)
                // Assuming saveUser returns Mono<UserRegistrationResponse>
                .flatMap(unused -> transformToUser(userRegistrationRequest))
                .flatMap(userRegistrationService::saveUser)
                // Wrap the UserRegistrationResponse in a ResponseEntity
                .map(userRegistrationResponse -> ResponseEntity.ok(new UserRegistrationResponse()));
    }

    private Mono<User> transformToUser(UserRegistration userRegistrationRequest) {
        return Mono.just(new User("","",""));
    }

}
