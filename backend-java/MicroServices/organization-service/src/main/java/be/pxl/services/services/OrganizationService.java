package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.exceptions.NotFoundException;
import be.pxl.services.repository.EmployeeRepository;
import be.pxl.services.repository.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{

    private final OrganizationRepository organizationRepository;

    public Organization addOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public OrganizationResponse findOrganizationById(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException("No organization with id [" + organizationId + "]"));
        return new OrganizationResponse(organization.getName(), organization.getAddress(), organization.getEmployees(), organization.getDepartments());
    }

    @Override
    public OrganizationResponse findByOrganizationIdWithDepartments(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException("No organization with id [" + organizationId + "]"));
        return new OrganizationResponse(organization.getName(), organization.getAddress(), organization.getEmployees(), organization.getDepartments());

    }

    @Override
    public OrganizationResponse findByOrganizationIdWithDepartmentsAndEmployees(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException("No organization with id [" + organizationId + "]"));
        return new OrganizationResponse(organization.getName(), organization.getAddress(), organization.getEmployees(), organization.getDepartments());

    }

    @Override
    public OrganizationResponse findByOrganizationIdWithEmployees(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException("No organization with id [" + organizationId + "]"));
        return new OrganizationResponse(organization.getName(), organization.getAddress(), organization.getEmployees(), null);

    }

}
