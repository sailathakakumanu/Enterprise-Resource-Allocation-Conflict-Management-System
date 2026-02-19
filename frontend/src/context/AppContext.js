import React, { createContext, useContext, useState, useEffect } from "react";
import { getEmployees, getProjects, getAllocations } from "../api/api";

// Create context
const AppContext = createContext();

// Provider component
export function AppProvider({ children }) {

  const [employees, setEmployees] = useState([]);
  const [projects, setProjects] = useState([]);
  const [allocations, setAllocations] = useState([]);

  const [loading, setLoading] = useState(true);

  // Load all data
  const loadData = async () => {

    try {

      const empRes = await getEmployees();
      const projRes = await getProjects();
      const allocRes = await getAllocations();

      setEmployees(empRes.data.data);
      setProjects(projRes.data.data);
      setAllocations(allocRes.data.data);

      setLoading(false);

    } catch (error) {

      console.error("Failed to load data");

    }

  };

  // Load on startup
  useEffect(() => {

    loadData();

  }, []);

  // Refresh function
  const refreshData = () => {

    loadData();

  };

  return (

    <AppContext.Provider value={{

      employees,
      projects,
      allocations,
      refreshData,
      loading

    }}>

      {children}

    </AppContext.Provider>

  );

}

// Custom hook
export function useAppContext() {

  return useContext(AppContext);

}