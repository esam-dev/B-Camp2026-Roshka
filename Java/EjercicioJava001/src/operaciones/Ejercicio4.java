package operaciones;
import java.util.Scanner;

// Ejercicio 4 -  Manejo de entrada por teclado 
public class Ejercicio4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingresa un numero : ");
		int numero = sc.nextInt();
		
		if (numero % 2 == 0 ) {
			System.out.println("El numero es divisible entre 2");
		} else {
			System.out.println("El numero no es divisible entre 2");
		}
		

	}

}
