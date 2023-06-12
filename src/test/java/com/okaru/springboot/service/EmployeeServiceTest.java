package com.okaru.springboot.service;

import com.okaru.springboot.exception.ResourceNoFoundException;
import com.okaru.springboot.model.Employee;
import com.okaru.springboot.repository.EmployeeJpaRepository;
import com.okaru.springboot.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeJpaRepository employeeJpaRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private Employee employee1;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1L)
                .firstName("okaru")
                .lastName("tsukero")
                .email("aklivmairkoo@gmail.com")
                .build();

        employee1 = Employee.builder()
                .id(2L)
                .firstName("Nanji")
                .lastName("Kimiko")
                .email("aklivmairkoo666@gmail.com")
                .build();
//        employeeJpaRepository = Mockito.mock(EmployeeJpaRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeJpaRepository);
    }

    @DisplayName("JUnit Test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given
        given(employeeJpaRepository.findEmployeeByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeJpaRepository.save(employee)).willReturn(employee);

        System.out.println("employeJpaRespository " + employeeJpaRepository);
        System.out.println("employeService " + employeeService);

        //when
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
    }


    @DisplayName("JUnit Test for saveEmployee method when fails")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
        // given
        given(employeeJpaRepository.findEmployeeByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println("employeJpaRespository " + employeeJpaRepository);
        System.out.println("employeService " + employeeService);

        //when
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNoFoundException.class, ()->{
            employeeService.saveEmployee(employee);
        });

        //then
        verify(employeeJpaRepository, never()).save(any(Employee.class));
    }

    @DisplayName("JUnit Test for getAll employees method" )
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        //given
        given(employeeJpaRepository.findAll()).willReturn(List.of(employee, employee1));

        //when - action or the behavior that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        //then - verify output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).size().isEqualTo(2);

    }

    @DisplayName("JUnit Test for getAll employees method negative scenario" )
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenfails(){
        //given - precondition or setup
        given(employeeJpaRepository.findAll()).willReturn(Collections.emptyList());

        //when - action or the behavior that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        //then - verify output
        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList).size().isEqualTo(0);

    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee(){
        //given - precondition or setup
        given(employeeJpaRepository.findEmployeeById(1L)).willReturn(Optional.of(employee));

        //when - action or the behavior that we are going to test
        Optional<Employee> findEmployee = employeeService.getEmployeeById(1L);

        //then - verify output
        Assertions.assertThat(findEmployee).isNotNull();
        Assertions.assertThat(findEmployee.get().getFirstName()).isEqualTo("okaru");

    }

    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given
        given(employeeJpaRepository.save(employee)).willReturn(employee);
        employee.setFirstName("Alvaro");
        employee.setEmail("cancerfire_yamialvaro@hotmail.com");

        //when
        employeeService.updateEmployee(employee);

        //then
        Assertions.assertThat(employee.getFirstName()).isEqualTo("Alvaro");
        Assertions.assertThat(employee.getEmail()).isEqualTo("cancerfire_yamialvaro@hotmail.com");

    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnsNothing(){
        //given
        willDoNothing().given(employeeJpaRepository).deleteById(1L);

        //when
        employeeService.deleteEmployee(1L);

        //then
        verify(employeeJpaRepository, times(1)).deleteById(any());
    }


}
