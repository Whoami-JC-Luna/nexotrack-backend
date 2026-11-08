# Flujo de trabajo con Git

## Ramas

`main` siempre compila, pasa los tests y está lista para desplegar. Nadie hace push directo a `main`.

Cada ticket se trabaja en su propia rama, creada desde `main` actualizado:

```
<tipo>/NXL-<número>-<descripcion-corta>
```

| Tipo | Uso | Ejemplo |
|---|---|---|
| `feature` | Funcionalidad nueva | `feature/NXL-009-crear-envios` |
| `fix` | Corrección de un bug | `fix/NXL-021-estado-entregado` |
| `chore` | Configuración, dependencias, build | `chore/NXL-001-project-setup` |
| `docs` | Solo documentación | `docs/NXL-007-adr-modelo-envios` |
| `test` | Solo tests | `test/NXL-012-testcontainers` |

```bash
git switch main
git pull
git switch -c feature/NXL-009-crear-envios
```

## Commits

Seguimos [Conventional Commits](https://www.conventionalcommits.org/es/), con la descripción en español:

```
<tipo>(<ámbito opcional>): <descripción en imperativo>
```

Ejemplos:

```
feat(shipment): añade endpoint de alta de envíos
fix(tracking): impide transición de ENTREGADO a EN_TRANSITO
test(shipment): cubre validación de destinatario vacío
```

Reglas:

- Commits pequeños y con sentido propio. Mejor cinco commits claros que uno gigante.
- Nada de mensajes tipo `cambios`, `fix` o `wip` en la rama que se va a revisar.
- Nunca se commitean secretos. Si ocurre, se avisa al Tech Lead **inmediatamente**: borrar el commit no basta, hay que rotar la credencial.

## Pull Requests

1. Antes de abrirla: `./mvnw verify` en verde y la rama actualizada con `main`.
2. Título: `NXL-XXX: descripción breve`.
3. Rellena la plantilla completa. Si algo no aplica, se explica por qué.
4. Una PR, un ticket. Si crece demasiado, se divide.
5. Responde a cada comentario de la revisión: corrígelo o argumenta por qué no.
6. El merge lo hace el Tech Lead una vez aprobada, con **squash merge**.

## Actualizar tu rama con `main`

```bash
git fetch origin
git rebase origin/main
# resuelve conflictos si los hay, y después:
git push --force-with-lease
```

`--force-with-lease` y nunca `--force` a secas: se niega a sobrescribir si alguien ha subido algo a tu rama que tú no tienes.
