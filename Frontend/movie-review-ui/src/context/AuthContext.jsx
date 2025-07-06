import React, { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [token, setToken] = useState(localStorage.getItem("jwt_token"));
  const [role, setRole] = useState(localStorage.getItem("role"));
  const [username, setUsername] = useState(localStorage.getItem("username"));

  useEffect(() => {
    if (token) localStorage.setItem("jwt_token", token);
    else localStorage.removeItem("jwt_token");
    if (role) localStorage.setItem("role", role);
    else localStorage.removeItem("role");
    if (username) localStorage.setItem("username", username);
    else localStorage.removeItem("username");
  }, [token, role, username]);

  const login = (token, role, username) => {
    setToken(token);
    setRole(role);
    setUsername(username);
  };
  const logout = () => {
    setToken(null);
    setRole(null);
    setUsername(null);
    localStorage.clear();
  };

  return (
    <AuthContext.Provider value={{ token, role, username, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}