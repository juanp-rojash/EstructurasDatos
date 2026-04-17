# 📚 Documentación Swagger - API de Gestión de Usuarios

## 📋 Descripción General

Esta es la documentación completa para la **API de Gestión de Usuarios** desarrollada con Spring Boot 3.5.6 y Java 21. La documentación interactiva está disponible a través de **Swagger UI** utilizando la librería `springdoc-openapi-starter-webmvc-ui:2.5.0`.

---

## 🚀 Inicio Rápido

### 1️⃣ Compilar el Proyecto

```bash
# Usando Gradle (Windows)
gradlew build

# Usando Gradle (Linux/Mac)
./gradlew build
```

### 2️⃣ Ejecutar la Aplicación

```bash
# Usando Gradle
gradlew bootRun

# O ejecutar directamente el JAR
java -jar build/libs/data-0.0.1-SNAPSHOT.jar
```

### 3️⃣ Acceder a Swagger UI

Una vez que la aplicación esté corriendo, abre tu navegador en:

```
http://localhost:8080/swagger-ui.html
```

O accede a la especificación OpenAPI en formato JSON:

```
http://localhost:8080/v3/api-docs
```

---

## 📡 Especificación de la API

### Información General
- **Nombre**: API de Gestión de Usuarios
- **Versión**: 1.0.0
- **Base URL**: `http://localhost:8080`
- **Descripción**: API REST para la gestión y administración de usuarios en el sistema de estructuras de datos

### Contacto
- **Nombre**: Soporte Técnico
- **Email**: soporte@estructuras.co

### Licencia
- **Nombre**: Apache 2.0
- **URL**: https://www.apache.org/licenses/LICENSE-2.0.html

---

## 🔌 Endpoints Documentados

### 📌 1. Crear Nuevo Usuario

**Endpoint**: `POST /v1/usuario/crear`

**Descripción**: Crea un nuevo usuario en el sistema con los datos proporcionados. El email debe ser único y válido.

**Request Body**:
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com",
  "password": "MiPassword123",
  "edad": 25
}
```

**Parámetros**:

| Parámetro | Tipo | Requerido | Descripción | Validación |
|-----------|------|-----------|-------------|-----------|
| `nombre` | string | ✅ | Nombre del usuario | Mínimo 1 carácter |
| `apellido` | string | ✅ | Apellido del usuario | Mínimo 1 carácter |
| `email` | string | ✅ | Email único y válido | Formato email válido |
| `password` | string | ✅ | Contraseña del usuario | Mínimo 8 caracteres |
| `edad` | integer | ✅ | Edad del usuario | Entre 18 y 120 años |

**Respuesta Exitosa (200)**:
```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com"
}
```

**Códigos de Respuesta**:

| Código | Descripción |
|--------|-------------|
| `200` | Usuario creado exitosamente |
| `400` | Datos inválidos - Validación fallida |
| `500` | Error interno del servidor |

**Ejemplo cURL**:
```bash
curl -X POST "http://localhost:8080/v1/usuario/crear" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@example.com",
    "password": "MiPassword123",
    "edad": 25
  }'
```

---

### 📌 2. Obtener Todos los Usuarios

**Endpoint**: `GET /v1/usuario`

**Descripción**: Recupera la lista de todos los usuarios registrados en el sistema.

**Respuesta Exitosa (200)**:
```
Usuarios
```

**Códigos de Respuesta**:

| Código | Descripción |
|--------|-------------|
| `200` | Listado de usuarios obtenido exitosamente |
| `500` | Error interno del servidor |

**Ejemplo cURL**:
```bash
curl -X GET "http://localhost:8080/v1/usuario"
```

---

### 📌 3. Obtener Usuario por ID

**Endpoint**: `GET /v1/usuario/{id}`

**Descripción**: Recupera los detalles de un usuario específico usando su identificador único.

**Parámetros**:

| Parámetro | Tipo | Ubicación | Requerido | Descripción |
|-----------|------|-----------|-----------|-------------|
| `id` | integer | Path | ✅ | Identificador único del usuario |

**Respuesta Exitosa (200)**:
```
Usuario con id: 1
```

**Códigos de Respuesta**:

| Código | Descripción |
|--------|-------------|
| `200` | Usuario encontrado exitosamente |
| `404` | Usuario no encontrado |
| `500` | Error interno del servidor |

**Ejemplo cURL**:
```bash
curl -X GET "http://localhost:8080/v1/usuario/1"
```

---

## 📊 Modelos de Datos

### UsuarioRequest

**Descripción**: DTO para solicitar la creación de un nuevo usuario

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com",
  "password": "MiPassword123",
  "edad": 25
}
```

**Propiedades**:

| Propiedad | Tipo | Formato | Descripción |
|-----------|------|--------|-------------|
| `nombre` | string | - | Nombre del usuario (requerido) |
| `apellido` | string | - | Apellido del usuario (requerido) |
| `email` | string | email | Email del usuario - debe ser único y válido (requerido) |
| `password` | string | - | Contraseña del usuario - mínimo 8 caracteres (requerido) |
| `edad` | integer | int32 | Edad del usuario - entre 18 y 120 años (requerido) |

---

### UsuarioResponse

**Descripción**: DTO que contiene la respuesta con los datos del usuario creado o recuperado

```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com"
}
```

**Propiedades**:

| Propiedad | Tipo | Descripción |
|-----------|------|-------------|
| `id` | integer | Identificador único del usuario |
| `nombre` | string | Nombre del usuario |
| `apellido` | string | Apellido del usuario |
| `email` | string | Email del usuario |

