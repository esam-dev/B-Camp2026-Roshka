# JWT Authentication API - Editorial Backend

## Descripción
Esta es la documentación de los endpoints de autenticación JWT en el backend Spring de Editorial.

## Endpoints Disponibles

### 1. Registro de Usuario
**POST** `/api/auth/register`

Registra un nuevo usuario en el sistema.

**Request Body:**
```json
{
  "username": "usuarioejemplo",
  "email": "usuario@example.com",
  "password": "micontraseña123"
}
```

**Validaciones:**
- `username`: Requerido, entre 3 y 50 caracteres, debe ser único
- `email`: Requerido, debe ser un email válido, debe ser único
- `password`: Requerido, mínimo 6 caracteres

**Response (201 Created):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": 86400000
}
```

**Posibles Errores:**
- `400 Bad Request`: Validación fallida
- `409 Conflict`: Username o email ya existe

---

### 2. Login
**POST** `/api/auth/login`

Autentica a un usuario y devuelve un token JWT.

**Request Body:**
```json
{
  "username": "usuarioejemplo",
  "password": "micontraseña123"
}
```

**Validaciones:**
- `username`: Requerido
- `password`: Requerido

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": 86400000
}
```

**Posibles Errores:**
- `401 Unauthorized`: Credenciales inválidas
- `400 Bad Request`: Validación fallida

---

## Uso del Token JWT

### Header de Autenticación
Para acceder a endpoints protegidos, incluye el token en el header `Authorization`:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### Ejemplo con cURL
```bash
# Registro
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"usuario1","email":"usuario@example.com","password":"password123"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"usuario1","password":"password123"}'

# Usar token en petición protegida
curl -X GET http://localhost:8080/api/libros \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

### Ejemplo con JavaScript/Fetch
```javascript
// Registro
const registerResponse = await fetch('http://localhost:8080/api/auth/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    username: 'usuario1',
    email: 'usuario@example.com',
    password: 'password123'
  })
});

const { token, expiresIn } = await registerResponse.json();

// Login
const loginResponse = await fetch('http://localhost:8080/api/auth/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    username: 'usuario1',
    password: 'password123'
  })
});

const { token } = await loginResponse.json();

// Usar el token para peticiones autenticadas
const protectedResponse = await fetch('http://localhost:8080/api/libros', {
  method: 'GET',
  headers: {
    'Authorization': `Bearer ${token}`
  }
});
```

### Ejemplo con Next.js
```typescript
// context/authContext.ts o similar
export const useAuth = () => {
  const [token, setToken] = useState<string | null>(
    localStorage.getItem('token')
  );

  const login = async (username: string, password: string) => {
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });

    const data = await response.json();
    localStorage.setItem('token', data.token);
    setToken(data.token);
    return data;
  };

  const register = async (username: string, email: string, password: string) => {
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, email, password })
    });

    const data = await response.json();
    localStorage.setItem('token', data.token);
    setToken(data.token);
    return data;
  };

  const logout = () => {
    localStorage.removeItem('token');
    setToken(null);
  };

  return { token, login, register, logout };
};
```

---

## Configuración

### Variables de Entorno
En `application.properties` o como variables de entorno:

```properties
# Secret para firmar los tokens (debe ser una cadena larga y segura)
jwt.secret=miClaveSecretaMuyLargaParaGenerarTokensJWTSegurosCon256BitsOMs

# Tiempo de expiración en milisegundos (86400000 = 24 horas)
jwt.expiration=86400000
```

### CORS
La aplicación está configurada para aceptar peticiones desde:
- `http://localhost:3000` (Next.js)
- `http://localhost:3001`

Modifica estos valores en `SecurityConfig.java` si necesitas otros orígenes.

---

## Estructura de Token JWT

El token JWT contiene:
1. **Header**: Tipo de token (JWT) y algoritmo (HS256)
2. **Payload**: Username y timestamps
3. **Signature**: Firma con la clave secreta

Ejemplo decodificado:
```json
{
  "sub": "usuarioejemplo",
  "iat": 1234567890,
  "exp": 1234654290
}
```

---

## Flujo de Autenticación

```
1. Usuario se registra o hace login
2. Backend devuelve un JWT
3. Cliente almacena el token (localStorage, sessionStorage, etc.)
4. Para cada petición protegida, envía: Authorization: Bearer {token}
5. Backend valida el token en cada petición
6. Si es válido, procesa la petición; si no, retorna 401
```

---

## Notas Importantes

- El token expira después de 24 horas (configurable)
- La contraseña se almacena encriptada con BCrypt
- Usa HTTPS en producción para proteger los tokens
- No expongas el secret en el código; usa variables de entorno
- Los tokens son stateless, no se almacenan en servidor

