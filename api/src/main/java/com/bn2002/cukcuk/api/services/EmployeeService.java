package com.bn2002.cukcuk.api.services;

import com.bn2002.cukcuk.api.models.Employee;
import com.bn2002.cukcuk.api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
