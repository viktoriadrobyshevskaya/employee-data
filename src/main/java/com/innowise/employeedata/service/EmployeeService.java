package com.innowise.employeedata.service;

import com.innowise.employeedata.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();

    EmployeeDto create(EmployeeDto employee);

    EmployeeDto findById(Long id);

    void deleteById(Long id);

    EmployeeDto findByEmail(String email);

    EmployeeDto update(EmployeeDto employeeDto, Long id);
}
