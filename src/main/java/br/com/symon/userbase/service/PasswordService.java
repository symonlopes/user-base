package br.com.symon.userbase.service;

import br.com.symon.userbase.model.api.ValidationError;
import br.com.symon.userbase.model.api.ValidationResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class PasswordService {
    public Mono<ValidationResult> validatePassword(String password) {
        ValidationResult result = ValidationResult.builder().build();
        if (!StringUtils.hasLength(password)) {
            result.getErrors().add(ValidationError.EMPTY_PASSWORD);
        }
        return Mono.just(result);
    }
}
