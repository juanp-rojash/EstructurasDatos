# 🚀 API de Gestión de Usuarios - Acceso Rápido

## ⚡ Inicio Rápido (3 pasos)

### 1️⃣ Compilar
```bash
gradlew build
```

### 2️⃣ Ejecutar
```bash
gradlew bootRun
```

### 3️⃣ Acceder a Swagger UI
Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

---

## 📊 URLs Importantes

| Recurso | URL |
|---------|-----|
| **Swagger UI** (Documentación Interactiva) | http://localhost:8080/swagger-ui.html |
| **OpenAPI JSON** | http://localhost:8080/v3/api-docs |
| **OpenAPI YAML** | http://localhost:8080/v3/api-docs.yaml |
| **Health Check** | http://localhost:8080/actuator/health |

---

## 🔌 Endpoints Disponibles

### Crear Usuario
```
POST /v1/usuario/crear
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com",
  "password": "MiPassword123",
  "edad": 25
}
```

### Obtener Todos los Usuarios
```
GET /v1/usuario
```

### Obtener Usuario por ID
```
GET /v1/usuario/{id}
```

---

## 📚 Documentación Completa

Consulta [SWAGGER_DOCUMENTATION.md](./SWAGGER_DOCUMENTATION.md) para:
- Especificación detallada de cada endpoint
- Modelos de datos con validaciones
- Ejemplos de prueba
- Herramientas recomendadas
- Arquitectura de la aplicación

---

## ✅ Validaciones Aplicadas

| Campo | Validación |
|-------|-----------|
| `nombre` | Obligatorio, no vacío |
| `apellido` | Obligatorio, no vacío |
| `email` | Email válido |
| `password` | Obligatorio, no vacío |
| `edad` | Entre 18 y 120 años |

---

## 🛠️ Stack Tecnológico

- **Java**: 21
- **Spring Boot**: 3.5.6
- **Swagger/OpenAPI**: 2.5.0 (springdoc-openapi-ui)
- **Validación**: Jakarta Validation
- **Build**: Gradle
- **Lombok**: Reducción de código boilerplate

---

## 📝 Más Información

Consulta la documentación interactiva en **Swagger UI** donde puedes:
- ✅ Ver toda la especificación de la API
- ✅ Probar los endpoints directamente
- ✅ Ver ejemplos de request/response
- ✅ Entender los modelos de datos
- ✅ Visualizar los códigos de error

