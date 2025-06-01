# Persons Finder API

## Overview

Persons Finder is a Spring Boot application using Gradle that provides a RESTful API for storing and retrieving geographical locations by a unique reference ID. The API is defined via an OpenAPI 3.0 specification, and basic coordinate validation is enforced.

## What Has Been Done

- **OpenAPI Definition**: Created `openapi.yaml` describing the `Location` schema and endpoints:
  - POST `/locations` to save a new location
  - GET `/locations` to list all locations
  - GET `/locations/{reference}` to retrieve a location by its reference ID
- **API Implementation**: Developed Spring Boot controllers, service layer, and domain model for `Location`.
- **Validation**: Added constraints for:
  - Latitude (`-90.0` to `90.0`)
  - Longitude (`-180.0` to `180.0`)
- **Testing**:
  - Unit tests for domain model and use-case implementations
  - Integration tests for web controllers
- **Build & Run**: Configured with Gradle wrapper, Dockerfile, and Docker Compose for local development.

## What Is Missing

- **Persistence**: Currently using an in-memory store. Needs migration to a production-grade database (e.g., PostgreSQL, MySQL).
- **Global Error Handling**: Implement a centralized exception handler and standardized error response format.
- **Extended Validation**: More field-level checks, request body error messaging, and null-safety controls.
- **API Documentation UI**: Integrate Swagger UI or Redoc to serve interactive API docs.
- **Security**: Add authentication/authorization (e.g., JWT, OAuth2) and input sanitization.
- **CI/CD Pipeline**: Set up automated builds, tests, and Docker image publishing (e.g., GitHub Actions, Jenkins).
- **Observability**: Introduce structured logging, metrics, health checks, and distributed tracing via Spring Actuator and Micrometer.

## Identified Improvements

1. **Database Integration**: Switch to JPA/Hibernate with a relational database, externalizing data configuration.
2. **DTO & Mapping**: Use DTOs and MapStruct to decouple API contracts from internal entities.
3. **Pagination & Filtering**: Add pagination parameters and filtering capabilities to GET endpoints.
4. **Enhanced Documentation**: Auto-generate client SDKs, publish versioned API docs, and host them.
5. **Resilience Patterns**: Implement retries, circuit breakers (Spring Cloud), and fallback mechanisms.
6. **Containerization Enhancements**: Optimize Dockerfile, multi-stage builds, and expand `docker-compose.yml` for database/service orchestration.
7. **Security Hardening**: Apply RBAC, rate limiting, and write security-focused tests.
8. **Caching Layer**: Introduce Redis or similar for frequently accessed data to improve performance.

## Getting Started

1. **Clone repository**
   ```bash
   git clone https://github.com/your-org/persons-finder.git
   cd persons-finder
   ```
2. **Build & Test**
   ```bash
   ./gradlew clean build
   ```
3. **Run Application**
   ```bash
   ./gradlew bootRun
   ```
4. **API Access**
   Open http://localhost:8080 and use the OpenAPI spec at `/v3/api-docs` (once Swagger UI is enabled).

---

_This README outlines current work, outstanding features, and roadmap items for Persons Finder._
