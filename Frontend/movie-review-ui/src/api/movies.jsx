import axios from "axios";
const BASE_URL = "http://localhost:8080/api/movies";

export const getAllMovies = async (token) =>
  axios.get(BASE_URL, {
    headers: { Authorization: `Bearer ${token}` }
  });

export const getMovieById = async (id, token) =>
  axios.get(`${BASE_URL}/${id}`, {
    headers: { Authorization: `Bearer ${token}` }
  });

export const createMovie = async (movie, token) =>
  axios.post(BASE_URL, movie, {
    headers: { Authorization: `Bearer ${token}` }
  });

export const updateMovie = async (id, movie, token) =>
  axios.put(`${BASE_URL}/${id}`, movie, {
    headers: { Authorization: `Bearer ${token}` }
  });

export const deleteMovie = async (id, token) =>
  axios.delete(`${BASE_URL}/${id}`, {
    headers: { Authorization: `Bearer ${token}` }
  });