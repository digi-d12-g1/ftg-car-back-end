package com.ftgcar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.EmployeeRepository;
import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.entity.Employee;
import com.ftgcar.exception.AlreadyExistsException;
import com.ftgcar.exception.NotFoundException;
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

    //////////////////////////////////// Find All //////////////////////////////

    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::employeeToEmployeeDto).toList();
    }

    public EmployeeDto findEmployeeById(long id) throws NotFoundException {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isEmpty()) {
            throw new NotFoundException("L'employé demandé n'existe pas.");
        }
        return employeeMapper.employeeToEmployeeDto(existingEmployee.get());
    }

    //////////////////////////////////// Add Employee//////////////////////////////

    public List<EmployeeDto> addEmployee(EmployeeDto employeeDto) throws AlreadyExistsException {
        chekIfEmployeeAlreadyExistsForAdd(employeeDto);
        employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto));
        return findAllEmployees();
    }

    private void chekIfEmployeeAlreadyExistsForAdd(EmployeeDto employeeDto) throws AlreadyExistsException {
        Optional<Employee> existingEmployeeById = employeeRepository.findById(employeeDto.id());
        if (existingEmployeeById.isPresent()) {
            throw new AlreadyExistsException(String.format("L'employé avec l'id %s existe déjà.", employeeDto.id()));
        }
        Optional<Employee> existingEmployeeByUsername = employeeRepository.findByUsername(employeeDto.username());
        if (existingEmployeeByUsername.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("L'employé' avec le pseudo %s existe déjà.", employeeDto.username()));
        }
    }

    //////////////////////// Delete Employee By Id
    //////////////////////// ///////////////////////////////////////////
    public List<EmployeeDto> deleteEmployee(long id) throws NotFoundException {
        findEmployeeById(id);
        employeeRepository.deleteById(id);
        return findAllEmployees();
    }
    //////////////////////////////////// Update Employee
    //////////////////////////////////// //////////////////////////////

    public List<EmployeeDto> updateEmployee(EmployeeDto employeeDto) throws AlreadyExistsException {
        Optional<Employee> employeeToUpdate = employeeRepository.findById(employeeDto.id());
        if (employeeToUpdate.isPresent()) {
            Optional<Employee> employeeExistingWithUsername = employeeRepository.findByUsername(employeeDto.username());
            if (employeeExistingWithUsername.isPresent()
                    && !employeeExistingWithUsername.get().getId().equals(employeeDto.id())) {
                throw new AlreadyExistsException(
                        String.format("Le pseudo %s existe déjà.", employeeDto.username()));
            }
            employeeMapper.updateEmployee(employeeDto, employeeToUpdate.get());
        } else {
            addEmployee(employeeDto);
        }

        return findAllEmployees();
    }
}
