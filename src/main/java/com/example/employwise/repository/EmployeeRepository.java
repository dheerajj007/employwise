package com.example.employwise.repository;

import com.example.employwise.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    // Additional queries if needed
    Optional<Employee> findByEmployeeName(String employeeName);

}
