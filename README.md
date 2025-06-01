# Persons Finder API

## Overview

Persons Finder is a Spring Boot application using Gradle that provides a RESTful API for storing and retrieving geographical locations by a unique reference ID. The API is defined via an OpenAPI 3.0 specification, and basic coordinate validation is enforced.

## What Has Been Done

- **OpenAPI Definition**: In `spec.yaml`. The primary API endpoints for managing persons and their locations are defined under `/api/v1/persons`.
- **API Implementation**:
  - Developed Spring Boot controllers, use cases (application services), and domain models for `Person` and `Location`.
  - The `PersonController` (`src/main/java/com/example/personsfinder/adapter/web/controller/persons/PersonController.java`) implements the following endpoints:
    - `POST /api/v1/persons`: Create a new person.
    - `GET /api/v1/persons`: Get persons by a list of IDs.
    - `PUT /api/v1/persons/{id}/location`: Update a specific person's location.
    - `GET /api/v1/persons/nearby`: Find persons near a given latitude and longitude within a specified radius.
- **Validation**: Added input validation for request bodies and path variables (e.g., latitude/longitude ranges, non-blank names).
- **Testing**:
  - Unit tests for domain models and use case implementations.
  - Integration tests for web controllers.
- **Build & Run**: Configured with Gradle wrapper, Dockerfile, and Docker Compose for local development and containerization.
- **Clean Architecture**: The project structure has been refactored to follow Clean Architecture principles, separating concerns into domain, application, and adapter layers as documented in `ARCHITECTURE.md`.

## What Is Missing

- **Security**: Add authentication/authorization (e.g., JWT, OAuth2) and input sanitization.
- **Observability**: Introduce structured logging, metrics, health checks, and distributed tracing via Spring Actuator and Micrometer.

## Identified Improvements

1. **Pagination & Filtering**: Add pagination parameters and filtering capabilities to GET endpoints.
2. **Enhanced Documentation**: Auto-generate client SDKs, publish versioned API docs, and host them.
3. **Containerization Enhancements**: Optimize Dockerfile, multi-stage builds, and expand `docker-compose.yml` for database/service orchestration.

## Identified Bottleneck

A key bottleneck for the application in its current state is its **reliance on an in-memory data store**. While suitable for initial development and testing, this approach has significant limitations for a production-like environment:

- **Data Volatility**: All data is lost when the application restarts.
- **Scalability**: In-memory storage does not scale well with increasing data volumes or concurrent users.
- **Limited Querying**: Complex data queries and relationships are harder to manage effectively compared to dedicated database systems.

Migrating to a persistent database solution (e.g., PostgreSQL, MySQL) using Spring Data JPA or a similar persistence framework is a critical next step. This is also highlighted in the "Identified Improvements" and "What Is Missing" sections.

## Getting Started

1.  **Clone repository**
    ```bash
    git clone https://github.com/pastyeyes/persons-finder.git
    cd persons-finder
    ```
2.  **Build & Test**
    The application uses Gradle. Ensure you have a compatible JDK installed (e.g., **JDK 17** or newer).
    ```bash
    ./gradlew clean build
    ```
3.  **Run Application (Option 1: Using Gradle)**
    This command starts the Spring Boot application directly.

    ```bash
    ./gradlew bootRun
    ```

    The application will be accessible at http://localhost:8080/hello.

4.  **Run Application (Option 2: Using Docker Compose)**
    This method builds the Docker image (if not already built or if changes are detected) and starts the container defined in `docker-compose.yml`.

    ```bash
    docker-compose up --build
    ```

    The application will be accessible at http://localhost:8080/hello.

5.  **API Access**
    Once the application is running, you can access:
    - The API endpoints as defined in `spec.yaml` (e.g., `/api/v1/persons`).
    - Example using curl for creating a person (ensure the server is running):
      ```bash
      curl -X POST http://localhost:8080/api/v1/persons -H "Content-Type: application/json" -d '{"name": "NEW_NAME"}'
      ```
    - Example using curl for reading
      ```bash
      curl "http://localhost:8080/api/v1/persons?id=1&id=2"
      ```

---

_This README outlines current work, outstanding features, and roadmap items for Persons Finder._
