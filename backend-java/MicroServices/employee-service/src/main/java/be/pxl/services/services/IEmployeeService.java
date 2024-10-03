package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponse> getAllEmployees();

    void addEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse findEmployeeById(Long employeeId);

    List<EmployeeResponse> findEmployeeByDepartment(Long departmentId);

    List<EmployeeResponse> findEmployeeByOrganization(Long organizationId);
}
