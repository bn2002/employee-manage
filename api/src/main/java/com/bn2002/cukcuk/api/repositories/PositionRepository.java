package com.bn2002.cukcuk.api.repositories;

import com.bn2002.cukcuk.api.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, String> {
    public Optional<Position> getPositionById(String positionId);
}
