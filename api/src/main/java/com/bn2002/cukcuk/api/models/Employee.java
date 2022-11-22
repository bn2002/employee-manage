package com.bn2002.cukcuk.api.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.juli.logging.LogFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Employee.class);

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "EmployeeID")
    private String id;
    @Column(name = "EmployeeCode", length = 20)
    private String employeeCode;
    @Column(name = "EmployeeName", length  = 100)
    private String employeeName;
    @Column(name = "DateOfBirth")
    private Date dateOfBirth;
    @Column(name = "Gender")
    private Integer gender;
    @Column(name = "IdentityNumber", length = 25)
    private String identityNumber;
    @Column(name = "IdentityIssuedPlace", length = 255)
    private String identityIssuedPlace;
    @Column(name = "IdentityIssuedDate")
    private Date identityIssuedDate;
    @Column(name = "Email", unique = true, nullable = false)
    private String email;
    @Column(name = "PhoneNumber", length = 50)
    private String phoneNumber;
    @Column(name = "PositionID", length = 36)
    private String positionId;
    @Column(name = "PositionName", length = 255)
    private String positionName;
    @Column(name = "DepartmentId", length = 36)
    private String departmentId;
    @Column(name = "DepartmentName", length = 255)
    private String departmentName;
    @Column(name = "TaxCode", length = 20)
    private String taxCode;
    @Column(name = "Salary")
    private BigDecimal salary;
    @Column(name = "JoiningDate")
    private Date joiningDate;
    @Column(name = "WorkStatus")
    private Integer workStatus;
    @Column(name = "CreatedDate")
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "CreatedBy", length = 100)
    private String createdBy;
    @Column(name = "ModifiedDate")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    public Employee() {};

    public Employee(String employeeCode, String employeeName, Date dateOfBirth, Integer gender, String identityNumber, String identityIssuedPlace, Date identityIssuedDate, String email, String phoneNumber, String positionId, String positionName, String departmentId, String departmentName, String taxCode, BigDecimal salary, Date joiningDate, Integer workStatus, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.identityNumber = identityNumber;
        this.identityIssuedPlace = identityIssuedPlace;
        this.identityIssuedDate = identityIssuedDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.positionId = positionId;
        this.positionName = positionName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.taxCode = taxCode;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.workStatus = workStatus;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeID=" + id +
                ", employeeCode='" + employeeCode + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", identityNumber='" + identityNumber + '\'' +
                ", identityIssuedPlace='" + identityIssuedPlace + '\'' +
                ", identityIssuedDate=" + identityIssuedDate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", positionId='" + positionId + '\'' +
                ", postionName='" + positionName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", salary=" + salary +
                ", joiningDate=" + joiningDate +
                ", workStatus=" + workStatus +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", modifiedBy=" + modifiedBy +
                '}';
    }

    @PreUpdate
    public void preUpdateFunction() {
        log.info("Update employee " + employeeName);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
