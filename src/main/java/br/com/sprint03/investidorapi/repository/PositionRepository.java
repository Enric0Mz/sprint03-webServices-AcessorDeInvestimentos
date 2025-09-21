package br.com.sprint03.investidorapi.repository;

import br.com.sprint03.investidorapi.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID> {
    List<Position> findByAccount_Id(UUID accountId);

    Optional<Position> findByAccount_IdAndProduct_Id(UUID accountId, UUID productId);
}