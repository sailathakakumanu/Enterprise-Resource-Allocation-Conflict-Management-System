import React, { useState, useEffect } from "react";
import { createAllocation, getEmployees, getProjects } from "../api/api";

// Component to create allocation
function AllocationForm() {

  // State to store allocation form data
  const [allocation, setAllocation] = useState({
    employeeId: "",
    projectId: "",
    startDate: "",
    endDate: "",
    percentage: ""
  });

  // State to store employees list
  const [employees, setEmployees] = useState([]);

  // State to store projects list
  const [projects, setProjects] = useState([]);

  // Load employees and projects when component loads
  useEffect(() => {

    fetchEmployees();
    fetchProjects();

  }, []);

  // Fetch employees from backend
  const fetchEmployees = async () => {

    try {

      const response = await getEmployees();

      setEmployees(response.data.data);

    } catch (error) {

      alert("Failed to load employees");

    }

  };

  // Fetch projects from backend
  const fetchProjects = async () => {

    try {

      const response = await getProjects();

      setProjects(response.data.data);

    } catch (error) {

      alert("Failed to load projects");

    }

  };

  // Handle input changes
  const handleChange = (event) => {

    setAllocation({
      ...allocation,
      [event.target.name]: event.target.value
    });

  };

  // Handle form submit
  const handleSubmit = async (event) => {

    event.preventDefault();

    try {

      const response = await createAllocation(allocation);

      alert(response.data.message);

      // Reset form
      setAllocation({
        employeeId: "",
        projectId: "",
        startDate: "",
        endDate: "",
        percentage: ""
      });

    } catch (error) {

      alert(error.response.data.message);

    }

  };

  return (

    <div>

      <h2>Create Allocation</h2>

      <form onSubmit={handleSubmit}>

        {/* Employee dropdown */}
        <select
          name="employeeId"
          value={allocation.employeeId}
          onChange={handleChange}
          required
        >

          <option value="">Select Employee</option>

          {employees.map(emp => (

            <option key={emp.id} value={emp.id}>
              {emp.name}
            </option>

          ))}

        </select>

        <br /><br />

        {/* Project dropdown */}
        <select
          name="projectId"
          value={allocation.projectId}
          onChange={handleChange}
          required
        >

          <option value="">Select Project</option>

          {projects.map(project => (

            <option key={project.id} value={project.id}>
              {project.name}
            </option>

          ))}

        </select>

        <br /><br />

        {/* Start date */}
        <input
          type="date"
          name="startDate"
          value={allocation.startDate}
          onChange={handleChange}
          required
        />

        <br /><br />

        {/* End date */}
        <input
          type="date"
          name="endDate"
          value={allocation.endDate}
          onChange={handleChange}
          required
        />

        <br /><br />

        {/* Percentage */}
        <input
          type="number"
          name="percentage"
          placeholder="Allocation percentage"
          value={allocation.percentage}
          onChange={handleChange}
          required
        />

        <br /><br />

        <button type="submit">

          Create Allocation

        </button>

      </form>

    </div>

  );

}

export default AllocationForm;