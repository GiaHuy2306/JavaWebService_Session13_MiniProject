package com.btvn.employeemanagement.controller;

import com.btvn.employeemanagement.entity.Employee;
import com.btvn.employeemanagement.exception.NotFoundException;
import com.btvn.employeemanagement.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCase1_getAllEmployees_Return200() throws Exception {
        when(employeeService.findAll()).thenReturn(List.of(new Employee("A", "IT", 500.0)));

        mockMvc.perform(get("/api/v1/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testCase2_getById_Found_Return200() throws Exception {
        Employee emp = new Employee("Son", "IT", 1000.0);
        emp.setId(1L);
        when(employeeService.findById(1L)).thenReturn(emp);

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.fullName").value("Son"));
    }

    @Test
    void testCase3_getById_NotFound_Return404() throws Exception {
        when(employeeService.findById(99L)).thenThrow(new NotFoundException("Not found"));

        mockMvc.perform(get("/api/v1/employees/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void testCase4_postEmployee_Return201() throws Exception {
        Employee emp = new Employee("New Emp", "HR", 1200.0);
        when(employeeService.createEmployee(any())).thenReturn(emp);

        String json = "{\"fullName\":\"New Emp\", \"department\":\"HR\", \"salary\":1200}";

        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Thêm nhân viên thành công!"));
    }
}