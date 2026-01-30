package operaciones;

import java.util.Scanner;



// Ejercicio 3 - Manejo de String 
public class Ejercicio3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingresa tu nombre :");
		
		String nombre = sc.nextLine();
		System.out.println("Hola Bienvenido !!"+ " "  + nombre);
		sc.close();

	}

}
