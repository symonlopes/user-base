package br.com.symon.userbase.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class ValidationError extends RuntimeException {

    public static final ValidationError DUPLICATED_USER = new ValidationError("Usuário já existe esse email", ValidationErrorCode.DUPLICATED_USER);
    public static final ValidationError EMAIL_NOT_PROVIDED = new ValidationError("Email é obrigatório", ValidationErrorCode.EMAIL_NOT_PROVIDED);
    public static final ValidationError EMAIL_INVALID = new ValidationError("Email inválido", ValidationErrorCode.EMAIL_INVALID);
    public static final ValidationError EMPTY_PASSWORD = new ValidationError("Senha é obrigatória", ValidationErrorCode.EMPTY_PASSWORD);

    private int code;
    private String message;
    private String details;

    public ValidationError(String message) {
        this.message = message;
    }

    public ValidationError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static ValidationError fromErrorMessage(String defaultMessage) {
        return new ValidationError(defaultMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError apiValidationFail = (ValidationError) o;
        return code == apiValidationFail.code && Objects.equals(message, apiValidationFail.message) && Objects.equals(details, apiValidationFail.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, details);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
