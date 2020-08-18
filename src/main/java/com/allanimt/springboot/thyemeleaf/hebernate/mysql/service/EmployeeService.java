package com.allanimt.springboot.thyemeleaf.hebernate.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanimt.springboot.thyemeleaf.hebernate.mysql.entity.Employee;
import com.allanimt.springboot.thyemeleaf.hebernate.mysql.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee insertEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeByID(Integer id) {
        return employeeRepository.getOne(id);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }


}
