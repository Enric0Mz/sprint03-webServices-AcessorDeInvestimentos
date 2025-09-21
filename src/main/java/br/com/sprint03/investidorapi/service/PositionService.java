package br.com.sprint03.investidorapi.service;

import br.com.sprint03.investidorapi.dto.CreatePositionDto;
import br.com.sprint03.investidorapi.dto.PositionDto;
import br.com.sprint03.investidorapi.exception.ResourceNotFoundException;
import br.com.sprint03.investidorapi.model.Account;
import br.com.sprint03.investidorapi.model.Position;
import br.com.sprint03.investidorapi.model.Product;
import br.com.sprint03.investidorapi.repository.AccountRepository;
import br.com.sprint03.investidorapi.repository.PositionRepository;
import br.com.sprint03.investidorapi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    public PositionService(PositionRepository positionRepository, AccountRepository accountRepository, ProductRepository productRepository) {
        this.positionRepository = positionRepository;
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public PositionDto create(CreatePositionDto createPositionDto) {
        Account account = accountRepository.findById(createPositionDto.accountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        Product product = productRepository.findById(createPositionDto.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Position position = positionRepository
                .findByAccount_IdAndProduct_Id(account.getId(), product.getId())
                .orElse(new Position());

        if (position.getId() == null) {
            position.setAccount(account);
            position.setProduct(product);
            position.setQuantity(createPositionDto.quantity());
        } else {
            position.setQuantity(position.getQuantity() + createPositionDto.quantity());
        }

        Position savedPosition = positionRepository.save(position);
        return new PositionDto(savedPosition);
    }

    public List<PositionDto> findByAccountId(UUID accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new ResourceNotFoundException("Account not found");
        }
        return positionRepository.findByAccount_Id(accountId)
                .stream()
                .map(PositionDto::new)
                .collect(Collectors.toList());
    }
}