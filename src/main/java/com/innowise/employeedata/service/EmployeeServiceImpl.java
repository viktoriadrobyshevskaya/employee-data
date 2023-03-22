package com.innowise.employeedata.service;

import com.innowise.employeedata.dto.EmployeeDto;
import com.innowise.employeedata.entity.Employee;
import com.innowise.employeedata.exception.EmployeeException;
import com.innowise.employeedata.mapper.EmployeeMapper;
import com.innowise.employeedata.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeMapper.mapToDtoList(employeeRepository.findAll());
    }

    @Override
    @Transactional
    public EmployeeDto create(EmployeeDto employeeDto) {
        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        Employee newEmp = employeeMapper.mapToEntity(employeeDto);
        return employeeMapper.mapToDto(employeeRepository.save(newEmp));
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        return employeeMapper.mapToDto(employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeException("Could not find Employee by email: " + email)));
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto, Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeMapper.updateEmployee(employee, employeeDto);
            return employeeMapper.mapToDto(employee);
        }

        throw new EmployeeException("Could not find Employee by ID: " + id);
    }

    @Override
    public EmployeeDto findById(Long id) {
        return employeeMapper.mapToDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Could not find Employee by ID: " + id)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
