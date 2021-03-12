package com.tcs.demo.web;

import com.tcs.demo.config.BatchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
url: http://localhost:8080/run/job
 */

@RestController
@RequestMapping("/run")
public class JobController {

    private final BatchConfig batchConfig;

    @Autowired
    public JobController(BatchConfig batchConfig) {
        this.batchConfig = batchConfig;
    }

    @RequestMapping(value = "/job")
    public String runJob() {
        batchConfig.step1();
        return String.format("Job  submitted successfully.");
    }
}