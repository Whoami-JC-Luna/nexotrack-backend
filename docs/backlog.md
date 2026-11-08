# Backlog

Los tickets se gestionan como **Issues de GitHub**, que son la referencia oficial. Este documento resume la planificación; el detalle de cada ticket (criterios de aceptación, seguridad y pruebas) está en su Issue.

## Sprint 01 — Project Foundation

**Fechas:** 9 – 20 de noviembre de 2026

**Objetivo:** dejar el repositorio, la configuración por entornos, la base de datos versionada y la seguridad base listos para empezar a construir funcionalidad.

| Ticket | Título | Responsable | Prioridad | Depende de |
|---|---|---|---|---|
| NXL-001 | Inicializar repositorio y esqueleto Maven | Santi | CRITICAL | — |
| NXL-002 | Configuración por perfiles y variables de entorno | Santi | HIGH | NXL-001 |
| NXL-003 | Docker Compose con PostgreSQL local | Laura | MEDIUM | NXL-002 |
| NXL-004 | Flyway: migración inicial V1 | Marcos | HIGH | NXL-002, NXL-007 |
| NXL-005 | Gestión global de errores con ProblemDetail | Juan C. Luna | HIGH | NXL-001 |
| NXL-006 | Pipeline CI con GitHub Actions | Laura | HIGH | NXL-001 |
| NXL-007 | ADR-001: modelo de dominio de envíos | Marcos | HIGH | — |
| NXL-008 | Spring Security base: denegar por defecto | Santi | CRITICAL | NXL-001 |

## Backlog de producto (sin planificar)

| Ticket | Título | Prioridad | Depende de |
|---|---|---|---|
| NXL-009 | API de alta y consulta de envíos | HIGH | NXL-004, NXL-005, NXL-008 |
| NXL-010 | Eventos de seguimiento y transiciones de estado | HIGH | NXL-007, NXL-009 |
| NXL-011 | Autenticación y roles | CRITICAL | NXL-008 |
| NXL-012 | Tests de integración con Testcontainers | MEDIUM | NXL-004, NXL-006 |

Los responsables del backlog sin planificar se deciden en el refinamiento de cada sprint.
