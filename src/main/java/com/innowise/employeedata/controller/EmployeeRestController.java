package com.innowise.employeedata.controller;

import com.innowise.employeedata.dto.EmployeeDto;
import com.innowise.employeedata.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = "/employees")
public class EmployeeRestController {

    final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> showAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        return employeeService.update(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String removeEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return String.format("Employee with id %s was successfully deleted", id);
    }
}
