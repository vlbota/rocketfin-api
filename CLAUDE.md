# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Dev mode with live reload (uses application-dev.properties)
./mvnw quarkus:dev

# Build
./mvnw package

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=MyTestClass

# Start infrastructure (RabbitMQ)
docker-compose up -d
```

Dev mode activates the `dev` profile automatically, which applies `application-dev.properties` on top of `application.properties`. The Quarkus Dev UI is available at `http://localhost:8080/q/dev/`.

## Environment

The app reads database and integration credentials from environment variables. Create a `.env` file (or set them in your shell) before running:

```
USERNAME_DB_ROCKETFIN=
PASSWORD_DB_ROCKETFIN=
ADDRESS_DB_ROCKETFIN=
FRONT_PATH=
BACK_PATH=
TELEGRAM_BOT=
TELEGRAM_CHAT_MSG_FAILS=
```

## JWT Keys

Generate RSA keys and place them in `src/main/resources/` before the JWT endpoints will work:

```bash
openssl genrsa -out private.pem 2048
openssl pkcs8 -topk8 -nocrypt -in private.pem -out privateKey.pem
openssl rsa -in private.pem -pubout -out publicKey.pem
```

## Architecture

### Module layout

Each domain is a top-level package under `vlb.developer` with a fixed internal structure:

```
vlb.developer.{module}/
  controller/   — JAX-RS resources (@Path)
  dtos/         — output objects (factory method: Dto.from(entity))
  forms/        — input objects from the frontend (public fields + @NotBlank/@Email)
  repositories/ — plain JPA repositories injecting EntityManager
  service/      — @ApplicationScoped beans with @Transactional methods
```

Current modules: `profile`, `login`, `bills`.

Shared JPA entities live in `vlb.developer.entities` (not inside any module). Environment config beans live in `vlb.developer.utils.environments`.

### Data access

Uses plain JPA with `EntityManager` injection — **no Panache**. Repositories are `@ApplicationScoped` beans. Write operations must be called from a `@Transactional` context (service layer, not controller).

Hibernate schema generation:
- **prod**: `validate` (schema must exist)
- **dev**: `update` (schema auto-migrated)

### Auth flow

- `POST /auth/login` — verifies BCrypt password, returns a signed JWT (1 h) + UUID refresh token stored in `client_refresh_tokens` table (7-day expiry).
- `POST /auth/refresh` — validates + rotates the refresh token, returns a new JWT pair.
- JWT claims: `upn` (username), `id`, `name`. Signed with RSA private key via SmallRye JWT (`Jwt.issuer("rocketfin").sign()`).
- Passwords are hashed with `BcryptUtil.bcryptHash` / `BcryptUtil.matches` from `quarkus-elytron-security-common`.

### Entity conventions

Entities use either manual getters/setters or Lombok `@Getter`/`@Setter`. UUID primary keys use `@UuidGenerator(style = UuidGenerator.Style.TIME)`. Creation timestamps use `@CreationTimestamp`.

### Infrastructure

- **PostgreSQL** — external, connected via JDBC.
- **RabbitMQ** — `docker-compose up -d` starts it on ports 5672 / 15672 (management UI).
- **Mailer** — Quarkus Mailer over Gmail SMTP; `quarkus.mailer.mock=true` in dev profile disables actual sends.
- **Telegram** — Apache Camel Telegram for failure notifications.

### Form classes

- receive data from external front-end
- forms should be declare like a record class java
- Classes who has been created should have a nomenclature final name with FORM

### DTO classes

- Data transform entities or others to send data to front end or external api
- DTOs should have implements lombok @Getter and constructor with target transform class
- Classes who has been created should have a nomenclature final name with DTO


