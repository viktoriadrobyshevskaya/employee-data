package com.innowise.employeedata;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.innowise.employeedata.dto.EmployeeDto;
import com.innowise.employeedata.entity.Role;
import com.innowise.employeedata.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeDataApplication implements ApplicationRunner {
    private final EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDataApplication.class, args);
    }

    public void run(ApplicationArguments args) {
        if (employeeService.findAll().isEmpty()) {
            EmployeeDto employee = EmployeeDto.builder()
                    .email("vikitosya@gmail.com")
                    .password("123456")
                    .firstName("Viktoria")
                    .lastName("Drobyshevskaya")
                    .role(Role.ROLE_ADMIN)
                    .build();

            employeeService.create(employee);
        }
    }
}
