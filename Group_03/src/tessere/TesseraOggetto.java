package tessere;

import java.util.Random;

public class TesseraOggetto {

	public boolean init = true;
	public boolean presa = false;
	
	private final Colore colore;

	// variabile usata nella classe della grafica principale
	public boolean escludi = false;

	//salva percorso dell'immagine
	private String path;
	
	/**
	*Classe enumerativa che contiene i colori delle varie tessere oggetto
	*/
	public enum Colore { 
		BLUE, GREEN, YELLOW, WHITE, RED, LIGHTBLUE;

		private static final Colore[] colori = Colore.values(); // Dichiarazione array di colori
		private static final Random RandColori = new Random(); // Inizializzazione del random per il pescaggio randomico
																// dei colori

		public static Colore getColore(int i) { // Metodo che restituisce il colore in posizione i dell'array Colore[]

			return Colore.colori[i];
		}

		public static Colore randColori() { // Metodo che restituisce in modo randomico un colore
			Colore[] colori = values();
			Colore c = colori[RandColori.nextInt(colori.length)];

			return c;
		}
	}

	/**
	 * Costruttore della tessera oggetto, che ha come argomenti il colore, il tipo e il numero
	 */

	public TesseraOggetto() {
		this.colore = Colore.randColori();
		path = "img\\colors\\" + colore.name() + ".png";
	}
	
	public TesseraOggetto(boolean init) {
		this.colore = null;
		this.init = init;
	}

	public String getPath() {
		return path;
	}
	
	/**
	 * @return colore
	 */
	public Colore getColore() { 
		return this.colore;
	}
	
	/**
	 * confronta il colore della tessera nella libreria con il colore della tessera nell'obiettivo personale
	 * @param colore
	 * @return booleano
	 */
	public boolean equals(TesseraOggetto colore) {
		if (this.colore == (colore.colore)) { 
			return true;
		} else {
			return false;
		}
	}

}
