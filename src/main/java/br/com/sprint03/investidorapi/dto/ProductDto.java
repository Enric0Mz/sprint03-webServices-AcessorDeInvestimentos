package br.com.sprint03.investidorapi.dto;

import br.com.sprint03.investidorapi.model.Product;

import java.util.UUID;

public record ProductDto(
    UUID id,
    String ticker,
    String name
) {
    public ProductDto(Product product) {
        this(product.getId(), product.getTicker(), product.getName());
    }
}