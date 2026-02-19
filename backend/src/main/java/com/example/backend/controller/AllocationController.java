package com.example.backend.controller;

import com.example.backend.entity.Allocation;
import com.example.backend.service.AllocationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // Marks this class as REST controller
@RequestMapping("/api/allocations") // Base URL
public class AllocationController {

    private final AllocationService allocationService;

    // Constructor injection
    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    // API to create allocation
    @PostMapping
    public Allocation createAllocation(

            @RequestParam Long employeeId,
            @RequestParam Long projectId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam int percentage
    ) {

        return allocationService.createAllocation(
                employeeId,
                projectId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                percentage
        );
    }

    // API to fetch all allocations
    @GetMapping
    public List<Allocation> getAllAllocations() {
        return allocationService.getAllAllocations();
    }

    // API to soft delete allocation
    @DeleteMapping("/{id}")
    public void deleteAllocation(@PathVariable Long id) {
        allocationService.deleteAllocation(id);
    }
}