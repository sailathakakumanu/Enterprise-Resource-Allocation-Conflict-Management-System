import React from "react";
import EmployeeForm from "./components/EmployeeForm";
import ProjectForm from "./components/ProjectForm";
import AllocationForm from "./components/AllocationForm";

function App() {

  return (

    <div>

      <h1>Resource Allocation System</h1>

      <EmployeeForm />

      <hr />

      <ProjectForm />

      <hr />

      <AllocationForm />

    </div>

  );

}

export default App;