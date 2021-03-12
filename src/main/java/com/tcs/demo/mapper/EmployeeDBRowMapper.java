package com.tcs.demo.mapper;


import com.tcs.demo.model.EmployeeEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


    @Component
    public class EmployeeDBRowMapper implements RowMapper<EmployeeEntity> {


        @Override
        public EmployeeEntity mapRow(ResultSet resultSet, int i) throws SQLException {
            EmployeeEntity employee = new EmployeeEntity();
            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setEmail(resultSet.getString("email"));
            return employee;
        }
    }

