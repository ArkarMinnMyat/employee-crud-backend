package com.example.employeecrudbackend.mapper;

import com.example.employeecrudbackend.dto.EmployeeDto;
import com.example.employeecrudbackend.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
    public static Employee toEntity(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
