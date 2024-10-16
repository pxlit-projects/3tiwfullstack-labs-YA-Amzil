package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.exceptions.NotFoundException;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final OrganizationRepository organizationRepository;

    private OrganizationResponse mapToOrganizationResponseWithDepartments(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .build();
    }

    private OrganizationResponse mapToOrganizationResponseWithEmployees(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .employees(organization.getEmployees())
                .build();
    }

    private OrganizationResponse mapToOrganizationResponseWithDepartmentsAndEmployees(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .employees(organization.getEmployees())
                .build();
    }

    @Override
    public void addOrganisation(OrganizationRequest organisationRequest) {
        Organization organization = Organization.builder()
                .name(organisationRequest.getName())
                .address(organisationRequest.getAddress())
                .build();
        organizationRepository.save(organization);
    }

    @Override
    public OrganizationResponse findOrganizationById(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("No organization with id [" + organizationId + "]"));
        return mapToOrganizationResponseWithDepartmentsAndEmployees(organization);
    }

    // Fixed the mapper to use only departments
    @Override
    public List<OrganizationResponse> findByOrganizationIdWithDepartments(Long organizationId) {
        List<Organization> organizations = organizationRepository.findById(organizationId).stream().toList();
        if (organizations.isEmpty()) {
            throw new NotFoundException("No organization with id [" + organizationId + "]");
        }
        return organizations.stream().map(this::mapToOrganizationResponseWithDepartments).toList();
    }

    // Fixed: using the correct mapper for both departments and employees
    @Override
    public List<OrganizationResponse> findByOrganizationIdWithDepartmentsAndEmployees(Long organizationId) {
        List<Organization> organizations = organizationRepository.findById(organizationId).stream().toList();
        if (organizations.isEmpty()) {
            throw new NotFoundException("No organization with id [" + organizationId + "]");
        }
        return organizations.stream().map(this::mapToOrganizationResponseWithDepartmentsAndEmployees).toList();
    }

    // Fixed the correct mapper usage for employees (you can modify this if needed)
    @Override
    public List<OrganizationResponse> findByOrganizationIdWithEmployees(Long organizationId) {
        List<Organization> organizations = organizationRepository.findById(organizationId).stream().toList();
        if (organizations.isEmpty()) {
            throw new NotFoundException("No organization with id [" + organizationId + "]");
        }
        return organizations.stream().map(this::mapToOrganizationResponseWithEmployees).toList();
    }
}
