package com.bn2002.cukcuk.api.services;

import com.bn2002.cukcuk.api.dtos.EmployeeDto;
import com.bn2002.cukcuk.api.models.Department;
import com.bn2002.cukcuk.api.models.Employee;
import com.bn2002.cukcuk.api.models.Position;
import com.bn2002.cukcuk.api.repositories.DepartmentRepository;
import com.bn2002.cukcuk.api.repositories.EmployeeRepository;
import com.bn2002.cukcuk.api.repositories.PositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityExistsException;
import javax.swing.text.html.Option;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void createNewEmployee(EmployeeDto employee) {
        // Kiểm tra email tồn tại hay chưa
        Optional<Employee> isEmailExist =  employeeRepository.getEmployeesByEmail(employee.getEmail());
        if(isEmailExist.isPresent()) {
            throw  new EntityExistsException("Email này đã tồn tại, hãy kiểm tra lại");
        }

        //Kiểm tra mã nhân viên tồn tại hay chưa
        Optional<Employee> isEmployeeCodeExist =  employeeRepository.getEmployeesByEmployeeCode(employee.getEmployeeCode());
        if(isEmployeeCodeExist.isPresent()) {
            throw  new EntityExistsException("Mã nhân viên này đã tồn tại, hãy kiểm tra lại");
        }
        // Kiểm tra số CMND/CCCD tồn tại chưa
        Optional<Employee> isIdentityNumberExist =  employeeRepository.getEmployeesByIdentityNumber(employee.getIdentityNumber());
        if(isIdentityNumberExist.isPresent()) {
            throw  new EntityExistsException("Số CMND/CCCD này đã tồn tại, hãy kiểm tra lại");
        }
        // Lấy thông tin vị trí và phòng ban
        Optional<Position> position = positionRepository.getPositionById(employee.getPositionId());
        if(!position.isPresent()) {
            throw new NoSuchElementException("Vị trí này không tồn tại");
        }

        Optional<Department> department = departmentRepository.getDepartmentById(employee.getDepartmentId());
        if(!department.isPresent()) {
            throw new NoSuchElementException("Phòng ban này không tồn tại");
        }
        // Set 1 số giá trị theo ID mà người dùng cung cấp
        employee.setDepartmentName(department.get().getDepartmentName());
        employee.setPostionName(position.get().getPositionName());
        employee.setCreatedBy("Nguyen Duy Doanh");
        // Map Dto qua Entity
        Employee newEmployee = modelMapper.map(employee, Employee.class);
        System.out.println(newEmployee.toString());
        employeeRepository.save(newEmployee);
        return ;
    }

    public boolean checkExists(String employeeCode) {
        return true;
    }

    public String getLastestEmployeeCode() {
        String currentEmployeeCode = employeeRepository.getMaxEmployeeCode();
        if(currentEmployeeCode.length() < 1) {
            return "NV00001";
        }
        String newEmployeeCode = "NV" + String.valueOf(Integer.parseInt(currentEmployeeCode.substring(2, currentEmployeeCode.length()) + 1));
        return newEmployeeCode;
    }

    public EmployeeDto getEmployeeBgId(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()) {
            throw new NoSuchElementException("Nhân viên này không tồn tại, vui lòng kiểm tra lại");
        }
        EmployeeDto employeeDto = modelMapper.map(employee.get(), EmployeeDto.class);
        return employeeDto;
    }
}
