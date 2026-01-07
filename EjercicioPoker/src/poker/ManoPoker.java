package poker;

/* Clase principal que contiene la logica , analiza las cartas  y determina
   que tipo de jugadas representan */
import java.util.*;

public class ManoPoker {
	
	private final List<Carta> cartas = new  ArrayList<>();
	
	// Constructor  que valida  y crea  la mano 
	public ManoPoker(String[] codigosCartas) {
		if (codigosCartas.length != 5 ) {
			throw new IllegalArgumentException("Debe haber 5 cartas ");
		}
		Set<String> sinDuplicados = new HashSet<>();
		for (String codigo : codigosCartas) {
			if (!sinDuplicados.add(codigo)) {
				throw new IllegalArgumentException("Cartas duplicadas ");
			}
			cartas.add(new Carta(codigo));
		}
	}
	// Identifica  el tipo de mano 
	public TipoMano identificarMano() {
		Map<Integer, Integer> conteo = new HashMap<>();
		Set<Palo> palos =  new  HashSet<>();
		List<Integer> valores = new ArrayList<>();
		
		for (Carta c :  cartas) {
			int v = c.getValor().getRango();
			valores.add(v);
			conteo.put(v, conteo.getOrDefault(v, 0) + 1 );
			palos.add(c.getPalo());
		}
		Collections.sort(valores);
		
		boolean esColor = palos.size() == 1; 
		boolean esEscalera = esEscalera(valores);
		
		if (esEscalera && esColor) return TipoMano.ESCALERA_COLOR;
		if (conteo.containsValue(4)) return  TipoMano.POKER;
		if (conteo.containsValue(3) && conteo.containsValue(2)) return TipoMano.FULL;
		if (esColor) return TipoMano.COLOR;
		if (esEscalera) return TipoMano.ESCALERA;
		if (conteo.containsValue(3)) return TipoMano.TRIO;
		
		int pares = 0; 
		for (int c : conteo.values()) {
			if (c == 2) pares++;
		}
		
		if (pares == 2)  return TipoMano.DOBLE_PAR;
		if (pares == 1) return TipoMano.PAR;
		
		return TipoMano.CARTA_ALTA;		
	}
	// Verifica si los valores forman una escalera
	private boolean esEscalera (List<Integer> valores) {
		// Caso A-2-3-4-5
		if (valores.equals(Arrays.asList(2, 3, 4, 5, 14 ))) {
			return true;  
		}
		for (int  i = 0;  i < valores.size() -1 ; i++) {
			if (valores.get(i) + 1  !=  valores.get(i + 1 )) {
				return false;
			}
		}
		return true;
	}

}
