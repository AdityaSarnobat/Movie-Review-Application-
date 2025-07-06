import React, { useEffect, useState, useContext } from "react";
import { getAllMovies } from "../api/movies";
import { AuthContext } from "../context/AuthContext";
import { Link } from "react-router-dom";

export default function MovieList() {
  const { token } = useContext(AuthContext);
  const [movies, setMovies] = useState([]);

  useEffect(() => {
    if (token)
      getAllMovies(token).then(res => setMovies(res.data));
  }, [token]);

  return (
    <div className="container">
      <h2>Movies</h2>
      <div className="movie-list">
        {movies.length === 0 && <div>No movies found.</div>}
        {movies.map(movie => (
          <div className="movie-card" key={movie.id}>
            <Link className="movie-title-link" to={`/movies/${movie.id}`}>{movie.title}</Link>
            <div><b>Genre:</b> {movie.genre}</div>
            <div><b>Release:</b> {movie.releaseDate}</div>
          </div>
        ))}
      </div>
    </div>
  );
}