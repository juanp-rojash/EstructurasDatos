# 🔧 Integración de Swagger/OpenAPI - Guía Técnica

## 📋 Resumen de Cambios Realizados

Esta documentación describe los cambios técnicos realizados para integrar la documentación **Swagger/OpenAPI** en el proyecto Spring Boot usando la librería `springdoc-openapi-starter-webmvc-ui:2.5.0`.

---

## 1️⃣ Configuración de la Aplicación Principal

### Archivo: `DataApplication.java`

Se agregó la anotación `@OpenAPIDefinition` para definir la información general de la API:

```java
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestión de Usuarios",
        version = "1.0.0",
        description = "API REST para la gestión y administración de usuarios...",
        contact = @Contact(
            name = "Soporte Técnico",
            email = "soporte@estructuras.co"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        )
    )
)
```

### Propósito
- Define los metadatos generales de la API
- Proporciona información de contacto y licencia
- Se muestra en la página principal de Swagger UI

---

## 2️⃣ Documentación del Controlador

### Archivo: `UsuarioController.java`

#### Anotaciones de Clase
```java
@Tag(
    name = "Gestión de Usuarios",
    description = "Endpoints para crear, obtener y gestionar usuarios del sistema"
)
```

**Propósito**: Agrupa todos los endpoints del controlador bajo una categoría en Swagger UI.

#### Anotaciones de Método (POST - Crear Usuario)

```java
@Operation(
    summary = "Crear nuevo usuario",
    description = "Crea un nuevo usuario en el sistema con los datos proporcionados..."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Usuario creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioResponse.class)
        )
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Datos inválidos - Validación fallida"
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor"
    )
})
```

**Propósito**:
- `@Operation`: Describe qué hace el endpoint
- `@ApiResponses`: Documenta todos los posibles códigos HTTP de respuesta
- `@ApiResponse`: Define cada respuesta posible con su esquema

#### Anotaciones de Parámetros

```java
@Parameter(
    name = "id",
    description = "Identificador único del usuario",
    required = true,
    example = "1"
)
@PathVariable long id
```

**Propósito**: Documenta parámetros de entrada con ejemplos y descripciones.

---

## 3️⃣ Documentación de DTOs

### Archivo: `UsuarioRequest.java`

```java
@Schema(
    name = "UsuarioRequest",
    description = "DTO para solicitar la creación de un nuevo usuario",
    example = "{\"nombre\":\"Juan\",...}"
)
public record UsuarioRequest(
    
    @Schema(
        description = "Nombre del usuario",
        example = "Juan",
        minLength = 1
    )
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,
    
    // ... otros campos
)
```

**Propósito**:
- `@Schema` en la clase: Define la información general del modelo
- `@Schema` en campos: Documenta cada propiedad con tipo, ejemplo y restricciones
- Se sincroniza automáticamente con las validaciones (`@NotBlank`, `@Email`, etc.)

### Archivo: `UsuarioResponse.java`

```java
@Schema(
    name = "UsuarioResponse",
    description = "DTO que contiene la respuesta con los datos del usuario creado...",
    example = "{\"id\":1,\"nombre\":\"Juan\",...}"
)
public record UsuarioResponse(
    
    @Schema(description = "Identificador único del usuario", example = "1")
    long id,
    
    // ... otros campos
)
```

---

## 4️⃣ Documentación de Entidades

### Archivo: `Usuario.java`

```java
@Schema(name = "Usuario", description = "Entidad que representa un usuario del sistema")
public class Usuario {
    
    @Schema(description = "Identificador único del usuario", example = "1")
    private long id;
    
    @Schema(description = "Contraseña del usuario (encriptada)", 
            accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;
    
    // ... otros campos
}
```

**Propósito**:
- Documenta la entidad con descripción
- Define el modo de acceso (`WRITE_ONLY` para contraseñas)
- Se utiliza como esquema de referencia en respuestas

---

## 5️⃣ Configuración de Propiedades

### Archivo: `application.properties`

```properties
# Swagger UI Configuration
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.defaultModelsExpandDepth=1

# OpenAPI Configuration
springdoc.api-docs.path=/v3/api-docs
springdoc.show-actuator=false
```

