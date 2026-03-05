# Integración JWT - Backend Spring + Frontend Next.js

## Configuración del Backend Spring

### 1. Estructura Implementada
```
src/main/java/com/elias/editorial/
├── config/
│   ├── SecurityConfig.java          # Configuración de seguridad
│   └── JwtAuthenticationFilter.java  # Filtro JWT
├── controller/
│   └── AuthController.java           # Endpoints de auth
├── dto/
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   └── RegisterRequest.java
├── model/
│   └── Usuario.java                  # Entity del usuario
├── service/
│   ├── AuthService.java              # Lógica de autenticación
│   ├── JwtService.java               # Manejo de tokens JWT
│   └── UsuarioDetailsService.java    # UserDetails service
└── repository/
    └── UsuarioRepository.java        # Repository del usuario
```

### 2. Endpoints Disponibles
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/login` - Login y obtener token

### 3. Endpoints Protegidos Actuales
Ahora puedes proteger los endpoints existentes. En los controladores (ej: `LibroController`), el acceso ya está restringido:

```java
// Sin cambios en el código, pero ahora requiere autenticación
@RestController
@RequestMapping("/api/libros")
public class LibroController {
    // Todos los endpoints ahora requieren Authentication
}
```

---

## Configuración del Frontend (Next.js)

### 1. Crear Context/Hook de Autenticación

**`src/context/AuthContext.tsx`**
```typescript
'use client';

import React, { createContext, useContext, useState, useEffect } from 'react';

interface AuthContextType {
  token: string | null;
  isAuthenticated: boolean;
  login: (username: string, password: string) => Promise<void>;
  register: (username: string, email: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType>({
  token: null,
  isAuthenticated: false,
  login: async () => {},
  register: async () => {},
  logout: () => {},
});

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    // Cargar token del localStorage al montar
    const savedToken = localStorage.getItem('token');
    if (savedToken) {
      setToken(savedToken);
    }
  }, []);

  const login = async (username: string, password: string) => {
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
      throw new Error('Login failed');
    }

    const data = await response.json();
    localStorage.setItem('token', data.token);
    setToken(data.token);
  };

  const register = async (username: string, email: string, password: string) => {
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, email, password }),
    });

    if (!response.ok) {
      throw new Error('Registration failed');
    }

    const data = await response.json();
    localStorage.setItem('token', data.token);
    setToken(data.token);
  };

  const logout = () => {
    localStorage.removeItem('token');
    setToken(null);
  };

  return (
    <AuthContext.Provider
      value={{
        token,
        isAuthenticated: !!token,
        login,
        register,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
}
```

### 2. Crear Hook para Peticiones Autenticadas

**`src/hooks/useFetchWithAuth.ts`**
```typescript
'use client';

import { useAuth } from '@/context/AuthContext';

export function useFetchWithAuth() {
  const { token } = useAuth();

  const fetchWithAuth = async (url: string, options: RequestInit = {}) => {
    const headers = {
      'Content-Type': 'application/json',
      ...options.headers,
    };

    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }

    const response = await fetch(url, {
      ...options,
      headers,
    });

    return response;
  };

  return { fetchWithAuth };
}
```

### 3. Página de Login

**`src/app/login/page.tsx`**
```typescript
'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/context/AuthContext';

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const router = useRouter();
  const { login } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      await login(username, password);
      router.push('/dashboard');
    } catch (err) {
      setError('Username o contraseña incorrectos');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <div className="max-w-md w-full">
        <h2 className="text-2xl font-bold mb-6">Login</h2>
        
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1">Username</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full px-4 py-2 border rounded-lg"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Contraseña</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-2 border rounded-lg"
              required
            />
          </div>

          {error && <p className="text-red-500 text-sm">{error}</p>}

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 disabled:opacity-50"
          >
            {loading ? 'Cargando...' : 'Login'}
          </button>
        </form>

        <p className="mt-4 text-center text-sm">
          ¿No tienes cuenta?{' '}
          <a href="/register" className="text-blue-600 hover:underline">
            Registrarse
          </a>
        </p>
      </div>
    </div>
  );
}
```

### 4. Página de Registro

**`src/app/register/page.tsx`**
```typescript
'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/context/AuthContext';

