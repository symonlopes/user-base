package br.com.symon.userbase.model.api;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ValidationResult {

    @Builder.Default
    private Collection<ValidationError> errors = new ArrayList<>();

}
