package com.bn2002.cukcuk.api.repositories;

import com.bn2002.cukcuk.api.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    public Optional<Department> getDepartmentById(String departmentId);
}
