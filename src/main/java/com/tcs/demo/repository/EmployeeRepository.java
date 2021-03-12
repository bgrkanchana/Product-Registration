package com.tcs.demo.repository;

import com.tcs.demo.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {
 
}
