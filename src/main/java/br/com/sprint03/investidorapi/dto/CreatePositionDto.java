package br.com.sprint03.investidorapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreatePositionDto(
    @NotNull
    UUID accountId,

    @NotNull
    UUID productId,

    @NotNull
    @Min(1)
    Integer quantity
) {}