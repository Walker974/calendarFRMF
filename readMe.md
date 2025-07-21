# Calendar Management Application

A Spring Boot-based calendar management system for organizing events, competitions, teams, stadiums, and more. Designed for sports or event organizers to efficiently manage schedules and resources.

## Features
- User authentication and authorization
- CRUD operations for cities, competitions, event types, organizers, stadiums, teams
- Event scheduling and management
- Role-based access control
- RESTful API endpoints
- Docker support for easy deployment

## Technologies Used
- Java 17+
- Spring Boot
- Spring Security
- Maven
- Docker

## Usage
- Register and log in to access features
- Use the web interface or REST API to manage events, teams, stadiums, etc.

## API Endpoints
Some example endpoints:
- `POST /api/auth/login` — User login
- `GET /api/cities` — List all cities
- `POST /api/events` — Create a new event
- `GET /api/teams` — List all teams

## Docker Deployment
To run with Docker:
```bash
docker build -t calendar-app .
docker run -p 8080:8080 calendar-app
```
Or use Docker Compose:
```bash
docker-compose up --build
```