package br.com.sprint03.investidorapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAccountDto(
    @NotBlank
    String accountNumber,

    @NotNull
    UUID userId
) {}