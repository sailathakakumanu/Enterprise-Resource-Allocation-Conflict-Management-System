import React, { useState } from "react";
import { createProject } from "../api/api";

// Component to create new project
function ProjectForm() {

  // State to store project form data
  const [project, setProject] = useState({
    name: "",
    client: "",
    startDate: "",
    endDate: ""
  });

  // Handle input changes
  const handleChange = (event) => {

    setProject({
      ...project,
      [event.target.name]: event.target.value
    });

  };

  // Handle form submit
  const handleSubmit = async (event) => {

    event.preventDefault();

    try {

      const response = await createProject(project);

      alert(response.data.message);

      // Reset form
      setProject({
        name: "",
        client: "",
        startDate: "",
        endDate: ""
      });

    } catch (error) {

      alert(error.response.data.message);

    }

  };

  return (

    <div>

      <h2>Create Project</h2>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="name"
          placeholder="Project name"
          value={project.name}
          onChange={handleChange}
          required
        />

        <br /><br />

        <input
          type="text"
          name="client"
          placeholder="Client name"
          value={project.client}
          onChange={handleChange}
          required
        />

        <br /><br />

        <input
          type="date"
          name="startDate"
          value={project.startDate}
          onChange={handleChange}
          required
        />

        <br /><br />

        <input
          type="date"
          name="endDate"
          value={project.endDate}
          onChange={handleChange}
          required
        />

        <br /><br />

        <button type="submit">

          Create Project

        </button>

      </form>

    </div>

  );

}

export default ProjectForm;