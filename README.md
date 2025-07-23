#  Job Portal Backend

A fully-featured Spring Boot backend for a Job Portal system, supporting:
- User & Recruiter authentication with JWT + Refresh Token
- Role-based access control (RECRUITER, USER)
- Post & search jobs by title/location
- Oracle DB integration

## Features

-  Register/Login with JWT token
-  Refresh token logic
-  Recruiter: Post Jobs
-  User: Search Jobs
-  Search by Title and Location
-  Exception handling and validation
-  Secure endpoints with Spring Security

##  Tech Stack

- Java + Spring Boot
- Spring Security + JWT + Refresh Token
- Oracle DB
- Maven

##  Folder Structure

- `controller/` – REST endpoints
- `service/` – Business logic
- `repo/` – JPA repositories
- `config/` – JWT, filter, CORS
- `dto/` – Request/response models

##  API Endpoints

| Method | Endpoint              | Description                  |
|--------|-----------------------|------------------------------|
| POST   | `/api/auth/register` | Register a user              |
| POST   | `/api/auth/login`    | Login and get JWT token      |
| POST   | `/api/job/`          | Recruiter posts job          |
| GET    | `/api/job/search`    | Search jobs by title/location |
| POST   | `/api/token/refresh` | Refresh the access token     |

##  Setup Instructions

1. Clone the repo
2. Setup Oracle DB and update `application.properties`
3. Run using IDE or `mvn spring-boot:run`

---

##  Contact

Developed by [Akash Patil](https://github.com/akashivu)  
