package be.pxl.services.controller;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDepartment(@RequestBody DepartmentRequest departmentRequest){
        departmentService.addDepartment(departmentRequest);
    }

    @GetMapping
    public ResponseEntity getDepartments(){
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }
    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable Long departmentId){
        Department department = departmentService.findDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
    @GetMapping("/organization/{organizationId}")
    public List<DepartmentResponse> findDepartmentByOrganization(@PathVariable Long organizationId) {
        return departmentService.findDepartmentByOrganization(organizationId);
    }
    @GetMapping("/organization/{organizationId}/with-employees")
    public List<DepartmentResponse> findDepartmentByOrganizationWithEmployees(@PathVariable Long organizationId) {
        return departmentService.findDepartmentByOrganizationWithEmployees(organizationId);
    }

}
