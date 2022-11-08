package com.bn2002.cukcuk.api.services;

import com.bn2002.cukcuk.api.models.Employee;
import com.bn2002.cukcuk.api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee createNewEmployee(Employee employee) {
        return new Employee();
    }

    public boolean checkExists(String employeeCode) {
        return true;
    }

    public String getLastestEmployeeCode() {
        String currentEmployeeCode = repository.getMaxEmployeeCode();
        if(currentEmployeeCode.length() < 1) {
            return "NV00001";
        }
        String newEmployeeCode = "NV" + String.valueOf(Integer.parseInt(currentEmployeeCode.substring(2, currentEmployeeCode.length()) + 1));
        return newEmployeeCode;
    }
}
