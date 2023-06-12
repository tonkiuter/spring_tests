package com.okaru.springboot.repository;

import com.okaru.springboot.repository.IEmployeeRepository;
import com.okaru.springboot.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class EmployeeJpaRepositoryTests {


    @Autowired
    private EmployeeJpaRepository employeeRepository;

    //JUnit test for save employee operation

    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Okaru")
                .lastName("Tsukero")
                .email("aklivmairkoo@gmail.com")
                .build();

        //when - action or the behavior that we are going to test
        employeeRepository.save(employee);
        Employee saveEmployee = employee;

                //then - verify the output
        Assertions.assertThat(saveEmployee).isNotNull();
        Assertions.assertThat(saveEmployee.getId()).isGreaterThan(0);

    }

    @Test
    public void givenEmployeeList_when_FindAll_then_employeesList(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Okaru")
                .lastName("Tsukero")
                .email("aklivmairkoo@gmail.com")
                .build();

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Nanji")
                .lastName("Kimiko")
                .email("aklivmairkoo666@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or behavior that we are going to test
        List<Employee> lista = employeeRepository.findAll();

        //then - verify the output
        Assertions.assertThat(lista).isNotNull();
        Assertions.assertThat(lista.size()).isEqualTo(2);

    }

    @Test
    @Sql({"/sql/insert_fwa.sql"})
    @Transactional
    public void testing_sql_scripts(){
        List<Employee> lista = employeeRepository.findAll();
//        Employee employee = employeeRepository.findByFirstName("nanji");
        for (Employee e : lista){
            System.out.println(e.getFirstName());
        }
        Assertions.assertThat(lista).isNotNull();
//        Assertions.assertThat(employee.getFirstName()).isEqualTo("nanji");

    }
}
