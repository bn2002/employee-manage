package com.bn2002.cukcuk.api.controllers;

import com.bn2002.cukcuk.api.dtos.EmployeeDto;
import com.bn2002.cukcuk.api.models.ResponseObject;
import com.bn2002.cukcuk.api.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllEmployees(
            @RequestParam(defaultValue = "", name = "keyword") String keyword,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size,
            @RequestParam(defaultValue = "", name = "position_id") String positionId,
            @RequestParam(defaultValue = "", name = "department_id") String departmentId
    ) {

        Map<String, Object> response = employeeService.getAllEmployees(page, size, keyword, positionId, departmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", response));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        employeeService.createNewEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Tạo nhân viên mới thành công", ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getEmployeeById(@PathVariable String id) {
        EmployeeDto result = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Thông tin chi tiết nhân viên", result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateEmployeeBydId(@PathVariable String id, @RequestBody @Valid EmployeeDto employeeDto) {
        employeeService.updateEmployeeById(id, employeeDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Cập nhật dữ liệu thành công", ""));
    }

    @GetMapping("/get-lastest-employee-code")
    public ResponseEntity<ResponseObject> getLastestEmployeeCode() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "", employeeService.getLastestEmployeeCode()));
    }

    @DeleteMapping("/delete-employee")
    public ResponseEntity<ResponseObject> deleteEmployees(@RequestBody List<String> ids) {
        int countDeleted = employeeService.deleteEmployeeByListId(ids);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Xóa thành công", countDeleted));
    }



}
