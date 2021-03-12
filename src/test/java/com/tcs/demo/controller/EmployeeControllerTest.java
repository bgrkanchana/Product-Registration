package com.tcs.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tcs.demo.AbstractTest;

import com.tcs.demo.exception.RecordNotFoundException;
import com.tcs.demo.model.EmployeeEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.http.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
public class EmployeeControllerTest extends AbstractTest {


    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        String uri = "/employees/list";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        EmployeeEntity[] employeeEntities = super.mapFromJson(content, EmployeeEntity[].class);
        assertTrue(employeeEntities.length > 0);

    }
    @Test
    public void testGetEmployeeById() throws Exception {
        String uri = "/employees/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        EmployeeEntity employeeEntity=super.mapFromJson(content,EmployeeEntity.class);
        assertTrue(employeeEntity.getId()==1);
    }
    @Test
    public void testGetEmployeeByIdException() throws Exception {
        String uri = "/employees/10";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);


    }
    @Test
    public void testCreateEmployee() throws Exception {
        String uri = "/employees";
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(3l);
        employee.setFirstName("Dora");
        employee.setLastName("Harish");
        String inputJson = super.mapToJson(employee);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,mapToJson(employee));
    }
    @Test
    public void testUpdateEmployee() throws Exception {
        String uri = "/employees";
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1l);
        employee.setFirstName("Dora");
        employee.setLastName("Harish");
        String inputJson = super.mapToJson(employee);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,mapToJson(employee));
    }
    @Test
    public void testCreateOrUpdateEmployeeValidException() throws Exception {
        String uri = "/employees";
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(3l);
        employee.setFirstName("haaa");
        employee.setLastName("");
        employee.setEmail("harish.h@gmail.com");
        String inputJson = super.mapToJson(employee);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
       // String content = mvcResult.getResponse().getContentAsString();
        //assertEquals(content,mapToJson(employee));
    }
    @Test
    public void testDeleteEmployee() throws Exception {
        String uri = "/employees/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }
    @Test
    public void testDeleteEmployeeInternalAPIException() throws Exception {
        String uri = "/employee/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);

    }
    @Test
    public void testDeleteException() throws Exception {
        String uri = "/employees/10";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);

    }
}


