package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(DepartmentRequest departmentRequest);

    List<Department> getAllDepartments();

    Department findDepartmentById(Long departmentId);

    List<DepartmentResponse> findDepartmentByOrganization(Long organizationId);

    List<DepartmentResponse> findDepartmentByOrganizationWithEmployees(Long organizationId);
}
