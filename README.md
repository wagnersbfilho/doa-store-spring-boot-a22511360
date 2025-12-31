## DOA Store Management System - Spring Boot / Docker

## Project Information
- <b>The project is available (public) in my personal GitHub repository:</b>
- https://github.com/wagnersbfilho/doa-store-spring-boot-a22511360

## Student Information
- Wagner Filho
- a22511360

## Project Description
This Project is a Java application with Spring Boot, JPA, and Database Persistence that manages the core operations of Jewelry Store.


## Database PostgreSQL with Docker
- Docker Compose is used for containerized database deployment.
- IntelliJ IDEA Environment Variables:
    - Run → Edit Configurations
        - DB_URL=jdbc:postgresql://localhost:5432/doa_store
        - DB_USERNAME=doa_user
        - DB_PASSWORD=doa_password

---

## Requirements
- Docker
- Docker Compose
- Java 21
- Maven

---

## Run database

```bash
docker-compose up -d