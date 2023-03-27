package com.innowise.employeedata.configuration;

import static java.util.Objects.isNull;

import com.innowise.employeedata.entity.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innowise.employeedata.dto.EmployeeDto;
import com.innowise.employeedata.mapper.EmployeeMapper;
import com.innowise.employeedata.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository repository;

    @Override
    public EmployeeUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByEmail(email);

        if (employee.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new EmployeeUserDetails(employee.get());
    }
}
