package br.com.symon.userbase.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

public record UserRegistration(
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "Password é obrigatório")
    @Size(min = 6, max = 40, message = "Sua senha deve ter entre 6 e 40 caracteres.")
    String password

){
}
