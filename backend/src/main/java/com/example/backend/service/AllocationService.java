package com.example.backend.service;

import com.example.backend.entity.Allocation;
import com.example.backend.entity.Employee;
import com.example.backend.entity.Project;
import com.example.backend.repository.AllocationRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service // Marks this class as service layer
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    // Constructor injection
    public AllocationService(
            AllocationRepository allocationRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {

        this.allocationRepository = allocationRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    // Create allocation with conflict detection
    // Create allocation with STRICT conflict detection (no overlaps allowed)
    public Allocation createAllocation(
            Long employeeId,
            Long projectId,
            LocalDate startDate,
            LocalDate endDate,
            int percentage) {

        // Validate percentage range
        if (percentage <= 0 || percentage > 100) {
            throw new RuntimeException("Allocation percentage must be between 1 and 100");
        }

        // Fetch employee from database
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Fetch project from database
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Find overlapping allocations
        List<Allocation> overlappingAllocations = allocationRepository.findOverlappingAllocations(
                employeeId,
                startDate,
                endDate);

        // STRICT RULE: Do not allow any overlapping allocation
        if (!overlappingAllocations.isEmpty()) {
            throw new RuntimeException(
                    "Employee already has an allocation during this period");
        }

        // Create allocation object
        Allocation allocation = new Allocation(
                employee,
                project,
                startDate,
                endDate,
                percentage);

        // Save allocation
        return allocationRepository.save(allocation);
    }

    // Fetch all allocations
    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    // Soft delete allocation
    public void deleteAllocation(Long id) {

        Optional<Allocation> optionalAllocation = allocationRepository.findById(id);

        if (optionalAllocation.isPresent()) {

            Allocation allocation = optionalAllocation.get();

            allocation.setActive(false); // mark inactive

            allocationRepository.save(allocation);
        }
    }
}