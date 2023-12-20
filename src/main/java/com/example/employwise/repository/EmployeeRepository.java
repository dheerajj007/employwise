package com.example.employwise.repository;

import com.example.employwise.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    // Additional queries if needed
}
