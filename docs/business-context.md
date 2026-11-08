# Contexto de negocio

## La empresa

NexoLogistics es una empresa tecnológica española que da servicios de logística a comercios y empresas: recogida, transporte, almacenaje temporal y entrega final.

## El problema

Hoy el estado de cada envío vive repartido entre hojas de cálculo de los centros logísticos y llamadas a atención al cliente. Eso provoca tres problemas:

- Los clientes no saben dónde está su envío sin llamar.
- Los operadores registran los cambios de estado tarde o con errores.
- No hay un historial fiable para resolver reclamaciones.

## El objetivo de NexoTrack

Una API REST única que sea la fuente de verdad de cada envío y de su recorrido.

## Actores

| Actor | Qué necesita |
|---|---|
| **Cliente** | Crear envíos y consultar el estado y el historial de los suyos, y solo de los suyos. |
| **Operador logístico** | Registrar eventos de seguimiento (recogido, en tránsito, en reparto, entregado, incidencia). |
| **Administrador** | Gestionar usuarios y consultar cualquier envío. |

## Requisitos de alto nivel

1. Alta de envíos con origen, destino, destinatario y datos del paquete.
2. Identificador público de seguimiento no adivinable.
3. Registro de eventos de tracking con fecha, ubicación y estado.
4. Solo se permiten transiciones de estado válidas (un envío entregado no vuelve a "en tránsito").
5. Historial completo y ordenado de cada envío.
6. Autenticación y autorización por rol y por propiedad del envío.

## Requisitos no funcionales

- **Seguridad primero:** el sistema maneja direcciones y datos personales (RGPD). Un cliente nunca puede ver envíos de otro.
- **Trazabilidad:** cada cambio de estado queda registrado y no se puede borrar.
- **Mantenibilidad:** código revisado, probado y documentado.

## Fuera de alcance (por ahora)

- Facturación y tarifas.
- Cálculo de rutas.
- Interfaz web o app móvil (NexoTrack es solo backend).
