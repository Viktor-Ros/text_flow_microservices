package com.example.employee_service.service;



import com.example.employee_service.dao.EmployeeDao;
import com.example.employee_service.entity.Employee;
import com.example.base_service.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends BaseService<Employee> {

    @Autowired
    private EmployeeDao employeeDao;


    @Transactional
    public List<Employee> getEmployeeListByIdList(List<String> subscriberIdList){
        return subscriberIdList
                .stream()
                .map(s -> employeeDao.getById(s))
                .collect(Collectors.toList());
    }

}
