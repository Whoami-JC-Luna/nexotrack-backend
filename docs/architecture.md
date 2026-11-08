# Arquitectura prevista

> Documento vivo. Las decisiones importantes se registran como ADR en [docs/adr/](adr/).

## Visión general

Monolito modular con Spring Boot. Para el tamaño del equipo y del dominio, un único servicio bien organizado es más fácil de desarrollar, probar y desplegar que varios microservicios.

```
Cliente HTTP
    │
    ▼
┌───────────────────────────────────────────┐
│ NexoTrack API (Spring Boot)               │
│                                           │
│  Controller  →  Service  →  Repository    │
│   (DTOs)        (reglas)     (JPA)        │
│                                           │
│  Spring Security · Gestión global errores │
└───────────────────────────────────────────┘
    │
    ▼
PostgreSQL (prod) · H2 (dev/test)
Esquema versionado con Flyway
```

## Organización de paquetes

Por funcionalidad, con capas dentro de cada una:

```
com.nexologistics.nexotrack
├── shipment/                 # envíos (primer módulo de negocio)
│   ├── api/                  # controllers y DTOs de entrada/salida
│   ├── application/          # servicios: casos de uso y reglas
│   ├── domain/               # entidades y reglas del dominio
│   └── infrastructure/       # repositorios y adaptadores
├── tracking/                 # eventos de seguimiento
├── user/                     # usuarios y roles
└── shared/                   # transversal: errores, configuración, seguridad
```

**Por qué por funcionalidad:** todo lo relacionado con envíos está junto. Cuando el proyecto crezca no tendremos un paquete `controller/` con cuarenta clases sin relación entre sí.

Los paquetes se crean cuando hay código que meter en ellos, no antes.

## Reglas de diseño

1. **Los controllers no contienen lógica de negocio.** Validan la entrada, llaman al servicio y traducen la respuesta.
2. **Las entidades JPA nunca salen por la API.** Se usan DTOs de entrada y salida (Java `record`). Esto evita exponer campos internos y el *mass assignment*.
3. **Validación en la frontera** con Bean Validation (`@Valid`, `@NotBlank`…).
4. **Errores uniformes** con `ProblemDetail` (RFC 9457) y sin filtrar detalles internos.
5. **El esquema de base de datos lo gestiona Flyway.** Hibernate solo valida (`ddl-auto=validate`).
6. **Configuración sensible por variables de entorno**, nunca en ficheros del repositorio.
7. **Seguridad cerrada por defecto:** todo endpoint requiere autenticación salvo los declarados explícitamente como públicos.

## Entornos y perfiles

| Perfil | Base de datos | Uso |
|---|---|---|
| `dev` | H2 en memoria | Desarrollo local rápido |
| `test` | H2 / Testcontainers | Tests automatizados |
| `prod` | PostgreSQL | Producción, configurado solo por variables de entorno |

## Estrategia de testing

| Tipo | Herramienta | Qué protege |
|---|---|---|
| Unitarios | JUnit 5 + Mockito | Reglas de negocio de los servicios, sin Spring |
| Controladores | MockMvc (`@WebMvcTest`) | Contrato HTTP: códigos, validación, formato de errores |
| Repositorios | `@DataJpaTest` | Consultas y mapeos JPA |
| Seguridad | MockMvc + `spring-security-test` | Quién puede acceder a qué (401, 403, IDOR) |
| Integración | Testcontainers + PostgreSQL | Que todo funciona contra la base de datos real |

La cobertura es una pista, no un objetivo: un test vale si falla cuando el comportamiento que protege se rompe.
