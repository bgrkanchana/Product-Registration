package com.tcs.demo.web;

import java.util.List;

import com.tcs.demo.exception.RecordNotFoundException;
import com.tcs.demo.model.EmployeeEntity;

import com.tcs.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController
    {
    @Autowired
    EmployeeService service;


	@GetMapping("/list")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();

            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping()
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@Valid @RequestBody EmployeeEntity employee)
                                                    throws RecordNotFoundException {
        //System.out.println("empid"+employee.getId());
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

}