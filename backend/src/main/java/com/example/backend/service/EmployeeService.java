package com.example.backend.service;

import com.example.backend.entity.Employee;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as service layer component
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor injection to connect repository
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Save a new employee to database
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Fetch all active employees from database
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Fetch employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Soft delete employee by setting active = false
    public void deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setActive(false); // mark employee as inactive
            employeeRepository.save(employee);
        }
    }
}