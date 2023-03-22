package com.innowise.employeedata.mapper;

import com.innowise.employeedata.dto.EmployeeDto;
import com.innowise.employeedata.entity.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    @Lazy
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract EmployeeDto mapToDto(Employee employee);

    public abstract List<EmployeeDto> mapToDtoList(List<Employee> employees);

    public abstract Employee mapToEntity(EmployeeDto employeeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public abstract void updateEmployee(@MappingTarget Employee employee, EmployeeDto employeeDto);

    @Named("encodePassword")
    protected String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
