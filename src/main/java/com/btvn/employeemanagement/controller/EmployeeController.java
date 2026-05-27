package com.btvn.employeemanagement.controller;

import com.btvn.employeemanagement.dto.ApiDataResponse;
import com.btvn.employeemanagement.dto.EmployeeDTO;
import com.btvn.employeemanagement.entity.Employee;
import com.btvn.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách nhân viên thành công!",
                employees,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Employee>> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin nhân viên bằng id thành công!",
                employee,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Employee>> save(@Valid @RequestBody EmployeeDTO dto) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm nhân viên thành công!",
                employeeService.createEmployee(dto),
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Employee>> update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        Employee update = employeeService.updateEmployee(dto,id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật toàn bộ thông tin nhân viên thành công!",
                update,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Employee>> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật toàn bộ thông tin nhân viên thành công!",
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }
}
