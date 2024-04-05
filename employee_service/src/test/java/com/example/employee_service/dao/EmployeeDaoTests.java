package com.example.employee_service.dao;

import com.example.employee_service.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.employee_service.utils.TestData.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class EmployeeDaoTests {

    @Autowired
    EmployeeDao employeeDao;

    private static String EMP_ID_NEW;


    @Test
    @Order(1)
    public void save(){
        Employee employee = new Employee(EMP_NAME_NEW);
        Employee savedEmployee = employeeDao.save(employee);

        Assertions.assertNotNull(savedEmployee, "Employee is NOT saved");
        Assertions.assertNotEquals(savedEmployee.getId(), 0, "Employee was created INCORRECT");

        EMP_ID_NEW = savedEmployee.getId();
    }

    @Test
    @Order(2)
    public void getById(){
        Employee employee = employeeDao.getById(EMP_1_ID);

        Assertions.assertNotNull(employee, "Employee is NOT get by id");
    }

    @Test
    @Order(3)
    public void delete(){
        String message = employeeDao.deleteById(EMP_ID_NEW);

        Assertions.assertEquals( Employee.class.getName() + " with id = " + EMP_ID_NEW + " successfully DELETED", message,"Employee is NOT deleted");
    }

    @Test
    @Order(4)
    public void getAll(){
        List<Employee> employeeList = employeeDao.getAllList();

        Assertions.assertNotNull(employeeList, "All Employee list is NULL");
        Assertions.assertNotEquals(employeeList.size(), 0, "All Employee list is EMPTY");
        Assertions.assertEquals(employeeList.size(), EMP_ALL_LIST_SIZE, "All Employee list is WRONG size");
    }

}
