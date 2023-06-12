package com.okaru.springboot.service.impl;

import com.okaru.springboot.exception.ResourceNoFoundException;
import com.okaru.springboot.model.Employee;
import com.okaru.springboot.repository.EmployeeJpaRepository;
import com.okaru.springboot.service.EmployeeService;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeJpaRepository employeeJpaRepository;

    public EmployeeServiceImpl(EmployeeJpaRepository employeeJpaRepository){
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> saveEmployee = employeeJpaRepository.findEmployeeByEmail(employee.getEmail());

        if(saveEmployee.isPresent()){
            throw new ResourceNoFoundException("Employee already exists with given email: " + employee.getEmail());
        }
        return employeeJpaRepository.save(employee);
    }
}
