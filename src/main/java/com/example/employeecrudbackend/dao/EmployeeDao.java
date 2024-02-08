package com.example.employeecrudbackend.dao;

import com.example.employeecrudbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}
