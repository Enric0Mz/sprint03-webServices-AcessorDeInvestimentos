package br.com.sprint03.investidorapi.controller;

import br.com.sprint03.investidorapi.dto.ProductDto;
import br.com.sprint03.investidorapi.service.InvestmentSuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/suggestions")
public class InvestmentSuggestionController {

    private final InvestmentSuggestionService suggestionService;

    public InvestmentSuggestionController(InvestmentSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getSuggestions(@PathVariable UUID userId) {
        List<ProductDto> suggestions = suggestionService.suggestProductsForUser(userId);
        return ResponseEntity.ok(suggestions);
    }
}