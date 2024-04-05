package com.example.employee_service.service;


import com.example.employee_service.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.employee_service.utils.TestData.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class EmployeeServiceTests {

    @Autowired
    EmployeeService employeeService;


    @Test()
    @Order(1)
    public void getEmployeeListByIdList(){
        List<String> employeeIdList = new ArrayList<>();
        employeeIdList.add(EMP_1_ID);
        employeeIdList.add(EMP_2_ID);
        employeeIdList.add(EMP_3_ID);

        List<Employee> employeeList = employeeService.getEmployeeListByIdList(employeeIdList);

        Assertions.assertNotNull(employeeList, "Employee list by list id is NULL");
        Assertions.assertNotEquals(employeeList.size(), 0, "Employee list by list id is EMPTY");
        Assertions.assertEquals(employeeList.size(), EMP_ALL_LIST_SIZE, "Employee list by list id is WRONG size");
    }

}
