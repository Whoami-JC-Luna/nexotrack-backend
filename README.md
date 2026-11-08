# NexoTrack Backend

API REST de **NexoLogistics** para gestionar envíos y su seguimiento: alta de envíos, consulta por clientes y registro de eventos de tracking a lo largo de la cadena logística.

| | |
|---|---|
| Estado | Sprint 01 — Project Foundation (en preparación) |
| Stack | Java 21 · Spring Boot 3.5 · Maven |
| Equipo | Backend Team de NexoLogistics ([docs/team.md](docs/team.md)) |

## Requisitos

- JDK 21
- Docker (para PostgreSQL local y Testcontainers, a partir de NXL-003)

No hace falta tener Maven instalado: el proyecto incluye Maven Wrapper (`./mvnw`).

## Arranque rápido

```bash
git clone https://github.com/Whoami-JC-Luna/nexotrack-backend.git
cd nexotrack-backend

./mvnw verify            # compila y ejecuta todos los tests
./mvnw spring-boot:run   # arranca la API en http://localhost:8080
```

Comprobación de salud:

```bash
curl http://localhost:8080/actuator/health
# {"status":"UP"}
```

## Stack tecnológico

| Capa | Tecnología | Entra en |
|---|---|---|
| Lenguaje y build | Java 21, Maven Wrapper | NXL-001 |
| Web y validación | Spring Web, Bean Validation | NXL-001 |
| Observabilidad | Spring Boot Actuator (solo `health`) | NXL-001 |
| Configuración | Perfiles `dev` / `test` / `prod`, variables de entorno | NXL-002 |
| Persistencia | Spring Data JPA, Hibernate, H2 (dev/test), PostgreSQL (prod) | NXL-002 / NXL-004 |
| Migraciones | Flyway | NXL-004 |
| Seguridad | Spring Security | NXL-008 |
| Testing | JUnit 5, Mockito, MockMvc, Testcontainers | Progresivo |
| CI | GitHub Actions | NXL-006 |

Las dependencias se añaden en el ticket que las necesita, no antes. Así cada cambio se puede revisar y justificar por separado.

## Estructura del proyecto

```
src/main/java/com/nexologistics/nexotrack
└── NexoTrackApplication.java      # punto de entrada
```

La organización de paquetes prevista (por funcionalidad, con capas dentro) está en [docs/architecture.md](docs/architecture.md).

## Documentación

| Documento | Contenido |
|---|---|
| [docs/business-context.md](docs/business-context.md) | Contexto de negocio y requisitos de alto nivel |
| [docs/architecture.md](docs/architecture.md) | Arquitectura prevista y decisiones técnicas |
| [docs/team.md](docs/team.md) | Equipo y responsabilidades |
| [docs/git-workflow.md](docs/git-workflow.md) | Ramas, commits y Pull Requests |
| [docs/definition-of-done.md](docs/definition-of-done.md) | Cuándo un ticket está terminado |
| [docs/backlog.md](docs/backlog.md) | Backlog y Sprint 01 |
| [docs/adr/](docs/adr/) | Registro de decisiones de arquitectura (ADR) |

## Cómo contribuir

1. Coge un ticket asignado (`NXL-XXX`).
2. Crea una rama desde `main` siguiendo [docs/git-workflow.md](docs/git-workflow.md).
3. Implementa con tests y ejecuta `./mvnw verify` en local.
4. Abre una Pull Request usando la plantilla.
5. Ninguna PR se mergea sin revisión aprobada.

## Seguridad

- Ningún secreto (contraseñas, tokens, claves) se sube al repositorio. La configuración sensible llega por variables de entorno.
- Si detectas una vulnerabilidad, no abras un Issue público: avisa directamente al Tech Lead.
