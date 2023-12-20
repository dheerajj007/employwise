package com.example.employwise.controller;


import com.example.employwise.entity.Employee;
import com.example.employwise.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee) {
        try {
            String employeeId = employeeService.addEmployee(employee);
            return ResponseEntity.ok(employeeId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable String employeeId) {
        try {
            employeeService.deleteEmployeeById(employeeId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeById(
            @PathVariable String employeeId,
            @Valid @RequestBody Employee updatedEmployee
    ) {
        Optional<Employee> result = employeeService.updateEmployeeById(employeeId, updatedEmployee);

        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
