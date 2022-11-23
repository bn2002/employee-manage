package com.bn2002.cukcuk.api.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    @Id
    private String id;
    @NotEmpty(message = "Vui lòng nhập mã nhân viên")
    @Size(min = 7, max = 7, message = "Độ dài mã nhân viên gồm 7 ký tự")
    private String employeeCode;
    @NotEmpty(message = "Vui lòng nhập tên nhân viên")
    @Size(max = 100, message = "Độ dài tên tối đa 100 ký tự")
    private String employeeName;
    @Past(message = "Ngày tháng năm sinh không hợp lệ")
    private Date dateOfBirth;
    @Range(min = 0, max = 2, message = "Giới tính bạn chọn không hợp lệ")
    private int gender;
    @NotEmpty(message = "Số CMND/CCCD không hợp lệ")
    @Size(max = 25, message = "Số CMND/CCCD tối đa 25 ký tự")
    private String identityNumber;
    @NotEmpty(message = "Ngày cấp không được để trống")
    @Size(max = 255, message = "Trường Nơi cấp không quá 255 ký tự")
    private String identityIssuedPlace;
    @Past(message = "Ngày cấp không hợp lệ")
    private Date identityIssuedDate;
    @NotEmpty(message = "Vui lòng nhập địa chỉ email")
    @Email(message = "Địa chỉ email không hợp lệ")
    private String email;
    @NotEmpty(message = "Vui lòng nhập số điện thoại")
    @Size(max = 15, message = "Số điện thoại không hợp lệ")
    private String phoneNumber;
    @NotEmpty(message = "ID vị trí không được để trống")
    @Pattern(message = "ID vị trí không hợp lệ", regexp = "[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}")
    private String positionId;
    @Nullable
    private String positionName;
    @NotEmpty(message = "Mã phòng ban không được để trống")
    @Pattern(message = "Mã phòng ban không hợp lệ", regexp = "[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}")
    private String departmentId;
    private String departmentName;
    private String taxCode;
    @NotNull(message = "Lương không được để trống")
    private BigDecimal salary;
    @Past
    private Date joiningDate;
    @NotNull(message = "Hãy nhập tình trạng làm việc")
    @Range(min = 0, max = 2, message = "Tình trạng làm việc không hợp lệ")
    private int workStatus;
    private LocalDateTime createdDate;
    private String createdBy;
}
