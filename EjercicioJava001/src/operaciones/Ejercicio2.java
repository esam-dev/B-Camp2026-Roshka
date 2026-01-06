package operaciones;

// Ejercicio 2 - Trabajar con dos variables identificar cual es el mayor 
public class Ejercicio2 {

	public static void main(String[] args) {
		
		int a = 8; 
		int b = 5; 
		
		if (a > b) {
			System.out.println("El numero mayor es : " +  a);
		} else if (b > a ) { 
			System.out.println("El numero mayor es : " + b);
		} else {
			System.out.println("Ambos numeros son iguales");
		}
	}

}
