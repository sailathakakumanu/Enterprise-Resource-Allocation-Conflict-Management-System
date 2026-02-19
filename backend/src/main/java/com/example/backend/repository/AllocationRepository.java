package com.example.backend.repository;

import com.example.backend.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository // Marks this interface as repository layer
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    // Finds overlapping allocations for an employee between given dates
    @Query("SELECT a FROM Allocation a " +
           "WHERE a.employee.id = :employeeId " +
           "AND a.active = true " +
           "AND a.startDate <= :endDate " +
           "AND a.endDate >= :startDate")
    List<Allocation> findOverlappingAllocations(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate
    );
}