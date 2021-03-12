
package com.tcs.demo.processor;


import com.tcs.demo.model.EmployeeEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeEntity, EmployeeEntity> {

    @Override
    public EmployeeEntity process(EmployeeEntity employee) throws Exception {

        return employee;
    }



}


