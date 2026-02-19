import React, { useEffect, useState } from "react";
import { getAllocations } from "../api/api";

// Dashboard component
function Dashboard() {

  // State for all allocations
  const [allocations, setAllocations] = useState([]);

  // State for active allocations
  const [activeAllocations, setActiveAllocations] = useState([]);

  // State for upcoming allocations
  const [upcomingAllocations, setUpcomingAllocations] = useState([]);

  // State for high allocation employees
  const [highAllocations, setHighAllocations] = useState([]);

  // Load data when component loads
  useEffect(() => {

    fetchAllocations();

  }, []);

  // Fetch allocations and filter data
  const fetchAllocations = async () => {

    try {

      const response = await getAllocations();

      const allAllocations = response.data.data;

      setAllocations(allAllocations);

      filterAllocations(allAllocations);

    } catch (error) {

      alert("Failed to load dashboard");

    }

  };

  // Filter allocations into categories
  const filterAllocations = (allocations) => {

    const today = new Date();

    const active = [];

    const upcoming = [];

    const high = [];

    allocations.forEach(allocation => {

      const start = new Date(allocation.startDate);

      const end = new Date(allocation.endDate);

      // Active allocations
      if (start <= today && end >= today) {

        active.push(allocation);

      }

      // Upcoming allocations
      if (start > today) {

        upcoming.push(allocation);

      }

      // High allocation (>80%)
      if (allocation.allocationPercentage > 80) {

        high.push(allocation);

      }

    });

    setActiveAllocations(active);

    setUpcomingAllocations(upcoming);

    setHighAllocations(high);

  };

  return (

    <div>

      <h2>Dashboard</h2>

      <h3>Active Allocations: {activeAllocations.length}</h3>

      <h3>Upcoming Allocations: {upcomingAllocations.length}</h3>

      <h3>High Allocations ({'>'}80%): {highAllocations.length}</h3>

    </div>

  );

}

export default Dashboard;