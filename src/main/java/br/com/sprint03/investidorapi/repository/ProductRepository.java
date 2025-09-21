package br.com.sprint03.investidorapi.repository;

import br.com.sprint03.investidorapi.model.Product;
import br.com.sprint03.investidorapi.model.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByRiskLevelIn(List<RiskLevel> riskLevels);
}