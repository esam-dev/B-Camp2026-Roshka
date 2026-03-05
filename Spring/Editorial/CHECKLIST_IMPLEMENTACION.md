# JWT Autenticación - Checklist de Implementación

## ✅ Backend Spring (Completado)

### Dependencias
- [x] `spring-boot-starter-security` agregada al pom.xml
- [x] `jjwt-api`, `jjwt-impl`, `jjwt-jackson` (v0.12.1) agregadas

### Modelos
- [x] `Usuario.java` creado (implementa UserDetails)
- [x] `UsuarioRepository.java` creado

### Servicios
- [x] `JwtService.java` - Manejo de tokens JWT
- [x] `AuthService.java` - Lógica de login/registro
- [x] `UsuarioDetailsService.java` - Implementa UserDetailsService

### Controladores
- [x] `AuthController.java` - Endpoints `/api/auth/login` y `/api/auth/register`

### Configuración
- [x] `SecurityConfig.java` - Configuración de Spring Security
- [x] `JwtAuthenticationFilter.java` - Filtro para validar tokens
- [x] `application.properties` - Configuración JWT

### DTOs
- [x] `LoginRequest.java`
- [x] `RegisterRequest.java`
- [x] `AuthResponse.java`

### Base de Datos
- [ ] Ejecutar script SQL: `crear_tabla_usuario.sql`

### Compilación
- [ ] Ejecutar `mvn clean install` o `mvn clean package`
- [ ] Verificar que no hay errores

---

## 📋 Próximos Pasos

### 1. Ejecutar Script SQL de Usuario
```bash
# En PostgreSQL
psql -U elias_user -d editorialdb -f crear_tabla_usuario.sql
```

### 2. Compilar y Ejecutar Backend
```bash
cd Spring/Editorial
mvn clean install
mvn spring-boot:run
```

O desde VS Code: 
- Presionar `F5` para ejecutar en debug
- O usar la terminal de VS Code: `mvn spring-boot:run`

### 3. Probar Endpoints con Postman/cURL

#### Registro:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario1",
    "email": "usuario1@example.com",
    "password": "password123"
  }'
```

#### Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario1",
    "password": "password123"
  }'
```

**Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvMSIsImlhdCI6MTcwODAwMDAwMCwiZXhwIjoxNzA4MDg2NDAwfQ...",
  "expiresIn": 86400000
}
```

#### Usar Token en Petición Protegida:
```bash
curl -X GET http://localhost:8080/api/libros \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

### 4. Configurar Frontend Next.js

Crear los archivos en tu proyecto Next.js:
1. Context de autenticación en `src/context/AuthContext.tsx`
2. Hook de fetch autenticado en `src/hooks/useFetchWithAuth.ts`
3. Páginas de login/registro
4. Envolver la app con `<AuthProvider>`

Ver detalles en: `INTEGRACION_NEXTJS.md`

### 5. Variables de Entorno (Producción)

En el backend, cambiar el secret en `application.properties`:
```properties
jwt.secret=GENERAR_UNA_CLAVE_ALEATORIA_SEGURA_MUY_LARGA
jwt.expiration=86400000
```

Se puede generar un secret con:
```bash
openssl rand -hex 32
```

---

## 🔒 Seguridad - Verificar

- [ ] El secret JWT es lo suficientemente largo (mínimo 32 caracteres)
- [ ] Las contraseñas se encriptan con BCrypt
- [ ] CORS está configurado correctamente
- [ ] Los endpoints protegidos requieren autenticación
- [ ] El token JWT incluye expiración

---

## 📚 Documentación

- `JWT_API_DOCUMENTATION.md` - Documentación de endpoints
- `INTEGRACION_NEXTJS.md` - Integración con Next.js
- `crear_tabla_usuario.sql` - Script para crear tabla

---

## 🐛 Troubleshooting

### Error: "Usuario no encontrado"
- Verificar que la tabla `usuario` existe en la BD
- Verificar credenciales de acceso a PostgreSQL

### Error: "Invalid JWT token"
- El token puede estar expirado
- Verificar que el secret es el mismo en la generación y validación
- Incluir "Bearer " en el header Authorization

### Error: CORS
- Verificar que el origin está en la lista blanca en `SecurityConfig.java`
- Cambiar de http://localhost:3000 si es necesario

### Error: Clase Usuario no se encuentra
- Maven puede necesitar recargar índices
- Ejecutar `mvn clean install` nuevamente
- Limpiar caché de VS Code: `Ctrl+Shift+P` → "Java: Clean Language Server Workspace"

---

## ✨ Características Que Puedes Agregar Después

- [ ] Refresh tokens
- [ ] Roles y permisos (ROLE_USER, ROLE_ADMIN)
- [ ] Email verification
- [ ] Two-factor authentication
- [ ] OAuth2 (Google, GitHub)
- [ ] Logout (blacklist de tokens)

