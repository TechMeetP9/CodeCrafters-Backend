# 🚀 Code Crafters Backend

## 🎯 Objetivos

> ✨ **Code Crafters**, una empresa líder en el sector tecnológico, desea implementar una **plataforma web** que facilite la organización de eventos tanto **online como presenciales** dentro de la comunidad tecnológica.  
>  
> Con el rápido avance de la tecnología, la colaboración y el intercambio de conocimientos son más importantes que nunca.  
>  
> Code Crafters busca crear una **plataforma única y poderosa** que satisfaga las necesidades específicas de la comunidad tecnológica, promoviendo la **colaboración**, el **aprendizaje continuo** y la **innovación** dentro del sector.  
>  
> Esta aplicación aspira a convertirse en el **punto de encuentro virtual** para profesionales, entusiastas y expertos en tecnología de todo el mundo.

---

## 🧠 Competencias Técnicas

- **Backend Development:** Implementación de la lógica del servidor y los endpoints RESTful.  
- **Database Creation:** Creación y estructuración de la base de datos PostgreSQL.  
- **Data Access Components:** Desarrollo de componentes que permiten la comunicación entre la API y la base de datos.  
- **Tests:** Validación del comportamiento del sistema utilizando JUnit y Mockito.  
- **Security:** Implementación de autenticación y autorización mediante Spring Security y JWT.

---

## 💻 Tecnologías

- **Lenguaje:** Java 21  
- **Framework Backend:** Spring Boot 3.3.5  
- **Seguridad:** Spring Security + JWT  
- **Base de Datos:** PostgreSQL  
- **Gestor de dependencias:** Maven  
- **Testing:** JUnit 5, Mockito  
- **Control de versiones:** Git / GitHub  
- **Pruebas de API:** Postman  

---

## 🛠️ Herramientas

- IntelliJ IDEA / Visual Studio Code  
- Trello (gestión ágil del proyecto)  
- Git / GitHub  
- Postman  
- DBeaver o pgAdmin (gestión de base de datos)  

---

## 🧩 Funcionalidades principales

La API ofrece un conjunto completo de operaciones para la **gestión de eventos tecnológicos** y **usuarios registrados**:

### 👤 Usuarios
- Registro de nuevos usuarios.  
- Inicio y cierre de sesión.  
- Actualización de perfil (nombre, correo, contraseña, imagen).  
- Listado de todos los usuarios registrados.

### 🎟️ Eventos
- Crear nuevos eventos (presenciales u online).  
- Listar todos los eventos.  
- Buscar eventos por ID, nombre, usuario o categoría.  
- Actualizar y eliminar eventos propios.  
- Paginación (máximo 15 eventos por página).  

### 🧾 Asistencias
- Apuntarse a eventos.  
- Desapuntarse de eventos.  
- Listar los asistentes de cada evento.  
- Restricción: un usuario no puede apuntarse más de una vez al mismo evento.  

### ⭐ Bonus
- Generación de tickets al inscribirse a un evento.  
- Visualización de tickets de los eventos a los que el usuario está apuntado.

---

## ⚙️ Relaciones de la base de datos

- **Usuario → Eventos (1:N)**: Un usuario puede crear varios eventos.  
- **Evento → Usuario (N:1)**: Cada evento pertenece a un único usuario.  
- **Usuario → Asistencia (1:N)**: Un usuario puede asistir a varios eventos.  
- **Evento → Asistencia (1:N)**: Un evento puede tener varios asistentes.

---

## 🧾 Validaciones

- El **nombre del evento** no puede estar vacío ni superar los 255 caracteres.  
- La **descripción** debe tener entre 50 y 2000 caracteres.  
- No se puede crear un evento con una **fecha anterior a la actual**.  
- Un usuario no puede registrarse dos veces al mismo evento.  
- Eliminación en cascada: si se elimina un usuario, también se eliminan sus eventos y asistencias.

---

## 🚀 Cómo iniciar el proyecto

### ✅ Requisitos previos

- **Java 21** instalado  
- **PostgreSQL** instalado y en ejecución  
- **Maven** configurado  

---

## ⚡ Pasos para iniciar

### 1. Clonar el repositorio
```bash
git clone https://github.com/CodeCrafters/CodeCrafters-Backend.git
cd CodeCrafters-Backend
```

### 2. Configurar la base de datos
```sql
CREATE DATABASE codecrafters_db;
```

