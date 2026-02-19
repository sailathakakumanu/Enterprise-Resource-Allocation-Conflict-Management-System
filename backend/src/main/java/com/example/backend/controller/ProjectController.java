package com.example.backend.controller;

import com.example.backend.entity.Project;
import com.example.backend.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marks this class as REST API controller
@RequestMapping("/api/projects") // Base URL for project APIs
public class ProjectController {

    private final ProjectService projectService;

    // Constructor injection to connect service layer
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // API to create a new project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    // API to fetch all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // API to fetch project by ID
    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // API to soft delete project
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}