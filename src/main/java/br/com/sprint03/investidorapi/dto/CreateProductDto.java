package br.com.sprint03.investidorapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProductDto(
    @NotBlank
    @Size(max = 10)
    String ticker,

    @NotBlank
    String name
) {}