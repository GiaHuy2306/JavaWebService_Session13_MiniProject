package com.btvn.employeemanagement.service;

import com.btvn.employeemanagement.dto.EmployeeDTO;
import com.btvn.employeemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee createEmployee(EmployeeDTO dto);
    Employee updateEmployee(EmployeeDTO dto, Long id);
    void deleteEmployee(Long id);
}
