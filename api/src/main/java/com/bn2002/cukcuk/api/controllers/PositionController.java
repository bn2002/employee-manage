package com.bn2002.cukcuk.api.controllers;

import com.bn2002.cukcuk.api.dtos.PositionDto;
import com.bn2002.cukcuk.api.services.PositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/v1/position")
public class PositionController {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final PositionService positionService;
    public PositionController(ModelMapper modelMapper, PositionService positionService) {
        this.modelMapper = modelMapper;
        this.positionService = positionService;
    }

    @GetMapping(path = "")
    public List<PositionDto> getAllPosition() {
        return positionService.getAllPositions().stream().map(position -> modelMapper.map(position, PositionDto.class)).collect(Collectors.toList());
    }
}
