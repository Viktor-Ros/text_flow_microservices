package com.example.employee_service.dao;

import com.example.base_service.dao.BaseDao;
import com.example.employee_service.entity.Employee;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDao extends BaseDao<Employee> {
    public EmployeeDao() {
        super(Employee.class);
    }
}
