# Movie Review Application

A full-stack Movie Review application built with Spring Boot (backend) and React + Vite (frontend).

## Features

- User registration & login with JWT authentication
- Movie CRUD (admin only)
- Post, view, and delete reviews for movies
- Basic role-based access (USER/ADMIN)
- Responsive React frontend (Vite)
- RESTful API with OpenAPI/Swagger documentation



## Tech Stack

- **Backend:** Java 17+, Spring Boot, JPA/Hibernate, Spring Security, H2/MySQL
- **Frontend:** React, Vite, Axios
- **API Docs:** SpringDoc OpenAPI/Swagger UI


## Setup Instructions

### 1. Clone the Repository

```sh
git clone https://github.com/yourusername/movie-review-app.git
cd movie-review-app
```

### 2. Backend Setup (Spring Boot)

```sh
cd backend
./mvnw clean package      # Or use mvn clean package if Maven is installed
java -jar target/movie-review-backend-*.jar
```
- By default, runs on `http://localhost:8080`
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Backend Configuration
- To switch DB, edit `src/main/resources/application.properties`
- Default: H2 (in-memory)
- For MySQL, set JDBC URL, username, password.

### 3. Frontend Setup (React + Vite)

```sh
cd ../Frontend/movie-review-ui
npm install
npm run dev
```
- Open [http://localhost:5173](http://localhost:5173)

---

## Database Schema Diagram

```
+------------+      +--------------+      +----------+
|   users    |      |   movies     |      | reviews  |
+------------+      +--------------+      +----------+
| id (PK)    |----< | id (PK)      |  >---| id (PK)  |
| username   |      | title        |      | movie_id |
| password   |      | genre        |      | user_id  |
| role       |      | description  |      | content  |
+------------+      | release_date |      | rating   |
                    +--------------+      +----------+
```

**Legend:**  
- A user can post many reviews  
- A movie can have many reviews  
- Admin users can CRUD movies

---

## API Documentation

The backend exposes a full set of REST APIs, documented via OpenAPI/Swagger.

### Access Swagger UI

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Sample Endpoints

#### Auth

| Method | Endpoint             | Description         |
|--------|----------------------|---------------------|
| POST   | `/api/auth/register` | Register new user   |
| POST   | `/api/auth/login`    | Login, returns JWT  |

#### Movies

| Method | Endpoint          | Description             | Auth           |
|--------|-------------------|------------------------|----------------|
| GET    | `/api/movies`     | List all movies        | USER/ADMIN     |
| POST   | `/api/movies`     | Create new movie       | ADMIN only     |
| GET    | `/api/movies/{id}`| Get movie by ID        | USER/ADMIN     |
| PUT    | `/api/movies/{id}`| Update movie           | ADMIN only     |
| DELETE | `/api/movies/{id}`| Delete movie           | ADMIN only     |

#### Reviews

| Method | Endpoint                    | Description         | Auth    |
|--------|-----------------------------|---------------------|---------|
| GET    | `/api/reviews/movie/{id}`   | Get reviews for movie| USER/ADMIN |
| POST   | `/api/reviews`              | Post review         | USER/ADMIN |

> **See full details and request/response schemas in Swagger UI.**

---

## Packaging & Delivery

- **Spring Boot Executable Jar:**  
  The backend is packaged as a single `.jar` file (see `/backend/target/` after build).  
  Run with:  
  ```sh
  java -jar movie-review-backend-<version>.jar
  ```

- **Frontend:**  
  Vite SPA, runs with `npm run dev`, or build static site using `npm run build`.

- **API Docs:**  
  Included with backend at `/swagger-ui.html`.

---

## Optional Frontend/Postman Collection

- **Frontend:**  
  Basic React SPA provided in `/Frontend/movie-review-ui/`—see setup above.

- **Postman Collection:**  
  You may import the included [postman_collection.json](postman_collection.json) (if provided) to test API endpoints directly.
