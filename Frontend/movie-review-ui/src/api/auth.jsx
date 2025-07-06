import axios from "axios";
const BASE_URL = "http://localhost:8080/api/auth";

export const login = async (username, password) => {
  const res = await axios.post(`${BASE_URL}/login`, { username, password });
  return res.data;
};

export const register = async (username, password) => {
  const res = await axios.post(`${BASE_URL}/register`, { username, password });
  return res.data;
};