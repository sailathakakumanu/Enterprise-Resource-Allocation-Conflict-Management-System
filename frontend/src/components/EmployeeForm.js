import React, { useState } from "react";
import { createEmployee } from "../api/api";

// Component to create new employee
function EmployeeForm() {

  // State to store employee form data
  const [employee, setEmployee] = useState({
    name: "",
    email: "",
    department: ""
  });

  // Handle input field changes
  const handleChange = (event) => {

    setEmployee({
      ...employee,
      [event.target.name]: event.target.value
    });

  };

  // Handle form submit
  const handleSubmit = async (event) => {

    event.preventDefault(); // prevent page reload

    try {

      const response = await createEmployee(employee);

      alert(response.data.message); // show success message

      // Reset form
      setEmployee({
        name: "",
        email: "",
        department: ""
      });

    } catch (error) {

      alert(error.response.data.message);

    }

  };

  return (

    <div>

      <h2>Create Employee</h2>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="name"
          placeholder="Enter name"
          value={employee.name}
          onChange={handleChange}
          required
        />

        <br /><br />

        <input
          type="email"
          name="email"
          placeholder="Enter email"
          value={employee.email}
          onChange={handleChange}
          required
        />

        <br /><br />

        <input
          type="text"
          name="department"
          placeholder="Enter department"
          value={employee.department}
          onChange={handleChange}
          required
        />

        <br /><br />

        <button type="submit">

          Create Employee

        </button>

      </form>

    </div>

  );

}

export default EmployeeForm;