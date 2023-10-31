package br.com.symon.userbase.service;

import br.com.symon.userbase.model.api.ValidationError;
import br.com.symon.userbase.model.api.ValidationResult;
import br.com.symon.userbase.model.api.ValidationErrorCode;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class EmailService {
    public Mono<ValidationResult> validateEmail(String email) {
        ValidationResult result = ValidationResult.builder().build();
        if (!StringUtils.hasLength(email)) {
            result.getErrors().add(ValidationError.builder().code(ValidationErrorCode.EMAIL_NOT_PROVIDED).message("Email é obrigatório").build());
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            result.getErrors().add(ValidationError.builder().code(ValidationErrorCode.EMAIL_INVALID).message("Email inválido").build());
        }
        return Mono.just(result);
    }
}
