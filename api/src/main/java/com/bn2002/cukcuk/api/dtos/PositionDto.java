package com.bn2002.cukcuk.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PositionDto {
    private UUID id;
    private String positionCode;
    private String positionName;
}
