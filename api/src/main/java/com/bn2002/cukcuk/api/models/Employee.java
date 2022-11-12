package com.bn2002.cukcuk.api.models;

import org.apache.juli.logging.LogFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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
    private String postionName;
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

    public Employee(String employeeCode, String employeeName, Date dateOfBirth, Integer gender, String identityNumber, String identityIssuedPlace, Date identityIssuedDate, String email, String phoneNumber, String positionId, String postionName, String departmentId, String departmentName, String taxCode, BigDecimal salary, Date joiningDate, Integer workStatus, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
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
        this.postionName = postionName;
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
                ", postionName='" + postionName + '\'' +
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

    public String getEmployeeID() {
        return id;
    }

    public void setEmployeeID(String employeeID) {
        id = employeeID;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getIdentityIssuedPlace() {
        return identityIssuedPlace;
    }

    public void setIdentityIssuedPlace(String identityIssuedPlace) {
        this.identityIssuedPlace = identityIssuedPlace;
    }

    public Date getIdentityIssuedDate() {
        return identityIssuedDate;
    }

    public void setIdentityIssuedDate(Date identityIssuedDate) {
        this.identityIssuedDate = identityIssuedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPostionName() {
        return postionName;
    }

    public void setPostionName(String postionName) {
        this.postionName = postionName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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
