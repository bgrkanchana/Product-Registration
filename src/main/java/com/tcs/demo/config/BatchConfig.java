package com.tcs.demo.config;

import javax.sql.DataSource;

import com.tcs.demo.mapper.EmployeeDBRowMapper;
import com.tcs.demo.model.EmployeeEntity;
import com.tcs.demo.processor.EmployeeProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;




    @Configuration
    @EnableBatchProcessing
    public class BatchConfig {

        //@Autowired
        JobBuilderFactory jobBuilderFactory;

        //@Autowired
        StepBuilderFactory stepBuilderFactory;

        //@Autowired
        DataSource dataSource;

        @Autowired
        public BatchConfig(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,DataSource dataSource) {
            this.jobBuilderFactory = jobBuilderFactory;
            this.stepBuilderFactory = stepBuilderFactory;
            this.dataSource = dataSource;
        }


        @Bean
        public JdbcCursorItemReader<EmployeeEntity> reader(){
            JdbcCursorItemReader<EmployeeEntity> cursorItemReader = new JdbcCursorItemReader<>();
            cursorItemReader.setDataSource(dataSource);
            cursorItemReader.setSql("SELECT id,first_name, last_name,email FROM TBL_EMPLOYEES");
            cursorItemReader.setRowMapper(new EmployeeDBRowMapper());
            return cursorItemReader;
        }

        @Bean
        public EmployeeProcessor processor(){
            return new EmployeeProcessor();
        }

        @Bean
        public FlatFileItemWriter<EmployeeEntity> writer(){
            FlatFileItemWriter<EmployeeEntity> writer = new FlatFileItemWriter<>();
            writer.setResource(new ClassPathResource("employee.csv"));

            DelimitedLineAggregator<EmployeeEntity> lineAggregator = new DelimitedLineAggregator<>();
            lineAggregator.setDelimiter(",");

            BeanWrapperFieldExtractor<EmployeeEntity>  fieldExtractor = new BeanWrapperFieldExtractor<>();
            fieldExtractor.setNames(new String[]{"id","firstName","lastName","email"});
            lineAggregator.setFieldExtractor(fieldExtractor);

            writer.setLineAggregator(lineAggregator);
            return writer;
        }

        @Bean
        public Step step1(){
            return stepBuilderFactory.get("step1").<EmployeeEntity,EmployeeEntity>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
        }

        @Bean
        public Job exportEmployeeJob(){
            return jobBuilderFactory.get("exportEmployeeJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
        }
    }
