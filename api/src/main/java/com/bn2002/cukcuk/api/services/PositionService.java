package com.bn2002.cukcuk.api.services;

import com.bn2002.cukcuk.api.models.Position;
import com.bn2002.cukcuk.api.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository repository;
    public List<Position> getAllPositions() {
        return repository.findAll();
    }
}
