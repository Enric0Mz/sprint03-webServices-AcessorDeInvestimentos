package br.com.sprint03.investidorapi.service;

import br.com.sprint03.investidorapi.dto.CreateProductDto;
import br.com.sprint03.investidorapi.dto.ProductDto;
import br.com.sprint03.investidorapi.exception.ResourceNotFoundException;
import br.com.sprint03.investidorapi.model.Product;
import br.com.sprint03.investidorapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto create(CreateProductDto createProductDto) {
        Product newProduct = new Product();
        newProduct.setTicker(createProductDto.ticker());
        newProduct.setName(createProductDto.name());

        Product savedProduct = productRepository.save(newProduct);
        return new ProductDto(savedProduct);
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public ProductDto findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return new ProductDto(product);
    }
}