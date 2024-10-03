package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.exceptions.NotFoundException;
import be.pxl.services.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .organizationId(departmentRequest.getOrganizationId())
                .position(departmentRequest.getPosition())
                .build();
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new NotFoundException("No department with id [" + departmentId + "]"));
    }

    @Override
    public List<DepartmentResponse> findDepartmentByOrganization(Long organizationId) {
        List<Department> departments = departmentRepository.findDepartmentByOrganizationId(organizationId).stream().toList();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found for organization with id [" + organizationId + "]");
        }
        List<DepartmentResponse> departmentDTOS = new ArrayList<>();
        for (Department department:departments
        ) {
            departmentDTOS.add(new DepartmentResponse(department.getOrganizationId(), department.getName(), department.getEmployees(), department.getPosition()));
        }
        return departmentDTOS;
    }

    @Override
    public List<DepartmentResponse> findDepartmentByOrganizationWithEmployees(Long organizationId) {
        List<Department> departments = departmentRepository.findDepartmentByOrganizationId(organizationId).stream().toList();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found for organization with id [" + organizationId + "]");
        }
        List<DepartmentResponse> departmentDTOS = new ArrayList<>();
        for (Department department:departments
        ) {
            departmentDTOS.add(new DepartmentResponse(department.getOrganizationId(), department.getName(), department.getEmployees(), department.getPosition()));
        }
        return departmentDTOS;
    }

}
