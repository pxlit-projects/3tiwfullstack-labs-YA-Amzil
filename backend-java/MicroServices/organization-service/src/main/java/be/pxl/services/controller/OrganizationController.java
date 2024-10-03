package be.pxl.services.controller;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.services.IEmployeeService;
import be.pxl.services.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) {
        Organization newOrganization = organizationService.addOrganization(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrganization);
    }
    @GetMapping("/{organizationId}")
    public OrganizationResponse findOrganizationById(@PathVariable Long organizationId) {
        return organizationService.findOrganizationById(organizationId);
    }
    @GetMapping("/{organizationId}/with-departments")
    public OrganizationResponse findByOrganizationIdWithDepartments(@PathVariable Long organizationId) {
        return organizationService.findByOrganizationIdWithDepartments(organizationId);
    }
    @GetMapping("/{organizationId}/with-departments-and-employees")
    public OrganizationResponse findByOrganizationIdWithDepartmentsAndEmployees(@PathVariable Long organizationId) {
        return organizationService.findByOrganizationIdWithDepartmentsAndEmployees(organizationId);
    }

    @GetMapping("/{organizationId}/with-employees")
    public OrganizationResponse findByOrganizationIdWithEmployees(@PathVariable Long organizationId) {
        return organizationService.findByOrganizationIdWithEmployees(organizationId);
    }

}
