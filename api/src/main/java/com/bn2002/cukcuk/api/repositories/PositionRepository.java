package com.bn2002.cukcuk.api.repositories;

import com.bn2002.cukcuk.api.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {
}
