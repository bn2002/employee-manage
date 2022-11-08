package com.bn2002.cukcuk.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class DepartmentDto {
    private UUID id;
    private String departmentCode;
    private String departmentName;
}
