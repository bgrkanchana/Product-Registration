/*
package com.tcs.demo.controller;

import com.tcs.demo.DemoApplication;
import com.tcs.demo.model.EmployeeEntity;
import com.tcs.demo.repository.EmployeeRepository;
import com.tcs.demo.service.EmployeeService;
import com.tcs.demo.web.EmployeeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class EmployeeControllerTestn {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository repository;
    //EmployeeRepository repository;
    @Autowired
    JpaRepository jpaRepository;

    MockMvc mvc;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    @Test
    public void testGetEmployees() {
        List<EmployeeEntity> list =new ArrayList<>();
        list.add(new EmployeeEntity(1l,"Kanchana","Harisankar","bgrkanchana@gmail.com"));
        list.add(new EmployeeEntity(2l,"Harshini","H","harshini.h@gmail.com"));
        //when(repository.findAll()).thenReturn(list);
        when(employeeService.getAllEmployees()).thenReturn(list);
      //  when(repository.findAll()).thenReturn(list);
        employeeController.getAllEmployees();
        verify(employeeService).getAllEmployees();
    }
}
*/
