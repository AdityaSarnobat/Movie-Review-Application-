import React, { useState } from "react";
import { register as apiRegister } from "../api/auth";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMsg("");
    try {
      await apiRegister(username, password);
      setMsg("Registration successful. You can now login.");
      setTimeout(() => navigate("/login"), 1000);
    } catch (err) {
      setMsg("Username already exists");
    }
  };

  return (
    <div className="container">
      <form onSubmit={handleSubmit}>
        <h2>Register</h2>
        <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Username" required />
        <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" required />
        <button type="submit">Register</button>
        {msg && <div>{msg}</div>}
      </form>
    </div>
  );
}