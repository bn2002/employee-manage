package com.bn2002.cukcuk.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeResponseDto {
    @Id
    private String id;
    private String employeeCode;
    private String employeeName;
    @JsonFormat(pattern = "dd-MM-YYYY")
    private Date dateOfBirth;
    private String phoneNumber;
    private int gender;
    private String email;
    private String positionName;
    private String departmentName;
    private BigDecimal salary;
    private int workStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getWorkStatus() {
        switch (workStatus) {
            case 0:
                return "Nghỉ việc";
            case 1:
                return "Đang làm việc";
            case 2:
                return "Thực tập";
                default:
                return "Không xác định";
        }
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    public String getGender() {
        switch(gender) {
            case 0:
                return "Nữ";
            case 1:
                return "Nam";
            case 2:
                return "Khác";
            default:
                return "Không xác định";
        }
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
