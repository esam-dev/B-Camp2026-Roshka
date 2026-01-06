package operaciones;

import java.util.Scanner;

public class Ejercicio7 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int numero; 
		
		do {
			System.out.println("Ingresa un numero mayor o igual a 0 : ");
			numero = sc.nextInt();
			} while ( numero < 0); 
		System.out.println("Numero ingresado : " + numero );
		sc.close();

	}

}
