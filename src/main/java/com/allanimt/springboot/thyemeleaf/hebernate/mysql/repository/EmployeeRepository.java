package com.allanimt.springboot.thyemeleaf.hebernate.mysql.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.allanimt.springboot.thyemeleaf.hebernate.mysql.entity.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}