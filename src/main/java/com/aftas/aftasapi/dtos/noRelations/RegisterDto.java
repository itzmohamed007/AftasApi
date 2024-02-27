package com.aftas.aftasapi.dtos.noRelations;

import com.aftas.aftasapi.enums.IdentityDocumentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {
//    @NotNull(message = "number cannot be null")
    private Integer num;
    @NotNull
    @Email(message = "email cannot be null")
    private String email;
    @NotNull(message = "password cannot be blank")
    @Size(min = 6, max = 20)
    private String password;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "family name cannot be null")
    private String familyName;
    private LocalDate accessionDate;
    @NotNull(message = "nationality cannot be null")
    private String nationality;
    @NotNull(message = "identity number cannot be null")
    private String identityNumber;
    @NotNull(message = "identity document cannot be null")
    private IdentityDocumentType identityDocument;
    @NotNull(message = "role cannot be null")
    private String role;
}