**Propósito**:
- `springdoc.swagger-ui.enabled=true`: Habilita la interfaz de Swagger
- `springdoc.swagger-ui.path`: Define la URL de acceso a Swagger UI
- `springdoc.api-docs.path`: Define la URL de la especificación OpenAPI
- `operationsSorter`: Ordena endpoints por método HTTP
- `tagsSorter`: Ordena etiquetas alfabéticamente

---

## 📊 Anotaciones OpenAPI Utilizadas

### En la Clase Principal
| Anotación | Propósito |
|-----------|----------|
| `@OpenAPIDefinition` | Define metadatos generales de la API |
| `@Info` | Información de la API (título, versión, descripción) |
| `@Contact` | Información de contacto |
| `@License` | Información de licencia |

### En Controladores
| Anotación | Propósito |
|-----------|----------|
| `@Tag` | Agrupa endpoints bajo una categoría |
| `@Operation` | Describe un endpoint específico |
| `@ApiResponses` | Define respuestas posibles |
| `@ApiResponse` | Define una respuesta específica |
| `@Parameter` | Documenta parámetros de entrada |

### En DTOs y Entidades
| Anotación | Propósito |
|-----------|----------|
| `@Schema` | Define el esquema del modelo |

### Relacionadas con Validación
| Anotación | Propósito |
|-----------|----------|
| `@NotBlank` | Campo obligatorio (se refleja en Swagger) |
| `@Email` | Formato email (se refleja en Swagger) |
| `@Min/@Max` | Rango numérico (se refleja en Swagger) |

---

## 🔄 Flujo de Documentación Automática

```
┌─────────────────────────────────────┐
│   Código Java con Anotaciones       │
│   (@Operation, @Schema, etc.)       │
└────────────────┬────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────┐
│   SpringDoc OpenAPI Scanner         │
│   (Escanea las anotaciones)         │
└────────────────┬────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────┐
│   Genera Especificación OpenAPI     │
│   (JSON/YAML)                       │
│   En: /v3/api-docs                  │
└────────────────┬────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────┐
│   Swagger UI                        │
│   (Interfaz Interactiva)            │
│   En: /swagger-ui.html              │
└─────────────────────────────────────┘
```

---

## 🎯 URLs Finales

Después de ejecutar `gradlew bootRun`, accede a:

| Recurso | URL |
|---------|-----|
| Swagger UI (Interfaz Interactiva) | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |
| OpenAPI YAML | http://localhost:8080/v3/api-docs.yaml |

---

## 🔍 Validaciones Reflejadas en Swagger

Las anotaciones de validación se **reflejan automáticamente** en la documentación:

```java
@NotBlank(message = "El nombre es obligatorio")  ➜ Campo requerido
@Email(message = "El email debe ser valido")    ➜ Formato: email
@Min(value = 18, ...)                           ➜ Mínimo: 18
@Max(value = 120, ...)                          ➜ Máximo: 120
```

Esto proporciona validación **en cliente** (UI) y **en servidor** (Java).

---

## 💡 Ventajas de Esta Implementación

✅ **Documentación Sincronizada**: Se actualiza automáticamente con el código  
✅ **Pruebas Interactivas**: Swagger UI permite probar endpoints sin herramientas externas  
✅ **Especificación Estándar**: Cumple con OpenAPI 3.0  
✅ **Validaciones Documentadas**: Las restricciones aparecen en la documentación  
✅ **Ejemplos Incluidos**: Cada campo tiene ejemplos predefinidos  
✅ **Sin Configuración Manual**: SpringDoc lo genera automáticamente  

---

## 📚 Bibliotecas Utilizadas

```gradle
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
```

Esta librería proporciona:
- Escaneo automático de anotaciones
- Generación de especificación OpenAPI
- Interfaz Swagger UI integrada
- Compatibilidad con Spring Boot 3.x y Jakarta EE

---

## 🔧 Extensiones Futuras

Para mejorar la documentación en el futuro:

1. **Autenticación**: Agregar `@SecurityScheme` para documentar OAuth/JWT
2. **Ejemplos Detallados**: Usar `@ExampleObject` para casos de uso complejos
3. **Versionado de API**: Usar `@ApiVersion` para múltiples versiones
4. **Documentación de Errores**: Crear DTO específico para `GlobalExceptionHandler`
5. **Rate Limiting**: Documentar límites de tasa en operaciones

---

**Última actualización**: Abril 17, 2026  
**Desarrollador**: Senior Java Developer
