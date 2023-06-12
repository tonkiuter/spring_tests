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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeJpaRepository employeeJpaRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1L)
                .firstName("okaru")
                .lastName("tsukero")
                .email("aklivmairkoo@gmail.com")
                .build();
//        employeeJpaRepository = Mockito.mock(EmployeeJpaRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeJpaRepository);
    }

    @DisplayName("JUnit Test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given
        BDDMockito.given(employeeJpaRepository.findEmployeeByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(employeeJpaRepository.save(employee)).willReturn(employee);

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
        BDDMockito.given(employeeJpaRepository.findEmployeeByEmail(employee.getEmail()))
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
}
