package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DepartmentServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class DepartmentServiceApplicationTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

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
        departmentRepository.deleteAll();
    }

    @Test
    public void testCreateDepartment() throws Exception {
        Department department = Department.builder()
                .name("HR")
                .position("Manager")
                .build();

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(1, departmentRepository.findAll().size());
    }

    @Test
    public void testGetDepartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/department"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department department = Department.builder()
                .name("HR")
                .position("Manager")
                .build();

        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/" + department.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDepartmentByOrganization() throws Exception {
        Department department = Department.builder()
                .name("HR")
                .position("Manager")
                .organizationId(1L)
                .build();

        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/" + department.getOrganizationId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDepartmentByOrganizationWithEmployees() throws Exception {
        Department department = Department.builder()
                .name("HR")
                .position("Manager")
                .organizationId(1L)
                .build();

        departmentRepository.save(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/" + department.getOrganizationId() + "/with-employees"))
                .andExpect(status().isOk());
    }
}
