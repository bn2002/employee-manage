package com.bn2002.cukcuk.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {
    private UUID id;
    private String employeeCode;
    private String employeeName;
    private Date dateOfBirth;
    private int gender;
    private String identityNumber;
    private String identityIssuedPlace;
    private Date identityIssuedDate;
    private String email;
    private String phoneNumber;
    private String positionId;
    private String postionName;
    private String departmentId;
    private String departmentName;
    private String taxCode;
    private BigDecimal salary;
    private Date joiningDate;
    private int workStatus;
    private Date createdDate;
    private String createdBy;

}
