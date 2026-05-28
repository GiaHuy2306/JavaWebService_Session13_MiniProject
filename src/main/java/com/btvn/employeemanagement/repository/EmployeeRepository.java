package com.btvn.employeemanagement.repository;

import com.btvn.employeemanagement.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class EmployeeRepository {
    public final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1L,"Nguyễn Văn A", "Engineering",15000000.0));
        employees.add(new Employee(2L,"Tran Thi B", "Human Resources",12000000.0));
        employees.add(new Employee(3L,"Le Van C", "Finance",13500000.0));
        employees.add(new Employee(4L,"Pham Thi D", "Marketing",11000000.0));
        employees.add(new Employee(5L,"Hoang Van E", "Engineering",18000000.0));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findEmployeeById(Long id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        IntStream.range(0, employees.size())
                .filter(i -> employees.get(i).getId().equals(employee.getId()))
                .findFirst()
                .ifPresent(i -> employees.set(i, employee));
        return employee;
    }

    public void delete(Employee employee) {
        employees.remove(employee);
    }
}
