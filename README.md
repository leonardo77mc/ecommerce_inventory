# E-commerce Inventory API

API REST para gestión de inventario de e-commerce desarrollada con **Spring Boot** siguiendo los principios de **Arquitectura Hexagonal/Clean Architecture**.

## 🚀 Características

- ✅ Consulta de disponibilidad de productos desde microservicio externo
- ✅ Actualización de stock de productos en microservicio externo
- ✅ Documentación completa con Swagger/OpenAPI 3
- ✅ Arquitectura Hexagonal (Clean Architecture)
- ✅ Validaciones de entrada
- ✅ Manejo de errores robusto
- ✅ Configuración por perfiles (dev, prod)

## 🏗️ Arquitectura

El proyecto sigue los principios de **Arquitectura Hexagonal**:

```
src/main/java/com/ecommerce_inventory/
├── config/                     # Configuraciones (OpenAPI, RestTemplate)
└── product/
    ├── application/            # Casos de uso (Use Cases)
    ├── domain/                 # Entidades y puertos del dominio
    └── infrastructure/         # Adaptadores
        ├── external/           # Servicios externos
        ├── persistence/        # Persistencia (JPA)
        └── web/               # Controladores REST
```

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL** (producción)
- **H2** (testing)
- **SpringDoc OpenAPI 3** (Swagger)
- **Gradle**
- **Lombok**

## 📋 Prerrequisitos

- Java 17+
- PostgreSQL 15+ (para perfil dev)
- Microservicio externo ejecutándose en `http://localhost:8088`

## ⚙️ Configuración

### Base de Datos (Perfil dev)
```yaml
# application-dev.yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/ecommerce
    username: postgres
    password: postgres
```

### Servicio Externo
```yaml
# application-dev.yml
external:
  product:
    service:
      url: http://localhost:8088
```

## 🚀 Ejecución

### 1. Clonar el repositorio
```bash
git clone <repository-url>
cd ecommerce_inventory
```

### 2. Configurar PostgreSQL
```sql
CREATE DATABASE ecommerce;
```

### 3. Ejecutar la aplicación
```bash
# Perfil de desarrollo
./gradlew bootRun --args='--spring.profiles.active=dev'

# O usando Gradle wrapper en Windows
gradlew.bat bootRun --args='--spring.profiles.active=dev'
```

La aplicación se ejecutará en: `http://localhost:8090`

## 📚 API Endpoints

### 🔍 Consultar Disponibilidad de Producto

**GET** `/api/products/{id}/availability`

Obtiene la disponibilidad (stock) de un producto desde el microservicio externo.

**Ejemplo:**
```bash
curl -X GET "http://localhost:8090/api/products/1/availability"
```

**Respuesta:**
```json
{
  "productId": 1,
  "stock": 25,
  "available": true
}
```

### 📝 Actualizar Stock de Producto

**PUT** `/api/products/{id}/stock`

Actualiza el stock de un producto en el microservicio externo.

**Ejemplo:**
```bash
curl -X PUT "http://localhost:8090/api/products/1/stock" \
  -H "Content-Type: application/json" \
  -d '{"stock": 14}'
```

**Body de la petición:**
```json
{
  "stock": 14
}
```

**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "name": "Producto Ejemplo",
  "description": "Descripción del producto",
  "price": 99.99,
  "stock": 14,
  "category": "Electronics"
}
```

## 📖 Documentación Swagger

La documentación interactiva de la API está disponible en:

**URL:** `http://localhost:8090/swagger-ui.html`

### Características de la documentación:
- ✅ Descripción detallada de cada endpoint
- ✅ Ejemplos de peticiones y respuestas
- ✅ Códigos de estado HTTP documentados
- ✅ Esquemas de datos de entrada y salida
- ✅ Validaciones documentadas
- ✅ Interfaz interactiva para probar endpoints

## 🔧 Casos de Uso Implementados

### 1. GetProductAvailabilityUseCase
- **Propósito:** Obtener disponibilidad de producto desde servicio externo
- **Validaciones:** ID de producto válido
- **Manejo de errores:** Producto no encontrado, servicio no disponible

### 2. UpdateProductStockUseCase
- **Propósito:** Actualizar stock de producto en servicio externo
- **Validaciones:** ID positivo, stock no negativo
- **Manejo de errores:** Producto no encontrado, datos inválidos

## 🌐 Servicios Externos

### ExternalProductService
Servicio que se comunica con el microservicio externo de productos:

- **Base URL:** `http://localhost:8088`
- **Endpoints consumidos:**
  - `GET /api/products/{id}` - Obtener producto
  - `PUT /api/products/{id}` - Actualizar producto

## 🧪 Testing

```bash
# Ejecutar tests
./gradlew test

# Ejecutar tests con reporte de cobertura
./gradlew test jacocoTestReport
```

Los reportes de cobertura se generan en: `build/jacocoHtml/`

## 📁 Estructura de DTOs

### UpdateStockRequest
```java
{
  "stock": 14  // Integer, mínimo 0, requerido
}
```

### ProductAvailabilityResponse
```java
{
  "productId": 1,
  "stock": 25,
  "available": true
}
```

### ExternalProductDto
```java
{
  "id": 1,
  "name": "Producto",
  "description": "Descripción",
  "price": 99.99,
  "stock": 14,
  "category": "Electronics"
}
```

## 🔒 Validaciones

- **ID de producto:** Debe ser un número positivo
- **Stock:** Debe ser un número entero no negativo
- **Campos requeridos:** Validación automática con `@Valid`

## 📊 Códigos de Respuesta HTTP

| Código | Descripción |
|--------|-------------|
| 200 | Operación exitosa |
| 400 | Datos de entrada inválidos |
| 404 | Producto no encontrado |
| 500 | Error interno del servidor o servicio externo no disponible |

## 🔄 Flujo de Comunicación

```
Cliente → ProductController → UseCase → ExternalProductService → Microservicio Externo
```

## 🚨 Manejo de Errores

- **RestClientException:** Cuando el servicio externo no está disponible
- **IllegalArgumentException:** Para validaciones de negocio
- **404 Not Found:** Cuando el producto no existe
- **400 Bad Request:** Para datos de entrada inválidos

## 🔧 Configuración de Desarrollo

### Perfiles disponibles:
- **dev:** Desarrollo con PostgreSQL
- **test:** Testing con H2 en memoria

### Logs habilitados:
- SQL queries (DEBUG)
- Hibernate SQL formatting
- Application logs (DEBUG level)

## 📝 Notas Importantes

1. **Microservicio externo requerido:** La aplicación necesita que el microservicio externo esté ejecutándose en `http://localhost:8088`

2. **Base de datos:** En perfil dev, asegúrate de tener PostgreSQL ejecutándose en el puerto 5433

3. **Puertos:**
   - Aplicación principal: `8090`
   - Microservicio externo: `8088`
   - PostgreSQL: `5433`

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

**Desarrollado con ❤️ usando Spring Boot y Arquitectura Hexagonal**