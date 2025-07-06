import React, { useContext, useState, useEffect } from "react";
import { AuthContext } from "../context/AuthContext";
import { getAllMovies, createMovie, deleteMovie } from "../api/movies";

export default function AdminPanel() {
  const { token } = useContext(AuthContext);
  const [movies, setMovies] = useState([]);
  const [form, setForm] = useState({ title: "", description: "", genre: "", releaseDate: "" });

  useEffect(() => {
    if (token)
      getAllMovies(token).then(res => setMovies(res.data));
  }, [token]);

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleCreate = async e => {
    e.preventDefault();
    await createMovie(form, token);
    getAllMovies(token).then(res => setMovies(res.data));
    setForm({ title: "", description: "", genre: "", releaseDate: "" });
  };

  const handleDelete = async id => {
    await deleteMovie(id, token);
    setMovies(movies.filter(m => m.id !== id));
  };

  return (
    <div className="container">
      <h2>Admin Panel</h2>
      <form onSubmit={handleCreate}>
        <input name="title" placeholder="Title" value={form.title} onChange={handleChange} required />
        <input name="description" placeholder="Description" value={form.description} onChange={handleChange} />
        <input name="genre" placeholder="Genre" value={form.genre} onChange={handleChange} />
        <input name="releaseDate" placeholder="Release Date YYYY-MM-DD" value={form.releaseDate} onChange={handleChange} />
        <button type="submit">Create Movie</button>
      </form>
      <ul style={{marginTop: "18px"}}>
        {movies.map(m => (
          <li key={m.id}>
            {m.title} <button style={{marginLeft: 8}} onClick={() => handleDelete(m.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}