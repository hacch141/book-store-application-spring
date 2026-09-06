# Bookstore Application

A bookstore application built with Spring Boot and a microservices architecture.

## Run the catalog service locally

Start its PostgreSQL database:

```bash
docker compose -f deployment/docker-compose/infra.yml up -d catalog-db
```

Then start the service:

```bash
./mvnw -pl catalog-service spring-boot:run
```

The catalog API is available at `http://localhost:8081/api/products`.
