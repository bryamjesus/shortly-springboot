# Shortly - URL Shortener

**Shortly** es un servicio de acortador de URLs desarrollado con Spring Boot y Java. Permite generar enlaces cortos, gestionar redirecciones y exponer estadísticas básicas de uso.

---

## Características

- Generación de enlaces cortos a partir de URLs largas.
- Redirección automática al hacer clic en el enlace corto.
- Conteo de visitas (hit count).
- Persistencia en PostgreSQL mediante JPA/Hibernate.
- Validación de entradas con Hibernate Validator.
- Manejo global de errores con respuestas unificadas.
- Configuración de CORS externalizada por perfiles.

---

## Tecnologías

## Arquitectura del proyecto

Descripción general de la arquitectura en capas del proyecto:

- **Configuración (`config`)**: Contiene clases de configuración de Spring, como `CorsProperties`, `CorsConfig` y `SecurityConfig`, cargando propiedades externas desde `application.properties`.
- **Controladores (`controller`)**: Define los endpoints REST en `ShortlyController`, manejando la generación y redirección de URLs cortas.
- **Servicios (`service`)**: Implementa la lógica de negocio en `UrlManagerService` y su implementación `UrlManagerServiceImpl`.
- **Repositorios (`repository`)**: Interfaces que extienden `JpaRepository` para acceder y manipular la entidad `Url`.
- **Modelos (`model`)**: Entidad JPA `Url` con atributos, auditoría (`createdAt`, `modifiedAt`) y contador de visitas (`hitCount`).
- **DTOs (`dto`)**: Objetos de transferencia de datos como `ShortUrlRequest`, `UrlResponse` y `ApiResponse`.
- **Manejo de errores (`error`)**: `GlobalExceptionHandler` unifica las respuestas de error y validaciones.
- **Utilidades (`util`)**: Clases auxiliares como `ResponseUtil` para crear respuestas estándar.

Representación de la estructura de archivos:

```
src/main/java/com/bjtg/shortly/
├── config/
│   ├── CorsProperties.java
│   ├── CorsConfig.java
│   └── SecurityConfig.java
├── controller/
│   └── ShortlyController.java
├── service/
│   ├── UrlManagerService.java
│   └── UrlManagerServiceImpl.java
├── repository/
│   └── UrlRepository.java
├── model/
│   └── url/
│       └── Url.java
├── dto/
│   ├── ShortUrlRequest.java
│   ├── UrlResponse.java
│   └── ApiResponse.java
├── error/
│   └── GlobalExceptionHandler.java
└── util/
    └── ResponseUtil.java
```

