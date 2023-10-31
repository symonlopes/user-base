package br.com.symon.userbase.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError extends RuntimeException {

    private int code;
    private String message;
    private String details;

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

}
