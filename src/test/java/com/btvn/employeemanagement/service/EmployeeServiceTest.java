package com.btvn.employeemanagement.service;

import com.btvn.employeemanagement.entity.Employee;
import com.btvn.employeemanagement.exception.NotFoundException;
import com.btvn.employeemanagement.repository.EmployeeRepository;
import com.btvn.employeemanagement.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getById_Found() {
        Employee emp = new Employee("Nguyen Van A", "IT", 1000.0);
        emp.setId(1L);
        when(employeeRepository.findEmployeeById(1L)).thenReturn(emp);

        Employee result = employeeService.findById(1L);
        assertEquals("Nguyen Van A", result.getFullName());
    }

    @Test
    void getById_NotFound_ThrowException() {
        when(employeeRepository.findEmployeeById(1L)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> employeeService.findById(1L));
    }
}
