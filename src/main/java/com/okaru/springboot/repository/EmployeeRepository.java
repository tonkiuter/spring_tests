package com.okaru.springboot.repository;

import com.okaru.springboot.model.Employee;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeRepository implements IEmployeeRepository{

    @Inject
    private EmployeeJpaRepository employeeJpaRepository;

    @Transactional
    public void save(Employee employee) {
        System.out.println("This entity is: " + employee.getFirstName());
        System.out.println("Nani desuka: " + employeeJpaRepository.findByFirstName(employee.getFirstName()));
        employeeJpaRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> lista = employeeJpaRepository.findAll();
        return lista;
    }

    @Override
    public void findByName(String name) {
        employeeJpaRepository.findByFirstName(name);

    }
}
