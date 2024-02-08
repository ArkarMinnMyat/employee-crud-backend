package com.example.employeecrudbackend.service;

import com.example.employeecrudbackend.dao.EmployeeDao;
import com.example.employeecrudbackend.dto.EmployeeDto;
import com.example.employeecrudbackend.entity.Employee;
import com.example.employeecrudbackend.exception.ResourceNotFoundException;
import com.example.employeecrudbackend.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.employeecrudbackend.mapper.EmployeeMapper.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDao employeeDao;

    /*
    create employee
    get employee by id
    get all employee
    update employee
    delete employee
     */

    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        return toDto(employeeDao.save(toEntity(employeeDto)));
    }

    public EmployeeDto getEmployeeById(int id){
        return toDto(employeeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id :: " + id)));
    }

    public List<EmployeeDto> getAllEmployees(){
        return employeeDao.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public EmployeeDto updateEmployee(int id,EmployeeDto employeeDto){
        EmployeeDto emp = getEmployeeById(id);
        emp.setEmail(employeeDto.getEmail());
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
        Employee employee = toEntity(emp);
        employee.setId(id);
        return toDto(employeeDao.save(employee));
    }

    public void deleteEmployee(int id){
        if (employeeDao.existsById(id)){
            employeeDao.deleteById(id);
        }else
            throw new ResourceNotFoundException("Employee is not exists with given id :: " + id);

    }
}
