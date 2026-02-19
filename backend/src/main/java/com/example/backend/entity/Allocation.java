package com.example.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // Marks this class as database entity
public class Allocation {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID
    private Long id;

    @ManyToOne // Many allocations can belong to one employee
    @JoinColumn(name = "employee_id", nullable = false) // Foreign key column
    private Employee employee;

    @ManyToOne // Many allocations can belong to one project
    @JoinColumn(name = "project_id", nullable = false) // Foreign key column
    private Project project;

    private LocalDate startDate; // Allocation start date

    private LocalDate endDate; // Allocation end date

    private int allocationPercentage; // Allocation percentage (0–100)

    private boolean active = true; // Soft delete flag to maintain history

    // Default constructor required by JPA
    public Allocation() {}

    // Constructor for creating allocation
    public Allocation(Employee employee, Project project,
                      LocalDate startDate, LocalDate endDate,
                      int allocationPercentage) {

        this.employee = employee;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allocationPercentage = allocationPercentage;
        this.active = true;
    }

    // Getter for ID
    public Long getId() {
        return id;
    }

    // Getter for employee
    public Employee getEmployee() {
        return employee;
    }

    // Setter for employee
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Getter for project
    public Project getProject() {
        return project;
    }

    // Setter for project
    public void setProject(Project project) {
        this.project = project;
    }

    // Getter for start date
    public LocalDate getStartDate() {
        return startDate;
    }

    // Setter for start date
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // Getter for end date
    public LocalDate getEndDate() {
        return endDate;
    }

    // Setter for end date
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Getter for allocation percentage
    public int getAllocationPercentage() {
        return allocationPercentage;
    }

    // Setter for allocation percentage
    public void setAllocationPercentage(int allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    // Getter for active flag
    public boolean isActive() {
        return active;
    }

    // Setter for active flag
    public void setActive(boolean active) {
        this.active = active;
    }
}