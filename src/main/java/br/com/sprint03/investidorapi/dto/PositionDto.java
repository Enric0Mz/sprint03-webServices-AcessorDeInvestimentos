package br.com.sprint03.investidorapi.dto;

import br.com.sprint03.investidorapi.model.Position;

import java.util.UUID;

public record PositionDto(
    UUID positionId,
    Integer quantity,
    ProductDto product
) {
    public PositionDto(Position position) {
        this(position.getId(), position.getQuantity(), new ProductDto(position.getProduct()));
    }
}