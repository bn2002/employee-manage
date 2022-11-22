package com.bn2002.cukcuk.api.services;

import com.bn2002.cukcuk.api.dtos.EmployeeDto;
import com.bn2002.cukcuk.api.dtos.EmployeeResponseDto;
import com.bn2002.cukcuk.api.models.Department;
import com.bn2002.cukcuk.api.models.Employee;
import com.bn2002.cukcuk.api.models.Position;
import com.bn2002.cukcuk.api.repositories.DepartmentRepository;
import com.bn2002.cukcuk.api.repositories.EmployeeRepository;
import com.bn2002.cukcuk.api.repositories.PositionRepository;
import com.bn2002.cukcuk.api.repositories.specs.EmployeeSpecification;
import com.bn2002.cukcuk.api.repositories.specs.SearchCriteria;
import com.bn2002.cukcuk.api.repositories.specs.SearchOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.data.jpa.domain.Specification.where;

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
    public Map<String, Object> getAllEmployees(int page, int size, String keyword, String positionId, String departmentId) {
        Specification<Employee> spec = where(null);

        // Tìm kiếm theo từ khóa
        if(keyword.length() > 0) {
            EmployeeSpecification esEmployeeCode = new EmployeeSpecification();
            esEmployeeCode.add(new SearchCriteria("employeeCode", keyword, SearchOperation.MATCH));
            spec = spec.and(esEmployeeCode);

            EmployeeSpecification esEmployeeName = new EmployeeSpecification();
            esEmployeeName.add(new SearchCriteria("employeeName", keyword, SearchOperation.MATCH));
            spec = spec.or(esEmployeeName);

            EmployeeSpecification esEmployeePhone = new EmployeeSpecification();
            esEmployeePhone.add(new SearchCriteria("phoneNumber", keyword, SearchOperation.MATCH));
            spec = spec.or(esEmployeePhone);
        }

        // Nếu người dùng filter theo positionId
        if(positionId.length() > 0) {
            EmployeeSpecification esPositionId = new EmployeeSpecification();
            esPositionId.add(new SearchCriteria("positionId", positionId, SearchOperation.EQUAL));
            spec = spec.and(esPositionId);
        }

        // Nếu người dùng filter theo departmentId
        if(departmentId.length() > 0) {
            EmployeeSpecification esDepartmentId = new EmployeeSpecification();
            esDepartmentId.add(new SearchCriteria("departmentId", departmentId, SearchOperation.EQUAL));
            spec = spec.and(esDepartmentId);
        }


        Pageable paging = PageRequest.of(page, size);
        Page<Employee> pageEmpl;
        List<Employee> employees = new ArrayList<Employee>();
        pageEmpl = employeeRepository.findAll(spec, paging);
        employees = pageEmpl.getContent();
        // Mapping qua Dto
        Map<String, Object> response = new HashMap<>();
        List<EmployeeResponseDto> employeesDto = Arrays.asList(modelMapper.map(employees, EmployeeResponseDto[].class));
        response.put("employees", employeesDto);
        response.put("currentPage", pageEmpl.getNumber());
        response.put("totalItems", pageEmpl.getTotalElements());
        response.put("totalPages", pageEmpl.getTotalPages());

        return response;
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
        employee.setPositionName(position.get().getPositionName());
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
        String newEmployeeCode = "NV" + String.valueOf(Integer.parseInt(currentEmployeeCode.substring(2, currentEmployeeCode.length())) + 1);
        return newEmployeeCode;
    }

    public EmployeeDto getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()) {
            throw new NoSuchElementException("Nhân viên này không tồn tại, vui lòng kiểm tra lại");
        }
        EmployeeDto employeeDto = modelMapper.map(employee.get(), EmployeeDto.class);
        return employeeDto;
    }
    public void updateEmployeeById(String employeeId, EmployeeDto newEmployeeData) {
        Optional<Employee> employees = employeeRepository.findById(employeeId);
        if(!employees.isPresent()) {
            throw new NoSuchElementException("Nhân viên này không tồn tại, hãy kiểm tra lại");
        }
        Employee employee = employees.get();
        // Nếu thay đổi mã nhân viên thì phải check xem mã nhân viên đó đã có ai sử dụng chưa
        if(newEmployeeData.getEmployeeCode().length() >  0 && employee.getEmployeeCode().equals(newEmployeeData.getEmployeeCode()) == false) {
            Optional<Employee> isExistsEmployeeCode = employeeRepository.getEmployeesByEmployeeCodeAndIdNot(newEmployeeData.getEmployeeCode(), employeeId);
            if(isExistsEmployeeCode.isPresent()) {
                throw  new EntityExistsException("Mã nhân viên này đã tồn tại, hãy sử dụng số khác");
            }
        }
        // Nếu email thay đổi,check xem email đã có ai dùng chưa
        if(newEmployeeData.getEmail().length() > 0 && employee.getEmail().equals(newEmployeeData.getEmail()) == false) {
            Optional<Employee> isExistsEmail = employeeRepository.getEmployeesByEmailAndIdNot(newEmployeeData.getEmail(), employeeId);
            if(isExistsEmail.isPresent()) {
                throw new EntityExistsException("Địa chỉ email này đã tồn tại, hãy sử dụng địa chỉ khác");
            }
        }
        // Nếu CMND/CCCD thay đổi, check xem đã có ai dùng số đó chưa
        if(newEmployeeData.getIdentityNumber().length() > 0 && employee.getIdentityNumber().equals(newEmployeeData.getIdentityNumber()) == false) {
            Optional<Employee> isExistsIdentityNumber = employeeRepository.getEmployeesByIdentityNumberAndIdNot(newEmployeeData.getIdentityNumber(), employeeId);
            if(isExistsIdentityNumber.isPresent()) {
                throw new EntityExistsException("Số CMND/CCCD này đã tồn tại, hãy sử dụng số khác");
            }
        }

        // Nếu có thay đổi về vị trí làm việc, cần lấy lại tên vị trí
        String positionName = employee.getPositionName();
        if(employee.getPositionId().equals(newEmployeeData.getPositionId()) == false) {
            Optional<Position> position = positionRepository.getPositionById(employee.getPositionId());
            if(!position.isPresent()) {
                throw new NoSuchElementException("Vị trí này không tồn tại");
            }
            positionName = position.get().getPositionName();
        }

        // Nếu thay đổi về phòng ban làm việc, cần lấy lại tn phòng ban
        String departmentName = employee.getDepartmentName();
        if(employee.getDepartmentId().equals(newEmployeeData.getDepartmentId()) == false) {
            Optional<Department> department = departmentRepository.getDepartmentById(employee.getDepartmentId());
            if(!department.isPresent()) {
                throw new NoSuchElementException("Phòng ban này không tồn tại");
            }
            departmentName = department.get().getDepartmentName();
        }


        employee.setEmployeeCode(newEmployeeData.getEmployeeCode());
        employee.setEmployeeName(newEmployeeData.getEmployeeName());
        employee.setDateOfBirth(newEmployeeData.getDateOfBirth());
        employee.setGender(newEmployeeData.getGender());
        employee.setIdentityNumber(newEmployeeData.getIdentityNumber());
        employee.setIdentityIssuedPlace(newEmployeeData.getIdentityIssuedPlace());
        employee.setIdentityIssuedDate(newEmployeeData.getIdentityIssuedDate());
        employee.setEmail(newEmployeeData.getEmail());
        employee.setPhoneNumber(newEmployeeData.getPhoneNumber());
        employee.setPositionId(newEmployeeData.getPositionId());
        employee.setPositionName(positionName);
        employee.setDepartmentId(newEmployeeData.getDepartmentId());
        employee.setDepartmentName(departmentName);
        employee.setTaxCode(newEmployeeData.getTaxCode());
        employee.setSalary(newEmployeeData.getSalary());
        employee.setJoiningDate(newEmployeeData.getJoiningDate());
        employee.setWorkStatus(newEmployeeData.getWorkStatus());
        employee.setDateOfBirth(newEmployeeData.getDateOfBirth());
        employee.setModifiedDate(LocalDateTime.now());
        employeeRepository.save(employee);
    }

    public int deleteEmployeeByListId(List<String> ids) {
       return employeeRepository.deleteAllByIdIn(ids);
    }


}
