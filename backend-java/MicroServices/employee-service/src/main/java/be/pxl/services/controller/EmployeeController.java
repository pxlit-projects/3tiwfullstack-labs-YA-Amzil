package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    //@Autowired // Not needed with Lombok's @RequiredArgsConstructor annotation --> Constructor Injection is used here to inject the IEmployeeService implementation (EmployeeService) into this controller class (EmployeeController)
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.addEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // HTTP 201 Created
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>> findEmployeeByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.findEmployeeByDepartment(departmentId));
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<EmployeeResponse>> findEmployeeByOrganization(@PathVariable Long organizationId) {
        return ResponseEntity.ok(employeeService.findEmployeeByOrganization(organizationId));
    }
}
