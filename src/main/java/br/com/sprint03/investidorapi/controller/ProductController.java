package br.com.sprint03.investidorapi.controller;

import br.com.sprint03.investidorapi.dto.CreateProductDto;
import br.com.sprint03.investidorapi.dto.ProductDto;
import br.com.sprint03.investidorapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(
            @RequestBody @Valid CreateProductDto createProductDto,
            UriComponentsBuilder uriBuilder
    ) {
        ProductDto productDto = productService.create(createProductDto);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.id()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable UUID id) {
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok(productDto);
    }
}