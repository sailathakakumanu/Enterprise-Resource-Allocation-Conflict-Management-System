import React from "react";
import EmployeeForm from "./components/EmployeeForm";
import ProjectForm from "./components/ProjectForm";
import AllocationForm from "./components/AllocationForm";
import AllocationList from "./components/AllocationList";
import Dashboard from "./pages/Dashboard";
function App() {

  return (

    <div>

      <h1>Resource Allocation System</h1>

      <EmployeeForm />

      <hr />

      <ProjectForm />

      <hr />

      <AllocationForm />
      <hr />

      <AllocationList />

<hr />

      <Dashboard />



    </div>

  );

}

export default App;