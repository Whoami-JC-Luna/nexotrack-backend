# Definition of Done

Un ticket está **terminado** solo cuando se cumple todo lo siguiente. "Funciona en mi máquina" no es una de las condiciones.

## Código

- [ ] Cumple todos los criterios de aceptación del ticket.
- [ ] Sigue las reglas de [architecture.md](architecture.md) (sin lógica en controllers, sin entidades en la API…).
- [ ] Sin código muerto, `System.out.println`, TODOs sin ticket ni código comentado.
- [ ] Nombres claros. Si necesita un comentario para entenderse, primero se intenta mejorar el nombre.

## Seguridad

- [ ] Sin secretos en el código ni en la configuración del repositorio.
- [ ] Entradas validadas.
- [ ] Los errores no exponen stack traces, SQL ni nombres de clases internas.
- [ ] Revisado contra los riesgos que aplican (IDOR, mass assignment, control de acceso…).

## Tests

- [ ] Tests nuevos para el comportamiento nuevo, del tipo adecuado al riesgo.
- [ ] Cada test comprueba algo concreto y tiene un nombre que lo explica.
- [ ] `./mvnw verify` pasa en local.
- [ ] El pipeline de CI pasa (desde NXL-006).

## Proceso

- [ ] Pull Request abierta con la plantilla completa.
- [ ] Revisión aprobada (`APPROVED` o `APPROVED WITH COMMENTS` con los comentarios resueltos).
- [ ] Mergeada a `main` y rama borrada.
- [ ] Documentación actualizada si el cambio lo requiere.
- [ ] Ticket movido a `DONE` en GitHub y en el tablero.
