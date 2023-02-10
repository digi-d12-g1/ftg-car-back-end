package com.ftgcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);

    void updateEmployee(EmployeeDto employeeDto, @MappingTarget Employee employee);

}
