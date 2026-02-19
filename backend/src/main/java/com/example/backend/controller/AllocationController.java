package com.example.backend.controller;
import com.example.backend.dto.AllocationRequest;
import com.example.backend.dto.ApiResponse;
import com.example.backend.entity.Allocation;
import com.example.backend.service.AllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    // CREATE allocation → 201 CREATED using request body DTO
    @PostMapping
    public ResponseEntity<ApiResponse<Allocation>> createAllocation(
            @RequestBody AllocationRequest request) {

        Allocation allocation = allocationService.createAllocation(
                request.getEmployeeId(),
                request.getProjectId(),
                request.getStartDate(),
                request.getEndDate(),
                request.getPercentage());

        return ResponseEntity.status(201)
                .body(new ApiResponse<>(
                        "Allocation created successfully",
                        allocation));
    }

    // GET allocations → 200 OK
    @GetMapping
    public ResponseEntity<ApiResponse<List<Allocation>>> getAllAllocations() {

        return ResponseEntity.ok(
                new ApiResponse<>("Allocations fetched successfully",
                        allocationService.getAllAllocations()));
    }

    // DELETE allocation → 204
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAllocation(
            @PathVariable Long id) {

        allocationService.deleteAllocation(id);

        return ResponseEntity.status(204)
                .body(new ApiResponse<>(
                        "Allocation deleted successfully", null));
    }
}