package br.com.sprint03.investidorapi.service;

import br.com.sprint03.investidorapi.dto.ProductDto;
import br.com.sprint03.investidorapi.exception.ResourceNotFoundException;
import br.com.sprint03.investidorapi.model.Product;
import br.com.sprint03.investidorapi.model.Profile;
import br.com.sprint03.investidorapi.model.RiskLevel;
import br.com.sprint03.investidorapi.model.User;
import br.com.sprint03.investidorapi.repository.ProductRepository;
import br.com.sprint03.investidorapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvestmentSuggestionService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public InvestmentSuggestionService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<ProductDto> suggestProductsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Profile userProfile = user.getProfile();
        List<RiskLevel> acceptableRiskLevels;

        switch (userProfile) {
            case CONSERVADOR:
                acceptableRiskLevels = List.of(RiskLevel.BAIXO);
                break;
            case MODERADO:
                acceptableRiskLevels = List.of(RiskLevel.BAIXO, RiskLevel.MEDIO);
                break;
            case ARROJADO:
                acceptableRiskLevels = List.of(RiskLevel.BAIXO, RiskLevel.MEDIO, RiskLevel.ALTO);
                break;
            default:
                acceptableRiskLevels = List.of();
                break;
        }

        List<Product> suggestedProducts = productRepository.findByRiskLevelIn(acceptableRiskLevels);

        return suggestedProducts.stream().map(ProductDto::new).collect(Collectors.toList());
    }
}