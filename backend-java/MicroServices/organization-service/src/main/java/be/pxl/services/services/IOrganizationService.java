package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {


    Organization addOrganization(Organization organization);

    OrganizationResponse findOrganizationById(Long organizationId);

    OrganizationResponse findByOrganizationIdWithDepartments(Long organizationId);

    OrganizationResponse findByOrganizationIdWithDepartmentsAndEmployees(Long organizationId);

    OrganizationResponse findByOrganizationIdWithEmployees(Long organizationId);

}
