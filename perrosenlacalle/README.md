# Perros en la Calle - Backend API

## Descripción
API REST para facilitar la búsqueda de perros perdidos en la calle.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database (en memoria)
- Lombok
- Maven

## Estructura del Proyecto
```
src/main/java/com/encontratumascota/perrosenlacalle/
├── controller/          # Controladores REST
├── dto/                 # Objetos de transferencia de datos
├── entity/              # Entidades JPA
├── enums/               # Enumeradores
├── excepciones/         # Manejo de excepciones
├── mapper/              # Mappers entre entidades y DTOs
├── repository/          # Repositorios de datos
├── security/            # Configuración de seguridad
└── service/             # Lógica de negocio
```

## Endpoints Disponibles

### Mascotas
- **POST** `/api/mascotas` - Crear una nueva mascota perdida
- **GET** `/api/mascotas` - Obtener todas las mascotas
- **GET** `/api/mascotas/buscar?nombre={nombre}` - Buscar mascotas por nombre
- **GET** `/api/mascotas/buscar?descripcion={descripcion}` - Buscar mascotas por descripción

## Cómo Ejecutar

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior

### Pasos
1. Clonar el repositorio
2. Navegar al directorio del proyecto
3. Ejecutar: `mvn spring-boot:run`
4. La aplicación estará disponible en: `http://localhost:8080`

### Base de Datos H2
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: (vacía)

## Ejemplos de Uso

### Crear una mascota perdida
```bash
curl -X POST http://localhost:8080/api/mascotas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Max",
    "descripcion": "Perro labrador dorado, collar azul",
    "fecha": "2024-10-15T10:30:00"
  }'
```

### Buscar todas las mascotas
```bash
curl -X GET http://localhost:8080/api/mascotas
```

### Buscar por nombre
```bash
curl -X GET "http://localhost:8080/api/mascotas/buscar?nombre=Max"
```

## Configuración
La aplicación está configurada para usar H2 Database en memoria para desarrollo. 
Para producción, cambiar la configuración en `application.properties` para usar MySQL u otra base de datos.



