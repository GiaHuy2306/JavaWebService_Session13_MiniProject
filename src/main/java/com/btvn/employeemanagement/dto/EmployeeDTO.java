package com.btvn.employeemanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDTO {
    @NotBlank(message = "Full Name không được bỏ trống!")
    private String fullName;
    @NotBlank(message = "Department không được bỏ trống!")
    private String department;
    @Min(value = 0, message = "Lương phải lớn hơn 0!")
    private Double salary;
}
