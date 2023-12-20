package com.example.employwise.controller;


import com.example.employwise.entity.Employee;
import com.example.employwise.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String employeeId) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(employeeId);

        return employeeOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "employeeName") String sortBy
    ) {
        try {
            Page<Employee> employees = employeeService.getAllEmployees(page, pageSize, sortBy);
            return ResponseEntity.ok(employees);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
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

    @GetMapping("/manager/{employeeId}/{level}")
    public ResponseEntity<?> getNthLevelManager(
            @PathVariable String employeeId,
            @PathVariable int level
    ) {
        try {
            Optional<Employee> nthLevelManager = employeeService.getNthLevelManager(employeeId, level);

            return nthLevelManager.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
