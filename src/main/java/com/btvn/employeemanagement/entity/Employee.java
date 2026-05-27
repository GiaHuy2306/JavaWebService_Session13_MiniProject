package com.btvn.employeemanagement.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;


@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "employee")
public class Employee {
    private static final AtomicLong idGenerator = new AtomicLong(1);
    private Long id;
    private String fullName;
    private String department;
    private Double salary;

    public Employee(String fullName, String department, Double salary) {
        this.id = idGenerator.getAndIncrement();
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public Employee(Long id, String fullName, String department, Double salary) {
        this.id = idGenerator.getAndIncrement();
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }
}
