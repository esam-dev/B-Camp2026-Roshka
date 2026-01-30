package poker;
// Enum Valor - Representa el valor numerico de cada carta 
public enum Valor {
	DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6),
	SIETE(7), OCHO(8), NUEVE(10), DIEZ(10),
	JOTA(11), REINA(12), REY(13), AS(14);
	
	// Rango numerico  del valor 
	private final int rango;
	Valor(int rango) {
		this.rango = rango;
	}
	
	// Devuelve el rango  del valor 
	public int getRango() {
		return rango;
	}
	
	// Convierte  texto  ingresado  por el usuario en un Valor 
	public static Valor desdeTexto(String s) {
		return switch (s) {
		case "2" -> DOS;
		case "3" -> TRES;
		case "4" -> CUATRO;
		case "5" -> CINCO;
		case "6" -> SEIS;
		case "7" -> SIETE;
		case "8" -> OCHO;
		case "9", "T" -> DIEZ;
		case "J" -> JOTA;
		case "Q" -> REINA;
		case "K" -> REY;
		case "A" -> AS;
		default -> throw new IllegalArgumentException("Valor inválido");		
		};
	}

}
