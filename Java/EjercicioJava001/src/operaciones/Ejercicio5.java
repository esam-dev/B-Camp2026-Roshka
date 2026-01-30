package operaciones;

import java.util.Scanner;
// Ejercicio 5 - Leer el precio de un producto  y calcular el precio final con IVA (10%)
public class Ejercicio5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final double IVA = 0.10;
		System.out.println("Ingresa el precio de un producto : ");
		double precio = sc.nextDouble();
		double precioFinal = precio + (precio * IVA);
		System.out.println("Precio Final con IVA es :" + IVA);
		sc.close();

	}

}
