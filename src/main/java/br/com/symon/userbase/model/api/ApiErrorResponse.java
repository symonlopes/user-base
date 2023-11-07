package br.com.symon.userbase.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;

public record ApiErrorResponse(Collection<ValidationError> errors) {

    public ApiErrorResponse() {
        this(new ArrayList<>());
    }
}
