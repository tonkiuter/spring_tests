package com.okaru.springboot.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.okaru.springboot.controller.EmployeeController;
import com.okaru.springboot.model.Employee;
import jakarta.inject.Inject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class EmployeeMockitoTests {

//    @Inject
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @MockBean
    public EmployeeJpaRepository employeeJpaRepository;

    @InjectMocks
    public EmployeeController employeeController;

    Employee employee1 = new Employee(1L, "Okaru", "Tsukero", "aklivmairkoo@gmail.com");
    Employee employee2 = new Employee(2L, "Nanji", "Kimiko", "nanji.kimiko@gmail.com");
    Employee employee3  = new Employee(3L, "Rauri", "Tsukimi", "enma.666@gmail.com");

    @Before
    public void setup(){
//        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
        ///this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getAllrecords_success() throws Exception {
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1,employee2,employee3));

        Mockito.when(employeeJpaRepository.findAll()).thenReturn(employeeList);

        //mockMvc.perform(MockMvcRequestBuilders.
//                get("/employee")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].firstName").value("Okaru"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$", handler()));
    }



}
