package be.pxl.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrganizationServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class OrganizationServiceApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Container
    private static MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @BeforeEach
    public void setup() {
        organizationRepository.deleteAll();
    }

    @Test
    public void testCreateOrganization() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .address("Elfde-Liniestraat 24")
                .build();

        String organizationString = objectMapper.writeValueAsString(organization);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization")
                        .contentType("application/json")
                        .content(organizationString))
                .andExpect(status().isCreated());

        assertEquals(1, organizationRepository.findAll().size());
    }

    @Test
    public void testFindOrganizationById() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .address("Elfde-Liniestraat 24")
                .build();

        organizationRepository.save(organization);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/" + organization.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByOrganizationIdWithDepartments() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .address("Elfde-Liniestraat 24")
                .build();

        organizationRepository.save(organization);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/" + organization.getId() + "/with-departments"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByOrganizationIdWithEmployees() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .address("Elfde-Liniestraat 24")
                .build();

        organizationRepository.save(organization);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/" + organization.getId() + "/with-departments-and-employees"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByOrganizationIdWithDepartmentsAndEmployees() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .address("Elfde-Liniestraat 24")
                .build();

        organizationRepository.save(organization);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/" + organization.getId() + "/with-departments-and-employees"))
                .andExpect(status().isOk());
    }
}
