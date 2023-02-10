package com.ftgcar.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.EmployeeRepository;
import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.mapper.EmployeeMapper;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::employeeToEmployeeDto).toList();
    }

}
