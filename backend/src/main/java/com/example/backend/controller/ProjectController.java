package com.example.backend.controller;
import com.example.backend.dto.ApiResponse;
import com.example.backend.entity.Project;
import com.example.backend.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // CREATE project → 201 CREATED
    @PostMapping
    public ResponseEntity<ApiResponse<Project>> createProject(
            @RequestBody Project project) {

        Project saved = projectService.createProject(project);

        return ResponseEntity.status(201)
                .body(new ApiResponse<>("Project created successfully", saved));
    }

    // GET all projects → 200 OK
    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {

        return ResponseEntity.ok(
                new ApiResponse<>("Projects fetched successfully",
                        projectService.getAllProjects())
        );
    }

    // GET project by id → 200 or 404
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> getProjectById(
            @PathVariable Long id) {

        return projectService.getProjectById(id)
                .map(project -> ResponseEntity.ok(
                        new ApiResponse<>("Project fetched successfully", project)))
                .orElse(ResponseEntity.status(404)
                        .body(new ApiResponse<>("Project not found", null)));
    }

    // DELETE project → 204
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return ResponseEntity.status(204)
                .body(new ApiResponse<>("Project deleted successfully", null));
    }
}