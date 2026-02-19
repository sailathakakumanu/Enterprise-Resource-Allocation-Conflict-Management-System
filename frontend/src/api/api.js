import axios from "axios";

// Base backend URL
const BASE_URL = "http://localhost:8080/api";

// ================= EMPLOYEE APIs =================

// Create employee API call
export const createEmployee = (employee) => {

  return axios.post(`${BASE_URL}/employees`, employee);

};

// Get all employees
export const getEmployees = () => {

  return axios.get(`${BASE_URL}/employees`);

};

// ================= PROJECT APIs =================

// Create project API call
export const createProject = (project) => {

  return axios.post(`${BASE_URL}/projects`, project);

};

// Get all projects
export const getProjects = () => {

  return axios.get(`${BASE_URL}/projects`);

};

// ================= ALLOCATION APIs =================

// Create allocation API call
export const createAllocation = (allocation) => {

  return axios.post(`${BASE_URL}/allocations`, allocation);

};

// Get all allocations
export const getAllocations = () => {

  return axios.get(`${BASE_URL}/allocations`);

};