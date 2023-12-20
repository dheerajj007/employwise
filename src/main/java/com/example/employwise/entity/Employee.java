package com.example.employwise.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Document
@Data
public class Employee {

    @Id
    private String id;
    @NotBlank(message = "EmployeeName is required")
    private String employeeName;

    @NotBlank(message = "PhoneNumber is required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String reportsTo;
    private String profileImage;

    // No need for explicit getters, setters, and constructors with Lombok

    // Other methods if needed
}