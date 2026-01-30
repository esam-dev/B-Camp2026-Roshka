package poker;
/* Modela  una carta individual ,se encarga  de interpretar 
   el texto ingrfesado por el usuario */
public class Carta {
	private final Valor valor;
	private final Palo palo;
	
	// Constructor  que recibe el  codigo de la carta (ej : AS , 10H)
	public Carta(String codigo) {
		codigo = codigo.toUpperCase();
		
		// Determinar si el valor es 10  o una sola letra 
		String parteValor = codigo.length()  == 3
				? codigo.substring(0,2)
			     : codigo.substring(0,1);
		
		char partePalo = codigo.charAt(codigo.length() - 1 );
		
		this.valor = Valor.desdeTexto(parteValor);
		this.palo = Palo.valueOf(String.valueOf(partePalo));
	}
	
	public Valor getValor() {
		return valor;
	}
	
	public Palo getPalo(){
		return palo;
	}
}
