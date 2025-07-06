import React, { useEffect, useState, useContext } from "react";
import { getMovieById } from "../api/movies";
import { getReviewsByMovie, createReview } from "../api/reviews";
import { AuthContext } from "../context/AuthContext";
import { useParams } from "react-router-dom";

export default function MovieDetail() {
  const { token, username } = useContext(AuthContext);
  const { id } = useParams();
  const [movie, setMovie] = useState(null);
  const [reviews, setReviews] = useState([]);
  const [reviewText, setReviewText] = useState("");
  const [rating, setRating] = useState(5);

  useEffect(() => {
    if (token) {
      getMovieById(id, token).then(res => setMovie(res.data));
      getReviewsByMovie(id, token).then(res => setReviews(res.data));
    }
  }, [id, token]);

  const submitReview = async (e) => {
    e.preventDefault();
    if (!reviewText.trim()) return;
    await createReview({ movieId: id, content: reviewText, rating }, token);
    setReviewText("");
    setRating(5);
    getReviewsByMovie(id, token).then(res => setReviews(res.data));
  };

  if (!movie) return <div className="container">Loading...</div>;

  return (
    <div className="container">
      <h2>{movie.title}</h2>
      <div><b>Description:</b> {movie.description || "N/A"}</div>
      <div><b>Genre:</b> {movie.genre}</div>
      <div><b>Release:</b> {movie.releaseDate}</div>
      <div><b>Avg Rating:</b> {movie.averageRating} | <b>Reviews:</b> {movie.reviewCount}</div>
      <hr />
      <h3>Reviews</h3>
      {reviews.length === 0 && <div>No reviews yet.</div>}
      {reviews.map(r => (
        <div className="review" key={r.id}>
          <b>{r.username}</b>: {r.content} (Rating: {r.rating})
        </div>
      ))}
      <hr />
      <form onSubmit={submitReview}>
        <label htmlFor="reviewText"><b>Write a review:</b></label>
        <textarea
          id="reviewText"
          value={reviewText}
          onChange={e => setReviewText(e.target.value)}
          required
          minLength={3}
          rows={3}
        />
        <label htmlFor="rating">Rating:</label>
        <select id="rating" value={rating} onChange={e => setRating(Number(e.target.value))}>
          {[1,2,3,4,5].map(n => <option key={n} value={n}>{n}</option>)}
        </select>
        <button type="submit">Post Review</button>
      </form>
    </div>
  );
}