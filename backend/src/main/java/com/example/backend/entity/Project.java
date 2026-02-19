package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity // Marks this class as database entity
public class Project {

    @Id // Marks this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID
    private Long id;

    private String name; // Project name

    private String client; // Client name

    private LocalDate startDate; // Project start date

    private LocalDate endDate; // Project end date

    private boolean active = true; // Used for soft delete and history tracking

    // Default constructor required by JPA
    public Project() {}

    // Constructor for creating project
    public Project(String name, String client, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    // Getter for ID
    public Long getId() {
        return id;
    }

    // Getter for project name
    public String getName() {
        return name;
    }

    // Setter for project name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for client
    public String getClient() {
        return client;
    }

    // Setter for client
    public void setClient(String client) {
        this.client = client;
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

    // Getter for active flag
    public boolean isActive() {
        return active;
    }

    // Setter for active flag
    public void setActive(boolean active) {
        this.active = active;
    }
}