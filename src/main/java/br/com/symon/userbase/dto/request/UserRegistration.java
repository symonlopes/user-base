package br.com.symon.userbase.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserRegistration {

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Password é obrigatório")
    @Min(value = 6, message = "Sua senha deve ter no mínimo 6 caracteres.")
    @Max(value = 40, message = "Sua senha deve ter no máximo 40 caracteres.")
    private String password;
}
