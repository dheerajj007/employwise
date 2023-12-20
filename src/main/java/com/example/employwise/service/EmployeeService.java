package com.example.employwise.service;

import com.example.employwise.entity.Employee;
import com.example.employwise.repository.EmployeeRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Validator validator;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, Validator validator) {
        this.employeeRepository = employeeRepository;
        this.validator = validator;
    }
    public String addEmployee(Employee employee) {
        // Perform manual validation
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation error: " + violations.iterator().next().getMessage());
        }

        // Generate a unique UUID as the ID field
        employee.setId(UUID.randomUUID().toString());

        // Save the employee to the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Return the ID if the employee is successfully added
        return savedEmployee.getId();
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public void deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Optional<Employee> updateEmployeeById(String employeeId, Employee updatedEmployee) {
        if (employeeRepository.existsById(employeeId)) {
            updatedEmployee.setId(employeeId);
            return Optional.of(employeeRepository.save(updatedEmployee));
        }
        return Optional.empty(); // Employee with the given ID not found
    }

}
