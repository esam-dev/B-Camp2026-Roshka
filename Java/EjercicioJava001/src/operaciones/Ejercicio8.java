package operaciones;

// Aplicacion que contiene una contraseña  y cuenta la cantidad de intentos 
import java.util.Scanner;

public class Ejercicio8 {

	public static void main(String[] args) {
		
		Scanner sc = new  Scanner(System.in);
		
		String password = "java123";
		String input; 
		int intentos = 3; 
		boolean correcto = false;
		while (intentos > 0 && !correcto) {
			System.out.println("Ingresa la contraseña : ");
			input = sc.nextLine();	
			
			if (input.equals(password)) {
				correcto = true;
			System.out.println("Correcto !");
		} else  {
			intentos --; 
			System.out.println("Contraseña incorrecta . Intentos restantes : " + intentos );
		}
	}
		if (!correcto) {
			System.out.println("Fallaste jaja ! ");
		}
		sc.close();
	}
}

