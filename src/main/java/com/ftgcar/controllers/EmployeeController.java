package com.ftgcar.controllers;

import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.exception.AlreadyExistsException;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //////////////////////////////////// Authentification
    //////////////////////////////////// //////////////////////////////

    @GetMapping("/checkAuth")
    public EmployeeDto getUserCo(@RequestBody EmployeeDto authEmployeeDto) throws NotFoundException {
        return employeeService.getUserCo(authEmployeeDto);
    }

    //////////////////////////////////// Find All //////////////////////////////

    @GetMapping("/findAll")
    public List<EmployeeDto> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    //////////////////////////////////// Find By Id //////////////////////////////

    @GetMapping("/find/{id}")
    public EmployeeDto findEmployeeById(@PathVariable long id) throws NotFoundException {
        return employeeService.findEmployeeById(id);
    }

    //////////////////////////////////// Add Employee //////////////////////////////

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) throws AlreadyExistsException {

        return employeeService.addEmployee(employeeDto);
    }

    //////////////////////////////////// Delete Employee
    //////////////////////////////////// //////////////////////////////
    @DeleteMapping("/delete/{id}")
    // @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public List<EmployeeDto> deleteEmployeById(@PathVariable long id) throws NotFoundException {
        return employeeService.deleteEmployee(id);
    }

    //////////////////////////////////// Update Employee
    //////////////////////////////////// //////////////////////////////
    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) throws AlreadyExistsException {
        return employeeService.updateEmployee(employeeDto);
    }

    /////////////////////////////// Gestion des exceptions
    //////////////////////////////// //////////////////////////////

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
