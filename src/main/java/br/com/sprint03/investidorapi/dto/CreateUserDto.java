package br.com.sprint03.investidorapi.dto;

import br.com.sprint03.investidorapi.model.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreateUserDto(
    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotNull
    @Past
    LocalDate birthDate,

    @NotNull
    Profile profile
) {}