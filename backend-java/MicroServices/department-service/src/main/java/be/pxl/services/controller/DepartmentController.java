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
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addDepartment(@RequestBody DepartmentRequest departmentRequest){
        departmentService.addDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> findDepartmentById(@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.findDepartmentById(departmentId));
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<DepartmentResponse>> findDepartmentByOrganization(@PathVariable Long organizationId) {
        return ResponseEntity.ok(departmentService.findDepartmentByOrganization(organizationId));
    }
    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity<List<DepartmentResponse>> findDepartmentByOrganizationWithEmployees(@PathVariable Long organizationId) {
        return ResponseEntity.ok(departmentService.findDepartmentByOrganizationWithEmployees(organizationId));
    }

}