export default function RegisterPage() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const router = useRouter();
  const { register } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      await register(username, email, password);
      router.push('/dashboard');
    } catch (err) {
      setError('Error en el registro. Intenta con otro usuario/email');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <div className="max-w-md w-full">
        <h2 className="text-2xl font-bold mb-6">Registro</h2>
        
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1">Username</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full px-4 py-2 border rounded-lg"
              required
              minLength={3}
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-2 border rounded-lg"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Contraseña</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-2 border rounded-lg"
              required
              minLength={6}
            />
          </div>

          {error && <p className="text-red-500 text-sm">{error}</p>}

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 disabled:opacity-50"
          >
            {loading ? 'Cargando...' : 'Registrarse'}
          </button>
        </form>

        <p className="mt-4 text-center text-sm">
          ¿Ya tienes cuenta?{' '}
          <a href="/login" className="text-blue-600 hover:underline">
            Login
          </a>
        </p>
      </div>
    </div>
  );
}
```

### 5. Usar en Componentes

**`src/app/dashboard/page.tsx`**
```typescript
'use client';

import { useAuth } from '@/context/AuthContext';
import { useFetchWithAuth } from '@/hooks/useFetchWithAuth';
import { useEffect, useState } from 'react';

export default function DashboardPage() {
  const { token, logout, isAuthenticated } = useAuth();
  const { fetchWithAuth } = useFetchWithAuth();
  const [libros, setLibros] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!isAuthenticated) return;

    const fetchLibros = async () => {
      try {
        const response = await fetchWithAuth('http://localhost:8080/api/libros');
        const data = await response.json();
        setLibros(data);
      } catch (error) {
        console.error('Error fetching libros:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchLibros();
  }, [isAuthenticated]);

  if (!isAuthenticated) {
    return <div>No estás autenticado</div>;
  }

  return (
    <div>
      <h1>Dashboard</h1>
      <p>Token: {token?.substring(0, 20)}...</p>
      
      <button
        onClick={logout}
        className="px-4 py-2 bg-red-600 text-white rounded"
      >
        Logout
      </button>

      <h2>Libros</h2>
      {loading ? (
        <p>Cargando...</p>
      ) : (
        <ul>
          {libros.map((libro: any) => (
            <li key={libro.id}>{libro.nombre}</li>
          ))}
        </ul>
      )}
    </div>
  );
}
```

### 6. Actualizar `layout.tsx`

**`src/app/layout.tsx`**
```typescript
import { AuthProvider } from '@/context/AuthContext';

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="es">
      <body>
        <AuthProvider>{children}</AuthProvider>
      </body>
    </html>
  );
}
```

---

## Flujo Completo

1. **Usuario abre la app** → Intenta cargar el token de localStorage
2. **Sin autenticación** → Redirecciona a `/login`
3. **Usuario hace login** → Se envía credenciales a `POST /api/auth/login`
4. **Backend retorna token** → Se guarda en localStorage y AuthContext
5. **Usuario navega** → Las peticiones incluyen el header `Authorization: Bearer {token}`
6. **Backend valida token** → Si es válido, procesa la petición; si no, retorna 401
7. **Usuario hace logout** → Se elimina el token de localStorage

---

## Notas de Seguridad

✅ **Implementado:**
- Contraseñas encriptadas con BCrypt
- Tokens JWT firmados
- CORS configurado
- ValidationMayor

⚠️ **Para Producción:**
- Cambiar `jwt.secret` a una cadena aleatoria segura
- Usar HTTPS
- Configurar CORS solo para tu dominio
- Implementar refresh tokens
- Usar HttpOnly cookies para guardar tokens

---

## Variables de Entorno (.env.local en Next.js)

```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

Luego usarla en los fetch:
```typescript
const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';
const response = await fetch(`${API_URL}/api/auth/login`, ...);
```

