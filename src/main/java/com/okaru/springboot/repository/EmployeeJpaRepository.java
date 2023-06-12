package com.okaru.springboot.repository;

import com.okaru.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
        Employee findByFirstName(String firstName);

        Optional<Employee> findEmployeeByEmail(String email);
}
