package com.ftgcar.controllers;

import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/findAll")
    public List<EmployeeDto> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

}
