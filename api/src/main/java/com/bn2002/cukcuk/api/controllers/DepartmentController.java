package com.bn2002.cukcuk.api.controllers;

import com.bn2002.cukcuk.api.dtos.DepartmentDto;
import com.bn2002.cukcuk.api.models.Department;
import com.bn2002.cukcuk.api.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/department")
public class DepartmentController {
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final DepartmentService departmentService;
    public DepartmentController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        departmentService = null;
    }

    @GetMapping("")
    List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments().stream().map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }


}
