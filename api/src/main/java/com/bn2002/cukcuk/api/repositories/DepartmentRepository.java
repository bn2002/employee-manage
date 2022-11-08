package com.bn2002.cukcuk.api.repositories;

import com.bn2002.cukcuk.api.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
