package com.aftas.aftasapi.dtos.noRelations;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank
    @Email(message = "email cannot be null")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6, max = 20)
    private String password;
}
