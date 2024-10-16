package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.exceptions.NotFoundException;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    private DepartmentResponse mapToDepatmentResponse(Department department){
        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .employees(department.getEmployees())
                .position(department.getPosition())
                .build();
    }

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
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(this::mapToDepatmentResponse).toList();
    }

    @Override
    public DepartmentResponse findDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("No department with id [" + departmentId + "]"));
        return mapToDepatmentResponse(department);
    }

    @Override
    public List<DepartmentResponse> findDepartmentByOrganization(Long organizationId) {
        List<Department> departments = departmentRepository.findDepartmentByOrganizationId(organizationId).stream().toList();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found for organization with id [" + organizationId + "]");
        }
        return departments.stream().map(this::mapToDepatmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> findDepartmentByOrganizationWithEmployees(Long organizationId) {
        List<Department> departments = departmentRepository.findDepartmentByOrganizationId(organizationId).stream().toList();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found for organization with id [" + organizationId + "]");
        }
        return departments.stream().map(this::mapToDepatmentResponse).toList();
    }

}
