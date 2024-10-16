package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Void> addOrganisation(@RequestBody OrganizationRequest organizationRequest) {
        organizationService.addOrganisation(organizationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponse> findOrganizationById(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findOrganizationById(organizationId));
    }

    @GetMapping("/{organizationId}/with-departments")
    public ResponseEntity<List<OrganizationResponse>> findByOrganizationIdWithDepartments(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findByOrganizationIdWithDepartments(organizationId));
    }

    @GetMapping("/{organizationId}/with-departments-and-employees")
    public ResponseEntity<List<OrganizationResponse>> findByOrganizationIdWithDepartmentsAndEmployees(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findByOrganizationIdWithDepartmentsAndEmployees(organizationId));
    }

    @GetMapping("/{organizationId}/with-employees")
    public ResponseEntity<List<OrganizationResponse>> findByOrganizationIdWithEmployees(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findByOrganizationIdWithEmployees(organizationId));
    }
}
