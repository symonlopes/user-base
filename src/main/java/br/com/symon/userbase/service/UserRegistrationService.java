package br.com.symon.userbase.service;

import br.com.symon.userbase.dto.request.UserRegistration;
import br.com.symon.userbase.model.api.User;
import br.com.symon.userbase.model.api.ValidationError;
import br.com.symon.userbase.model.api.ValidationErrorCode;
import br.com.symon.userbase.model.api.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserRegistrationService {

    private final EmailService emailService;
    private final PasswordService passwordService;

    @Autowired
    public UserRegistrationService(EmailService emailService, PasswordService passwordService) {
        this.emailService = emailService;
        this.passwordService = passwordService;
    }

    public Mono<ValidationResult> validateRegistration(UserRegistration userRegistration) {
        return Mono.just(ValidationResult.builder().build())
                .flatMap(validationResult -> emailService.validateEmail(userRegistration.getEmail()))
                .flatMap(validationResult -> passwordService.validatePassword(userRegistration.getPassword()));
    }

    public Mono<Void> validateDuplicateUser(UserRegistration userRegistrationRequest) {
        return Mono.error(ValidationError.builder().message("Usuário já existe com email xyz...").code(ValidationErrorCode.DUPLICATED_USER).build());
    }

    public Mono<User> saveUser(UserRegistration userRegistrationRequest) {
        return Mono.just(User.builder().id(UUID.randomUUID().toString()).build());
    }
}
