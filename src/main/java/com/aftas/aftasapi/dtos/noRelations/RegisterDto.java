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
    @NotNull(message = "number cannot be null")
    private Integer num;
    @NotBlank
    @Email(message = "email cannot be null")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6, max = 20)
    private String password;
    @NotBlank(message = "name cannot be null")
    private String name;
    @NotBlank(message = "family name cannot be null")
    private String familyName;
    @NotNull(message = "accession date cannot be null")
    private LocalDate accessionDate;
    @NotBlank(message = "nationality cannot be null")
    private String nationality;
    @NotBlank(message = "identity number cannot be null")
    private String identityNumber;
    @NotNull(message = "identity document cannot be null")
    private IdentityDocumentType identityDocument;
    @NotBlank(message = "role cannot be null")
    private String role;
}
