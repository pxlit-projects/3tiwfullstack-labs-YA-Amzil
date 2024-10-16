package be.pxl.services.controller;

import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;
    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponse> findOrganizationById(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findOrganizationById(organizationId));
    }

    @GetMapping("/{organizationId}/with-departments")
    public ResponseEntity<OrganizationResponse> findByOrganizationIdWithDepartments(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findByOrganizationIdWithDepartments(organizationId));
    }

    @GetMapping("/{organizationId}/with-departments-and-employees")
    public ResponseEntity<OrganizationResponse> findByOrganizationIdWithDepartmentsAndEmployees(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findByOrganizationIdWithDepartmentsAndEmployees(organizationId));
    }

    @GetMapping("/{organizationId}/with-employees")
    public ResponseEntity<OrganizationResponse> findByOrganizationIdWithEmployees(@PathVariable Long organizationId) {
        return ResponseEntity.ok(organizationService.findByOrganizationIdWithEmployees(organizationId));
    }
}
