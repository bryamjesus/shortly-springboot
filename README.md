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

---

- Java 21
- Spring Boot 3.4.3
- Spring Web
- Spring Data JPA (Hibernate)
- Spring Security
- ModelMapper
- PostgreSQL
- Maven

---

## Requisitos previos

- Java 21+
- Maven 3.6+
- PostgreSQL 14+

---

## Instalación y configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/bryamjesus/shortly-springboot.git
   cd shortly-springboot
   ```

2. **Configurar la base de datos**:
    - Crear una base de datos en PostgreSQL (por ejemplo, `shortly_db`).
    - Ajustar `src/main/resources/application-dev.properties` con tus credenciales:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/shortly_db
      spring.datasource.username=tu_usuario
      spring.datasource.password=tu_contraseña
      ```

3. **Ejecutar la aplicación**:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
   ```

---

## Endpoints de la API

| Método | Ruta                | Descripción                             | Request Body                   |
| ------ | ------------------- | --------------------------------------- | ------------------------------ |
| POST   | `/shortly`          | Generar un enlace corto                 | `{ "longUrl": "<url>" }`    |
| GET    | `/shortly/{code}`   | Obtener/Redirigir a la URL original     | N/A                            |


### Ejemplos

- Generar enlace corto:
  ```bash
  curl -X POST http://localhost:8080/shortly \
    -H "Content-Type: application/json" \
    -d '{"longUrl":"https://www.example.com"}'
  ```

- Redirigir a la URL original:
  ```bash
  curl -v http://localhost:8080/shortly/abc123
  ```

---

## Pruebas

Se incluyen pruebas de integración con MockMvc para verificar CORS y endpoints:

```bash
./mvnw test
```
