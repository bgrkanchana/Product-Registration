package com.tcs.demo.controller;

import com.tcs.demo.DemoApplication;
import com.tcs.demo.model.EmployeeEntity;
import com.tcs.demo.repository.EmployeeRepository;
import com.tcs.demo.service.EmployeeService;
import com.tcs.demo.web.EmployeeControllerUI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class EmployeeControllerUITest  {

    @InjectMocks
    EmployeeControllerUI employeeControllerUI;
    @Mock
    EmployeeService service;
    @Mock
    EmployeeRepository repository;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeControllerUI).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<EmployeeEntity>employees=new ArrayList<>();
        employees.add(new EmployeeEntity(1L,"Kanchana","Harisankar","bgrkanchana@gmail.com"));
        Mockito.when(service.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())

                .andExpect(forwardedUrl("list-employees"))
                .andExpect(model().attribute("employees", hasSize(1)))
                .andExpect(model().attribute("employees", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Kanchana")),
                                hasProperty("lastName", is("Harisankar")),
                                hasProperty("email", is("bgrkanchana@gmail.com"))
                        )
                )))
                .andExpect(model().attribute("employees", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Kanchana")),
                                hasProperty("lastName", is("Harisankar")),
                                hasProperty("email", is("bgrkanchana@gmail.com"))
                        )
                )));
        verify(service, times(1)).getAllEmployees();
       // this.mockMvc.perform(get("/").

    }
    @Test
    public void testGetAllEmployees_EmptyList() throws Exception {
        List<EmployeeEntity>employees=new ArrayList<>();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())

                .andExpect(forwardedUrl("list-employees"));
        Mockito.when(service.getAllEmployees()).thenReturn(employees);
        verify(service, times(1)).getAllEmployees();


    }

    @Test
    public void testEditEmployeeByID() throws Exception {
       // EmployeeEntity employee=new EmployeeEntity(1l,"Kanchana","Harisankar","bgrkanchana@gmail.com");
        mockMvc.perform(get("/edit"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add-edit-employee"));

    }
    @Test
    public void testEditEmployeeByIDPassing() throws Exception {
        EmployeeEntity employee=new EmployeeEntity(1L,"Harsha","Harisankar","bgrkanchana@gmail.com");

        mockMvc.perform(get("/edit","/edit/{1l}"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add-edit-employee"));


    }
    @Test
    public void testCreateOrUpdateEmployee() throws Exception {
        EmployeeEntity employee=new EmployeeEntity(10l,"Veda","Avula","ul.veda@gmail.com");
       // Mockito.when(service.createOrUpdateEmployee(Mockito.any(EmployeeEntity.class))).thenReturn(employee);


        mockMvc.perform(post("/","/createEmployee"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("list-employees"));
        Mockito.when(service.createOrUpdateEmployee(employee)).thenReturn(employee);
        employeeControllerUI.createOrUpdateEmployee(employee);
    }
    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(get("/","/delete/{1l}"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("list-employees"));
        employeeControllerUI.deleteEmployeeById(1l);
    }


}
