package com.tcs.demo.controller;

import com.tcs.demo.DemoApplication;
import com.tcs.demo.config.BatchConfig;
import com.tcs.demo.web.JobController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class JobControllerTest {

    @InjectMocks
    JobController jobController;
    @Mock
    private BatchConfig batchConfig;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    @Test
    public void testRunJob() throws Exception {


        mockMvc.perform(get("/run/job"))
                .andExpect(status().isOk());
        verify(batchConfig,times(1)).step1();
    }
}
