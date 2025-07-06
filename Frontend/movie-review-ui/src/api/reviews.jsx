import axios from "axios";
const BASE_URL = "http://localhost:8080/api/reviews";

export const getReviewsByMovie = async (movieId, token) =>
  axios.get(`${BASE_URL}/movie/${movieId}`, {
    headers: { Authorization: `Bearer ${token}` }
  });

export const createReview = async (review, token) =>
  axios.post(BASE_URL, review, {
    headers: { Authorization: `Bearer ${token}` }
  });