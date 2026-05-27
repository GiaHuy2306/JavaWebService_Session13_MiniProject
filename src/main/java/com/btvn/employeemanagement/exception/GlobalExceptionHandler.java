package com.btvn.employeemanagement.exception;

import com.btvn.employeemanagement.dto.ApiDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(new ApiDataResponse<>(
                false, "Dữ liệu không hợp lệ", errors, HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false, ex.getMessage(), null, HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }
}
