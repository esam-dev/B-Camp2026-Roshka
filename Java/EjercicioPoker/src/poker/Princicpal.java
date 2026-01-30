package poker;

import java.util.Scanner;

public class Princicpal {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese 5 cartas (ej: AS  KH  QD  JC  10S):");
		
		String[] entrada = scanner.nextLine().split("\\s+");
		
		try {
			ManoPoker mano = new ManoPoker(entrada);
			System.out.println("Jugada: " + mano.identificarMano());
		} catch (Exception e ) {
			System.out.println("Error : " + e.getMessage());
		}

	}

}
