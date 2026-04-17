# 📐 Arquitectura de la API y Swagger

## 🏗️ Diagrama de Arquitectura

```
┌─────────────────────────────────────────────────────────────────────┐
│                        Cliente (Navegador)                          │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │  Swagger UI (http://localhost:8080/swagger-ui.html)         │   │
│  │  - Interfaz visual interactiva                              │   │
│  │  - Pruebas de endpoints en tiempo real                      │   │
│  │  - Documentación explorable                                 │   │
│  └──────────────────────────────────────────────────────────────┘   │
└────────────────────────────┬────────────────────────────────────────┘
                             │
                    HTTP/REST Requests
                             │
┌────────────────────────────▼────────────────────────────────────────┐
│                    Spring Boot 3.5.6 (Java 21)                      │
│                                                                      │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │            UsuarioController (@RestController)              │   │
│  ├──────────────────────────────────────────────────────────────┤   │
│  │  POST   /v1/usuario/crear                                   │   │
│  │  GET    /v1/usuario                                         │   │
│  │  GET    /v1/usuario/{id}                                    │   │
│  │                                                              │   │
│  │  Anotaciones OpenAPI: @Tag, @Operation, @ApiResponse        │   │
│  └────────────────────────┬─────────────────────────────────────┘   │
│                           │                                          │
│                           ▼                                          │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │            UsuarioServices (@Service)                       │   │
│  ├──────────────────────────────────────────────────────────────┤   │
│  │  crearUsuario(UsuarioRequest): UsuarioResponse              │   │
│  │  - Lógica de negocio                                        │   │
│  │  - Validaciones adicionales                                 │   │
│  │  - Mapeo de DTO a Entity                                    │   │
│  └────────────────────────┬─────────────────────────────────────┘   │
│                           │                                          │
│                           ▼                                          │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │                Model Layer (DTOs & Entidades)               │   │
│  ├──────────────────────────────────────────────────────────────┤   │
│  │                                                              │   │
│  │  ┌─────────────────────┐  ┌──────────────────────┐          │   │
│  │  │  UsuarioRequest     │  │  UsuarioResponse     │          │   │
│  │  ├─────────────────────┤  ├──────────────────────┤          │   │
│  │  │ @Schema             │  │ @Schema              │          │   │
│  │  │ nombre              │  │ id                   │          │   │
│  │  │ apellido            │  │ nombre               │          │   │
│  │  │ email               │  │ apellido             │          │   │
│  │  │ password            │  │ email                │          │   │
│  │  │ edad                │  │ (sin password)       │          │   │
│  │  └─────────────────────┘  └──────────────────────┘          │   │
│  │                                                              │   │
│  │  ┌─────────────────────────────────────────────────┐        │   │
│  │  │         Usuario (Entity)                        │        │   │
│  │  ├─────────────────────────────────────────────────┤        │   │
│  │  │ @Schema                                         │        │   │
│  │  │ id, nombre, apellido, email, password, edad    │        │   │
│  │  └─────────────────────────────────────────────────┘        │   │
│  │                                                              │   │
│  └──────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │  Validaciones (Jakarta Validation)                          │   │
│  ├──────────────────────────────────────────────────────────────┤   │
│  │  @NotBlank  @Email  @Min  @Max  → Reflejadas en Swagger     │   │
│  └──────────────────────────────────────────────────────────────┘   │
│                                                                      │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │  SpringDoc OpenAPI                                          │   │
│  ├──────────────────────────────────────────────────────────────┤   │
│  │  Escanea @Operation, @Schema, etc.                          │   │
│  │  Genera especificación OpenAPI/Swagger                      │   │
│  │  En: /v3/api-docs (JSON) y /v3/api-docs.yaml (YAML)         │   │
│  └──────────────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────────────┘
```

---

## 🔄 Flujo de Solicitud HTTP

```
1. Cliente (Navegador/Postman)
   │
   └─→ POST /v1/usuario/crear
       {
         "nombre": "Juan",
         "apellido": "Pérez",
         "email": "juan@example.com",
         "password": "MiPassword123",
         "edad": 25
       }
       │
       ▼
2. Spring DispatcherServlet
   └─→ Enruta a UsuarioController
       │
       ▼
3. UsuarioController.crearUsuario()
   @Valid ← Valida UsuarioRequest
   ├─ @NotBlank validado
   ├─ @Email validado
   ├─ @Min/@Max validado
   │
   └─→ Pasa validación ✅
       └─→ Llama a UsuarioServices.crearUsuario()
           │
           ▼
4. UsuarioServices
   ├─ Crea objeto Usuario
   ├─ Asigna ID
   ├─ Mapea a UsuarioResponse
   │
   └─→ Retorna UsuarioResponse
       │
       ▼
5. Spring Serializa a JSON
   └─→ Respuesta HTTP 200
       {
         "id": 0,
         "nombre": "Juan",
         "apellido": "Pérez",
         "email": "juan@example.com"
       }
       │
       └─→ Cliente recibe respuesta ✅
```

