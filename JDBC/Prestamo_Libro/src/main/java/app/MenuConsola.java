package app;

import dao.LibroDAO;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuConsola {

    public static void  main (String [] args){
        Scanner  sc = new Scanner(System.in);
        int opcion;

        do{

            System.out.println("\n ===  SISTEMA DE PRÉSTAMOS ===");
            System.out.println("1. Gestionar libros ");
            System.out.println("2. Gestionar Clientes ");
            System.out.println("3. Gestionar Préstamos");
            System.out.println("0.Salir ");
            System.out.println("Opcion: ");

            // Validacion de entero
            if(!sc.hasNextInt()){
                System.out.println("Entrada no valida.Ingrese un numero ");
                sc.nextLine(); // Limpia entrada invalida
                continue;
            }
             opcion = sc.nextInt();
            sc.nextLine(); // Limpiamos el salto de linea  pendiente

            switch (opcion){
                case 1  -> menuLibros(sc);
                case 2  -> menuClientes(sc);
                case 3  -> menuPrestamos(sc);
                case 0  -> System.out.println(" Saliendo del sistema...");
                default -> System.out.println("Opción inválida");

            }

        } while (opcion != 0);

    }
}

     public static void menuLibros(Scanner sc) {
         int opcion;

         do {
             System.out.println("\n--- MENU LIBROS ---");
             System.out.println("1.Listar libros ");
             System.out.println("2.Buscar libro por título");
             System.out.println("3.Agregar libro ");
             System.out.println("4.Actualizar libro");
             System.out.println("5.ELiminar libro");
             System.out.println("0.Volver");
             System.out.println("Opcion :");

             // Validacion de entero
             if (!sc.hasNextInt()) {
                 System.out.println("Entrada no valida.Ingrese un numero ");
                 sc.nextLine(); // Limpia entrada invalida
                 continue;
             }
             opcion = sc.nextInt();
             sc.nextLine();

             switch (option ){

                 case 1 -> {
                         System.out.println("[Listando Libros ...");
                         //LibroDAO.listar();
                 }
                 case 2 -> {
                     System.out.println("Ingrese  titulo : ");
                     String titulo = sc.nextLine();
                     System.out.println("[Buscando : " + titulo + "]");
                     // LibroDAO.
                 }
                 case 3 -> {

                 }
                 case 4 -> {

                 }
                 case 5 -> {

                 }
                 case 0 -> {

                 }
             }


         }
     }
