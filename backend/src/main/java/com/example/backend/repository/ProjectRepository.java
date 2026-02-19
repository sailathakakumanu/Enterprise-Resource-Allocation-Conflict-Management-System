package com.example.backend.repository;

import com.example.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as repository layer component
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // JpaRepository automatically provides CRUD operations
    // save(project)
    // findById(id)
    // findAll()
    // delete(project)

}