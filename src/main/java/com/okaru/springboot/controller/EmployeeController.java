package com.okaru.springboot.controller;

import com.okaru.springboot.model.Employee;
import com.okaru.springboot.repository.EmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;


//    @GetMapping(value = {"/employeeId"})
//    public Employee getEmployee(@PathVariable(value = "employeeId") Long employeeId){
//
//        return employeeJpaRepository.findById(employeeId).get();
//    }

    @GetMapping
    public List<Employee> getAllEmployees(){

        return employeeJpaRepository.findAll();

    }

}
