package app;

import java.util.Scanner;

public class MenuConsola {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("\n=== SISTEMA DE PRÉSTAMOS ===");
                System.out.println("1. Gestionar libros");
                System.out.println("2. Gestionar clientes");
                System.out.println("3. Gestionar préstamos");
                System.out.println("0. Salir");
                System.out.print("Opción: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Entrada no válida. Ingrese un número.");
                    sc.nextLine();
                    continue;
                }
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        menuLibros(sc);
                        break;
                    case 2:
                        menuClientes(sc);
                        break;
                    case 3:
                        menuPrestamos(sc);
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }

            } while (opcion != 0);
        }
    }

    private static void menuLibros(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ LIBROS ---");
            System.out.println("1. Listar libros");
            System.out.println("2. Buscar libro por título");
            System.out.println("3. Agregar libro");
            System.out.println("4. Actualizar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Ingrese un número.");
                sc.nextLine();
                continue;
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("[Listando libros...]");
                    break;
                case 2:
                    System.out.print("Ingrese título: ");
                    String titulo = sc.nextLine();
                    System.out.println("[Buscando: " + titulo + "]");
                    break;
                case 3:
                    System.out.print("Título: ");
                    String t = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Año: ");
                    Integer anio = leerEntero(sc);
                    if (anio != null) {
                        System.out.println("[Agregando libro " + t + " - " + autor + " (" + anio + ")]");
                    }
                    break;
                case 4:
                    System.out.print("ID del libro a actualizar: ");
                    Integer id = leerEntero(sc);
                    if (id != null) {
                        System.out.print("Nuevo título: ");
                        String nt = sc.nextLine();
                        System.out.print("Nuevo autor: ");
                        String na = sc.nextLine();
                        System.out.println("[Actualizando libro ID " + id + "]");
                    }
                    break;
                case 5:
                    System.out.print("ID del libro a eliminar: ");
                    Integer idDel = leerEntero(sc);
                    if (idDel != null) {
                        System.out.println("[Eliminando libro ID " + idDel + "]");
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void menuClientes(Scanner sc) {
        // Implementación similar a menuLibros...
        System.out.println("[Menú clientes]");
    }

    private static void menuPrestamos(Scanner sc) {
        // Implementación similar a menuLibros...
        System.out.println("[Menú préstamos]");
    }

    private static Integer leerEntero(Scanner sc) {
        if (!sc.hasNextInt()) {
            System.out.println("Debe ingresar un número entero.");
            sc.nextLine();
            return null;
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }
}
