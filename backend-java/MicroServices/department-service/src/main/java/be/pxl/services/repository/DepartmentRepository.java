package be.pxl.services.repository;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentByOrganizationId(Long organizationId);
}