---

### Usuario (Entidad)

**Descripción**: Entidad que representa un usuario del sistema en la base de datos

**Propiedades**:

| Propiedad | Tipo | Modo de Acceso | Descripción |
|-----------|------|---|-------------|
| `id` | integer | Lectura | Identificador único del usuario |
| `nombre` | string | Lectura/Escritura | Nombre del usuario |
| `apellido` | string | Lectura/Escritura | Apellido del usuario |
| `email` | string | Lectura/Escritura | Email del usuario |
| `password` | string | Escritura (oculto) | Contraseña del usuario (encriptada) |
| `edad` | integer | Lectura/Escritura | Edad del usuario - entre 18 y 120 años |

---

## ✅ Validaciones

La API aplica las siguientes validaciones automáticas:

| Campo | Validación | Mensaje de Error |
|-------|-----------|-----------------|
| `nombre` | Obligatorio, no vacío | "El nombre es obligatorio" |
| `apellido` | Obligatorio, no vacío | "El apellido es obligatorio" |
| `email` | Formato email válido | "El email debe ser valido" |
| `password` | Obligatorio, no vacío | "El password es obligatorio" |
| `edad` | Mínimo 18 años | "La edad debe ser mayor a 18" |
| `edad` | Máximo 120 años | "La edad debe ser menor a 120" |

---

## 🔧 Configuración

### Dependencias Maven/Gradle

La configuración de la API está en [build.gradle](./build.gradle):

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
    
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
}
```

**Librerías principales**:
- **Spring Boot Starter Web**: Framework web RESTful
- **Spring Boot Starter Validation**: Validaciones automáticas con anotaciones
- **SpringDoc OpenAPI UI**: Generación automática de Swagger/OpenAPI
- **Lombok**: Reducción de código boilerplate

### Versiones

| Dependencia | Versión |
|------------|---------|
| Spring Boot | 3.5.6 |
| Java | 21 |
| SpringDoc OpenAPI | 2.5.0 |

---

## 🧪 Ejemplos de Prueba

### Ejemplo 1: Crear un usuario válido

```bash
curl -X POST "http://localhost:8080/v1/usuario/crear" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos",
    "apellido": "García",
    "email": "carlos.garcia@example.com",
    "password": "SecurePass123",
    "edad": 30
  }'
```

**Respuesta esperada**:
```json
{
  "id": 0,
  "nombre": "Carlos",
  "apellido": "García",
  "email": "carlos.garcia@example.com"
}
```

---

### Ejemplo 2: Crear un usuario con validación fallida (edad menor a 18)

```bash
curl -X POST "http://localhost:8080/v1/usuario/crear" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pedro",
    "apellido": "López",
    "email": "pedro@example.com",
    "password": "Pass123",
    "edad": 15
  }'
```

**Respuesta esperada** (400):
```json
{
  "timestamp": "2024-01-15T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "La edad debe ser mayor a 18"
}
```

---

### Ejemplo 3: Email inválido

```bash
curl -X POST "http://localhost:8080/v1/usuario/crear" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "María",
    "apellido": "Rodríguez",
    "email": "email-invalido",
    "password": "Password123",
    "edad": 28
  }'
```

**Respuesta esperada** (400):
```json
{
  "timestamp": "2024-01-15T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "El email debe ser valido"
}
```

---

## 📱 Herramientas Recomendadas para Probar la API

### 1. **Swagger UI** (Recomendado)
- Acceso: `http://localhost:8080/swagger-ui.html`
- Interfaz visual interactiva
- Pruebas directas desde el navegador

### 2. **Postman**
- Descarga: https://www.postman.com/downloads/
- Importar desde: `http://localhost:8080/v3/api-docs`

### 3. **cURL**
- Comando de línea
- Ejemplos incluidos en esta documentación

### 4. **Thunder Client** (VS Code)
- Extensión de VS Code
- Similar a Postman pero integrada en el editor

### 5. **REST Client** (VS Code)
- Extensión de VS Code
- Usar archivos `.http` o `.rest`

---

## 🔍 Recursos Útiles

- **Swagger/OpenAPI Specification**: https://swagger.io/
- **Spring Boot Documentation**: https://spring.io/projects/spring-boot
- **SpringDoc OpenAPI**: https://springdoc.org/
- **Jakarta Validation**: https://jakarta.ee/specifications/validation/

---

## 📝 Notas de Implementación

### Arquitectura
- **Patrón**: MVC (Model-View-Controller)
- **Layer de Controlador**: `UsuarioController`
- **Layer de Servicio**: `UsuarioServices`
- **Layer de Datos**: `Usuario` (Entidad)
- **DTOs**: `UsuarioRequest`, `UsuarioResponse`

### Validaciones
- Implementadas con **Jakarta Validation**
- Validaciones en el nivel de controlador con `@Valid`
- Manejo centralizado de excepciones en `GlobalExceptionHandler`

### Documentación OpenAPI
- Generada automáticamente por **SpringDoc OpenAPI**
- Sincronizada con el código fuente
- Accesible en múltiples formatos (JSON, YAML)

---

## 📧 Soporte

Para reportar problemas o sugerencias sobre la API, contactar a:
- **Email**: soporte@estructuras.co
- **Documentación**: Consultar [SWAGGER_DOCUMENTATION.md](./SWAGGER_DOCUMENTATION.md)

---

**Última actualización**: Abril 17, 2026
**Desarrollador Senior**: Java & Spring Boot
