package com.bn2002.cukcuk.api.services;

import com.bn2002.cukcuk.api.models.Department;
import com.bn2002.cukcuk.api.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }
}
