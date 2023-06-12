package com.okaru.springboot.config;

import com.okaru.springboot.repository.EmployeeRepository;
import com.okaru.springboot.repository.IEmployeeRepository;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@AutoConfigureOrder(1)
@EnableJpaRepositories("com.okaru.springboot.repository")
public class RepositoriesConfig {

    @Bean
    public IEmployeeRepository employeeRepository(){
        return new EmployeeRepository();
    }
}