---

## 📋 Capas de la Aplicación

### Capa 1: Controller (API REST)
**Archivos**: `UsuarioController.java`
- Maneja solicitudes HTTP
- Validación inicial con `@Valid`
- Anotaciones OpenAPI: `@Tag`, `@Operation`, `@ApiResponse`
- Devuelve DTOs (no Entities)

### Capa 2: Service (Lógica de Negocio)
**Archivos**: `UsuarioServices.java`
- Contiene lógica de negocio
- Transforma datos (DTO → Entity)
- Acceso a datos (ahora en memoria)
- Sin anotaciones web

### Capa 3: Model (Datos)
**Archivos**:
- `Entity/Usuario.java` - Entidad de dominio
- `Dto/user/UsuarioRequest.java` - DTO de entrada
- `Dto/user/UsuarioResponse.java` - DTO de salida

Todas con anotaciones `@Schema` para documentación

### Capa 4: Exception Handling
**Archivos**: `GlobalExceptionHandler.java`
- Manejo centralizado de excepciones
- Traduce excepciones a respuestas HTTP
- Compatible con Swagger

---

## 🎯 Integración Swagger por Capa

### ✅ Controller
```java
@Tag(name = "Gestión de Usuarios")
@Operation(summary = "Crear nuevo usuario")
@ApiResponses({...})
@Parameter(name = "id", ...)
```

### ✅ DTO Request
```java
@Schema(name = "UsuarioRequest", description = "...")
@NotBlank(message = "...")
@Email(message = "...")
@Min(value = 18, ...)
@Max(value = 120, ...)
```

### ✅ DTO Response
```java
@Schema(name = "UsuarioResponse", ...)
@Schema(description = "Identificador único", example = "1")
```

### ✅ Entity
```java
@Schema(name = "Usuario", ...)
@Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
```

### ✅ Configuration
```properties
springdoc.swagger-ui.enabled=true
springdoc.api-docs.path=/v3/api-docs
```

---

## 🚀 Flujo de Inicio

```
1. Ejecutar: gradlew bootRun
   │
   ▼
2. Spring Boot Inicia
   │
   ├─ Carga aplicación DataApplication
   ├─ Lee @OpenAPIDefinition
   ├─ Registra componentes (@Controller, @Service, etc.)
   │
   ▼
3. SpringDoc OpenAPI Scanner
   │
   ├─ Escanea todas las anotaciones @Operation, @Schema, etc.
   ├─ Lee @NotBlank, @Email, @Min, @Max, etc.
   ├─ Genera especificación OpenAPI
   │
   ▼
4. SpringBoot Listener
   │
   ├─ Expone /v3/api-docs (JSON)
   ├─ Expone /v3/api-docs.yaml (YAML)
   ├─ Sirve /swagger-ui.html
   │
   ▼
5. Aplicación Lista
   │
   └─ Acceso: http://localhost:8080/swagger-ui.html ✅
```

---

## 📊 Stack Tecnológico Detallado

```
Frontend:
├─ Swagger UI 5.x (Interfaz interactiva)
└─ JavaScript/HTML/CSS

Backend:
├─ Java 21
├─ Spring Boot 3.5.6
│  ├─ spring-boot-starter-web
│  ├─ spring-boot-starter-validation
│  └─ springdoc-openapi-starter-webmvc-ui:2.5.0
├─ Jakarta EE (Validation, Web)
├─ Lombok
└─ Gradle

Especificación:
├─ OpenAPI 3.0.0
├─ JSON
└─ YAML

Protocolos:
├─ HTTP/1.1
├─ REST
└─ JSON (application/json)
```

---

## 🔐 Seguridad en la Documentación

Por seguridad, se pueden ocultar ciertos campos en Swagger:

```java
// Campo oculto en la respuesta
@Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
private String password;

// Campo de solo lectura
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
private long id;
```

---

## 📈 Escalabilidad

Para escalar la documentación:

1. **Múltiples Controladores**: Cada uno con su `@Tag` única
2. **Versionado de API**: URLs como `/v2/usuario`
3. **Seguridad**: Agregar `@SecurityScheme` para OAuth/JWT
4. **Filtrado**: Usar `@Hidden` para endpoints internos
5. **Custom Schemas**: Crear esquemas reutilizables

---

## 🎓 Ejemplo de Expansión Futura

```java
// Controlador Adicional
@RestController
@RequestMapping("/v1/admin")
@Tag(name = "Administración")
public class AdminController { ... }

// Con autenticación
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "bearer")
public class DataApplication { ... }

// Endpoint seguro
@Operation(security = @SecurityRequirement(name = "bearer"))
@PostMapping("/crear")
public UsuarioResponse crearUsuario(...) { ... }
```

---

**Arquitectura diseñada para**: Mantenibilidad, Documentación automática, Escalabilidad  
**Última actualización**: Abril 17, 2026  
**Estado**: Producción-Ready
