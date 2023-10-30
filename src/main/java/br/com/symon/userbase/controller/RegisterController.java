package br.com.symon.userbase.controller;

import br.com.symon.userbase.dto.request.UserRegistration;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
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

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> registerUser(@RequestBody @Valid UserRegistration userRegistrationRequest) {
        return Mono.just(userRegistrationRequest)
                .doOnNext(userRegistration -> {
                    // Aqui, você pode adicionar a lógica de registro, como salvar o usuário no banco de dados, etc.
                    log.info("Registro de usuário: {}", userRegistration);
                })
                .then();
    }

}
