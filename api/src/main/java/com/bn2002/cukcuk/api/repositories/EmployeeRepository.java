package com.bn2002.cukcuk.api.repositories;

import com.bn2002.cukcuk.api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
