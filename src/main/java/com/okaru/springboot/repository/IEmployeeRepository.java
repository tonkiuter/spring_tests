package com.okaru.springboot.repository;

import com.okaru.springboot.model.Employee;

import java.util.List;

public interface IEmployeeRepository {

    public void save(Employee employee);

    public List<Employee> findAll();

    public void findByName(String name);
}
