package operaciones;

import java.util.Scanner;
// El ejercicio 4 esta con el  3 ya que es una modificacion del codigo del ejercicio 3 
// Ejercicio 10 - Días  laborales de una semana 
public class Ejercicio09 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingresa un día de la semana: ");
		String día = sc.nextLine().toLowerCase();
		 
		switch (día){
			case "lunes" :
			case "martes":
			case "miercoles":
			case "jueves" : 
			case "viernes":
				System.out.println("Es un día laboral ");
				break;
			case "sabado":
			case "sábado":
			case "domingo":
				System.out.println("No es un día laboral ");
				break;
				
			default : 
				System.out.println("Día invalido");
		
		}
		sc.close();
	}

}
