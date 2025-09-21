package br.com.sprint03.investidorapi.dto;

import br.com.sprint03.investidorapi.model.RiskLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProductDto(
    @NotBlank
    @Size(max = 10)
    String ticker,

    @NotBlank
    String name,

    @NotNull
    RiskLevel riskLevel
) {}