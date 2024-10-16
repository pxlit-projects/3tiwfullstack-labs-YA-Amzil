package be.pxl.services.services;

import be.pxl.services.domain.dto.OrganizationRequest;
import be.pxl.services.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {

    void addOrganisation(OrganizationRequest organisationRequest);

    OrganizationResponse findOrganizationById(Long organizationId);

    List<OrganizationResponse> findByOrganizationIdWithDepartments(Long organizationId);

    List<OrganizationResponse> findByOrganizationIdWithDepartmentsAndEmployees(Long organizationId);

    List<OrganizationResponse> findByOrganizationIdWithEmployees(Long organizationId);

}
