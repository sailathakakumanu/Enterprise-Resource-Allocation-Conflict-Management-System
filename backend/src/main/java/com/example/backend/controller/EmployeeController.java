package com.example.backend.controller;
import com.example.backend.dto.ApiResponse;
import com.example.backend.entity.Employee;
import com.example.backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE employee → 201 CREATED
    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(
            @RequestBody Employee employee) {

        Employee saved = employeeService.createEmployee(employee);

        return ResponseEntity
                .status(201)
                .body(new ApiResponse<>("Employee created successfully", saved));
    }

    // GET all employees → 200 OK
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        return ResponseEntity.ok(
                new ApiResponse<>("Employees fetched successfully", employees)
        );
    }

    // GET employee by id → 200 OK or 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id)
                .map(emp -> ResponseEntity.ok(
                        new ApiResponse<>("Employee fetched successfully", emp)))
                .orElse(ResponseEntity.status(404)
                        .body(new ApiResponse<>("Employee not found", null)));
    }

    // DELETE employee → 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.status(204)
                .body(new ApiResponse<>("Employee deleted successfully", null));
    }
}