### 3. Configurar el archivo `.env` o `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/codecrafters_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=tu_clave_secreta
jwt.expiration=3600000
```

### 4. Ejecutar el proyecto

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

### 5. Verificar la instalación
La API estará disponible en:
👉 **http://localhost:8080**

---

## 🧪 Testing

Se realizan pruebas unitarias con **JUnit 5** y **Mockito**.

Para ejecutar los tests:
```bash
./mvnw test
```

---

## 📂 Estructura del Proyecto
```
CODECRAFTERS-BACKEND/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── src/
│   ├── main/
│   │   ├── java/com/code_crafters/app/
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UsersController.java 
│   │   │   │   ├── EventsController.java 
│   │   │   │   ├── AttendanceController.java
│   │   │   │   └── CategoryController.java
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── RegisterRequest.java
│   │   │   │   │   ├── EventsRequest.java 
│   │   │   │   │   ├── AttendanceRequest.java
│   │   │   │   │   └── CategoryRequest.java
│   │   │   │   └── response/
│   │   │   │       ├── JwtResponse.java
│   │   │   │       ├── UsersResponse.java 
│   │   │   │       ├── EventsResponse.java 
│   │   │   │       ├── AttendanceResponse.java
│   │   │   │       └── CategoryResponse.java
│   │   │   │
│   │   │   ├── entity/
│   │   │   │   ├── Users.java 
│   │   │   │   ├── Events.java 
│   │   │   │   ├── Attendance.java
│   │   │   │   └── Category.java
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── BadRequestException.java
│   │   │   │   └── UnauthorizedException.java
│   │   │   │
│   │   │   ├── mapper/
│   │   │   │   ├── UsersMapper.java 
│   │   │   │   ├── EventsMapper.java 
│   │   │   │   ├── AttendanceMapper.java
│   │   │   │   └── CategoryMapper.java
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── UsersRepository.java 
│   │   │   │   ├── EventsRepository.java 
│   │   │   │   ├── AttendanceRepository.java
│   │   │   │   └── CategoryRepository.java
│   │   │   │
│   │   │   ├── security/
│   │   │   │   ├── JwtAuthFilter.java
│   │   │   │   ├── JwtUtils.java
│   │   │   │   ├── UsersDetailsImpl.java 
│   │   │   │   ├── UsersDetailsServiceImpl.java 
│   │   │   │   └── WebSecurityConfig.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── interfaces/
│   │   │   │   │   ├── UsersService.java 
│   │   │   │   │   ├── EventsService.java 
│   │   │   │   │   ├── AttendanceService.java
│   │   │   │   │   └── CategoryService.java
│   │   │   │   └── impl/
│   │   │   │       ├── UsersServiceImpl.java 
│   │   │   │       ├── EventsServiceImpl.java 
│   │   │   │       ├── AttendanceServiceImpl.java
│   │   │   │       └── CategoryServiceImpl.java
│   │   │   │
│   │   │   ├── util/
│   │   │   │   └── DateUtils.java
│   │   │   │
│   │   │   └── CodeCraftersApplication.java 
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── data.sql
│   │       └── schema.sql
│   │
│   └── test/
│       └── java/com/code_crafters/app/ 
│           └── CodeCraftersApplicationTests.java
│
├── target/
├── .env
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

## 🧭 Metodología

El proyecto se desarrolló bajo **metodología ágil (Scrum)** en un sprint de dos semanas, fomentando:
- ✅ Colaboración del equipo
- ✅ Mejora continua
- ✅ Entrega incremental de valor

---

## 🛠️ Tecnologías utilizadas

- **Spring Boot** - Framework principal
- **Spring Security** - Autenticación y autorización
- **JWT** - Tokens de seguridad
- **PostgreSQL** - Base de datos
- **JUnit 5 & Mockito** - Testing
- **Maven** - Gestión de dependencias

---

## 👥 Equipo

| Nombre | GitHub | LinkedIn |
|--------|--------|----------|
| **Ana Aguilera** | [@AnaAguileraMorales88](https://github.com/AnaAguileraMorales88) | [Ana Aguilera Morales](https://www.linkedin.com/in/ana-aguilera-morales/) |
| **Andrea Olivera** | [@andreaonweb](https://github.com/andreaonweb) | [Andrea Olivera Romero](https://www.linkedin.com/in/AndreaOliveraRomero) |
| **Gabi Gallegos** | [@hgall3](https://github.com/hgall3) | [Gabriela Gallegos Anda](https://www.linkedin.com/in/gabrielagallegosanda/) |
| **Mio Ogura** | [@miaryl](https://github.com/miaryl) | [Mio Ogura](https://www.linkedin.com/in/mio-ogura-a66880182) |
| **Montse Muñoz** | [@Montc027](https://github.com/Montc027) | [Montse Muñoz](https://www.linkedin.com/in/montse-mu%C3%B1oz-ba202b227/) |
| **Sofia Toro** | [@sofiatoroviafara01](https://github.com/sofiatoroviafara01) | [Sofía Toro Viafara](https://www.linkedin.com/in/sof%C3%ADa-toro-viafara-690124356/) |

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

