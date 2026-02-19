import React, { useEffect, useState } from "react";
import { getAllocations } from "../api/api";

// Component to display all allocations
function AllocationList() {

  // State to store allocations
  const [allocations, setAllocations] = useState([]);

  // Load allocations when component loads
  useEffect(() => {

    fetchAllocations();

  }, []);

  // Fetch allocations from backend
  const fetchAllocations = async () => {

    try {

      const response = await getAllocations();

      setAllocations(response.data.data);

    } catch (error) {

      alert("Failed to fetch allocations");

    }

  };

  return (

    <div>

      <h2>Allocation List</h2>

      <table border="1">

        <thead>

          <tr>

            <th>ID</th>
            <th>Employee</th>
            <th>Project</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Percentage</th>

          </tr>

        </thead>

        <tbody>

          {allocations.map(allocation => (

            <tr key={allocation.id}>

              <td>{allocation.id}</td>

              <td>{allocation.employee.name}</td>

              <td>{allocation.project.name}</td>

              <td>{allocation.startDate}</td>

              <td>{allocation.endDate}</td>

              <td>{allocation.allocationPercentage}%</td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>

  );

}

export default AllocationList;