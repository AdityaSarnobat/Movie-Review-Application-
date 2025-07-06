import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

export default function Navbar() {
  const { token, role, logout } = useContext(AuthContext);
  return (
    <nav>
      <Link to="/">Home</Link>
      {token ? (
        <>
          <button onClick={logout}>Logout</button>
          {role === "ADMIN" && (
            <Link to="/admin">Admin</Link>
          )}
        </>
      ) : (
        <>
          <Link to="/login">Login</Link>
          <Link to="/register">Register</Link>
        </>
      )}
    </nav>
  );
}