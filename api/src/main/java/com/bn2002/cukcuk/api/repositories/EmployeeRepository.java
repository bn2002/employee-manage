package com.bn2002.cukcuk.api.repositories;

import com.bn2002.cukcuk.api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.PreUpdate;
import javax.swing.text.html.Option;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "SELECT MAX(employeeCode) FROM Employee")
    String getMaxEmployeeCode();

    Optional<Employee> getEmployeesByEmail(String email);

    Optional<Employee> getEmployeesByEmployeeCode(String employeeCode);

    Optional<Employee> getEmployeesByIdentityNumber(String identityNumber);

    Optional<Employee> getEmployeesByEmployeeCodeAndIdNot(String employeeCode, String employeeId);

    Optional<Employee> getEmployeesByEmailAndIdNot(String email, String employeeId);

    Optional<Employee> getEmployeesByIdentityNumberAndIdNot(String identityNumber, String employeeId);
}


