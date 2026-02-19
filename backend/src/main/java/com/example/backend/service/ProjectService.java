package com.example.backend.service;

import com.example.backend.entity.Project;
import com.example.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as service layer component
public class ProjectService {

    private final ProjectRepository projectRepository;

    // Constructor injection to connect repository layer
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Save a new project to database
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Fetch all projects from database
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Fetch project by ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // Soft delete project by setting active = false
    public void deleteProject(Long id) {

        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isPresent()) {

            Project project = optionalProject.get();

            project.setActive(false); // mark project as inactive

            projectRepository.save(project); // update in database
        }
    }
}