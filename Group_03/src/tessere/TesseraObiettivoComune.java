package tessere;

import java.util.Random;

import grafica.GUI;
import tessere.TesseraOggetto.Colore;

import java.awt.Color;
import java.util.ArrayList;

public class TesseraObiettivoComune {
	
	public enum ObiettivoComune {
		
		// L'identificazione delle carte obiettivo avviene tramite semplice numerazione
		Obiettivo1, Obiettivo2, Obiettivo3, Obiettivo4, Obiettivo5, Obiettivo6, Obiettivo7, Obiettivo8, Obiettivo9,
		Obiettivo10, Obiettivo11, Obiettivo12;
		
		// Array che contiene il valore degli obiettivi (es. "Obiettivo6")
		private static final ObiettivoComune[] obiettivi = ObiettivoComune.values();
		private static Random RandObiettivo;

		/**
		 * Metodo utilizzato per prelevare l'obiettivo in una determinata posizione dall'array
		 * @param i indica la posizione dell'obiettivo all'interno dell'array
		 * @return l'obiettivo presente nell'array in posizione i
		 */
		public static ObiettivoComune getObiettivoComune(int i) {
			return ObiettivoComune.obiettivi[i];
		}
		
		// viene generato casualmente un obiettivo comune dall'array 
		public static ObiettivoComune randObiettivi() {
			ObiettivoComune[] obiettivi = values();
			RandObiettivo = new Random();
			return obiettivi[RandObiettivo.nextInt(obiettivi.length)];
		}

	}

	public ObiettivoComune obiettivo;

	public TesseraObiettivoComune() {
		obiettivo = ObiettivoComune.randObiettivi();
	}

	// al primo raggiungimento di un obiettivo comune si parte dall'attribuzione di 8 punti
	private static int punti = 8;
	
/**
 * metodo che attribuisce punti al raggiungimento degli obiettivi comuni a seconda che sia la prima volta o successive
 * @param numGiocatori indica quanti giocatori hanno già raggiunto l'obiettivo
 * @return il punteggio ottenuto dal raggiungimento dell'obiettivo
 */
	public static int getPoints(int numGiocatori) {
		int tmp = punti;
		if (numGiocatori > 2) {
			punti -= 2;
		}
		if (numGiocatori == 2) {
			punti -= 4;
		}
		return tmp;
	}

	private static int vicini = 1;

	// ogni metodo controlla il raggiungimento di un obiettivo e riceve come parametro la libreria da controllare

	public static boolean check1(TesseraOggetto[][] libreria) { 
		TesseraOggetto[][] cells = libreria;
			
		int contaGruppi = 0;	
		TesseraOggetto[][] clone = cells.clone();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (cells[i][j] != null) {
					if (clone[i][j] != null) {
						
						// controllo se ho tessere adiacenti uguali
						vicini = hasAdiacente(i, j, clone, 'n');

						// se il gruppo è da 2 tessere lo conto
						if (vicini == 2)
							contaGruppi++;
					}
				}
				vicini = 1;
			}
		}
		if (contaGruppi == 6)
			return true;
		return false;
	}

	public static boolean check2(TesseraOggetto[][] libreria) {
		//i quattro angoli della libreria devono essere uguali
		if (libreria[0][0] != null && libreria[5][0] != null && libreria[5][4] != null && libreria[0][4] != null) {
			if (libreria[0][0].getColore() == libreria[5][0].getColore()
					&& libreria[0][0].getColore() == libreria[5][4].getColore()
					&& libreria[0][0].getColore() == libreria[0][4].getColore()) {
				return true;
			}
		}
		return false;
	}

	public static boolean check3(TesseraOggetto[][] libreria) {
		TesseraOggetto[][] cells = libreria;

		int contaGruppi = 0;
		TesseraOggetto[][] clone = cells.clone();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (cells[i][j] != null) {
					if (clone[i][j] != null) {

						// controllo se ho tessere adiacenti uguali
						vicini = hasAdiacente(i, j, clone, 'n');

						// se il gruppo è da 4 lo conto
						if (vicini == 4)
							contaGruppi++;
					}
				}
				vicini = 1;
			}
		}
		if (contaGruppi == 4)
			return true;
		return false;
	}

	public static boolean check4(TesseraOggetto[][] libreria) {
		Color color = null;
		int cont = 0; // contatore per tenere traccia di quanti gruppi ci sono
		TesseraOggetto[][] clone = libreria.clone();
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 4; c++) {

				if (clone[r][c] != null && clone[r][c + 1] != null && clone[r + 1][c] != null
						&& clone[r + 1][c + 1] != null) {
					// se il possibile gruppo da 4 è diverso da null lo controllo altrimetni so già che non può essere un gruppo

					// controllo corrispondenza colori
					if (GUI.getColor(clone[r][c].getColore()).equals(GUI.getColor(clone[r][c + 1].getColore()))
							&& GUI.getColor(clone[r][c].getColore()).equals(GUI.getColor(clone[r + 1][c].getColore()))
							&& GUI.getColor(clone[r][c].getColore())
									.equals(GUI.getColor(clone[r + 1][c + 1].getColore()))) {

						// se il colore prima è null significa che è il primo gruppo altrimenti controllo se il colore è uguale al precedente
						if (color == null || GUI.getColor(clone[r][c].getColore()).equals(color)) {
							// se c'è corrispondenza conto il gruppo
							cont++;
							if (cont == 2)
								return true;

							// salvo il colore perchè il successivo gruppo deve essere dello stesso colore
							color = GUI.getColor(clone[r][c].getColore());

							// imposto a null così poi non vengono contate 
							clone[r][c] = null;
							clone[r][c + 1] = null;
							clone[r + 1][c] = null;
							clone[r + 1][c + 1] = null;

							c += 2; // se trovo un gruppo la successiva colonna da controllare è a +3 dal riferimento
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean check5(TesseraOggetto[][] libreria) {
		//array per tenere traccia dei colori di ogni colonna
		ArrayList<Colore> colori = new ArrayList<Colore>();
		boolean piena = true;
		int colonne = 0;
		for (int j = 0; j < 5; j++) {
			piena = true;
			for (int i = 0; i < 6 && piena; i++) {
				if (libreria[i][j] != null) {
					if (!colori.contains(libreria[i][j].getColore()))
						colori.add(libreria[i][j].getColore());
				} else
					piena = false;// se c'è una casella null l'obiettivo non può essere perseguito
			}

			// se la colonna è piena
			if (piena) {
				// se i colori dentro la colonna solo al massimo 3 diversi
				if (colori.size() <= 3)
					// conto la colonna
					colonne++;
			}
			// svuoto l'array che conta i colori della colonna
			colori.clear();
		}
		// se ho almeno 3 colonne che soddisfano torno true
		if (colonne >= 3)
			return true;
		else
			return false;
	}

	public static boolean check6(TesseraOggetto[][] libreria) {
		TesseraOggetto[][] clone = libreria.clone();
		Color color;
		int count;
		// ciclo uno a uno tutti i colori disponibili
		for (int k = 0; k < Colore.values().length; k++) {
			// salvo il colore da verificare
			color = GUI.getColor(Colore.getColore(k));
			count = 0;
			// scorro tutta la matrie e conto quante caselle del colore salvato trovo
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 5; j++) {
					// se la casella è piena
					if (clone[i][j] != null) {
						if (GUI.getColor(clone[i][j].getColore()).equals(color))
							count++;
					}
				}
			}
			if (count == 8)
				return true;
		}
		return false;
	}

	public static boolean check7(TesseraOggetto[][] libreria) {

		boolean diagonale = true;

		// diagonale alta da sx a dx
		Color color = new Color(1);

		if (libreria[0][0] != null)
			color = GUI.getColor(libreria[0][0].getColore());
		else
			diagonale = false;

		for (int i = 1; i < 5 && diagonale; i++) {
			if (libreria[i][i] != null) {
				if (!GUI.getColor(libreria[i][i].getColore()).equals(color))
					diagonale = false;
			} else
				diagonale = false;
		}

		// se la diagonale esiste
		if (diagonale)
			return true;
		// se la diagonale non esiste controllo quella dopo
		else
			diagonale = true;

		// diagonale alta dx a sx
		if (libreria[0][4] != null)
			color = GUI.getColor(libreria[0][4].getColore());
		else
			diagonale = false;

		for (int i = 1; i < 5 && diagonale; i++) {
			if (libreria[i][4 - i] != null) {
				if (!GUI.getColor(libreria[i][4 - i].getColore()).equals(color))
					diagonale = false;
			} else
				diagonale = false;
		}
		// se la diagonale esiste
		if (diagonale)
			return true;
		// se la diagonale non esiste controllo quella dopo
		else
			diagonale = true;

		// diagonale bassa da sx a dx

		if (libreria[5][0] != null)
			color = GUI.getColor(libreria[5][0].getColore());
		else
			diagonale = false;

		for (int i = 4; i > 0 && diagonale; i--) {

			if (libreria[i][5 - i] != null) {
				if (!GUI.getColor(libreria[i][5 - i].getColore()).equals(color))
					diagonale = false;
			} else
				diagonale = false;
		}
		// se la diagonale esiste
		if (diagonale)
			return true;
		// se la diagonale non esiste controllo quella dopo
		else
			diagonale = true;

		if (libreria[5][4] != null)
			color = GUI.getColor(libreria[5][4].getColore());
		else
			diagonale = false;

		for (int i = 4; i > 0 && diagonale; i--) {
			if (libreria[i][i - 1] != null) {
				if (!GUI.getColor(libreria[i][i - 1].getColore()).equals(color))
					diagonale = false;
			} else
				diagonale = false;
		}
		// se la diagonale esiste
		if (diagonale)
			return true;

		return false;

	}

	public static boolean check8(TesseraOggetto[][] libreria) {
		ArrayList<Colore> colori = new ArrayList<Colore>();
		boolean piena = true;
		int righe = 0;
		for (int i = 0; i < 6; i++) {
			piena = true;
			for (int j = 0; j < 5 && piena; j++) {
				if (libreria[i][j] != null) {
					if (!colori.contains(libreria[i][j].getColore()))
						colori.add(libreria[i][j].getColore());
				} else
					piena = false;// se c'è una casella null so già che l'obiettivo non può essere completato da questa riga
			}

			// se la colonna è piena
			if (piena) {
				// se i colori dentro la riga sono al massimo 3 diversi
				if (colori.size() <= 3)
					// conto la colonna
					righe++;
			}
			// svuoto l'array che conta i colori della riga
			colori.clear();
		}
		// se ho almeno 4 righe che soddisfano torno true
		if (righe >= 4)
			return true;
		else
			return false;
	}

	public static boolean check9(TesseraOggetto[][] libreria) {
		int colonne = 0;
		boolean uguale;
		for (int j = 0; j < 5; j++) {
			uguale = true;
			for (int i = 0; i < 5; i++) {

				// se trovo una tessera null la colonna non può più realizzare l'obiettivo
				if (libreria[i][j] == null || libreria[i + 1][j] == null) {
					uguale = false;
					break;
				}

				if (GUI.getColor(libreria[i][j].getColore()).equals(GUI.getColor(libreria[i + 1][j].getColore()))) {
					// se trovo una tessera uguale la colonna è non valida
					uguale = false;
					break;
				}
			}
			if (uguale)
				colonne++; // se la riga è uguale
			if (colonne == 2)
				return true; // se ne ho trovate due posso fermarmi
		}
		return false;
	}

	public static boolean check10(TesseraOggetto[][] libreria) {
		int righe = 0;
		boolean uguale;
		for (int i = 0; i < 6; i++) {
			uguale = true;
			for (int j = 0; j < 4; j++) {
				// se trovo una tessera null non posso raggiungere l'obiettivo
				if (libreria[i][j] == null || libreria[i][j + 1] == null) {
					uguale = false;
					break;
				}

				if (GUI.getColor(libreria[i][j].getColore()).equals(GUI.getColor(libreria[i][j + 1].getColore()))) {
					// se trovo una tessera uguale la linea non è valida
					uguale = false;
					break;
				}
			}
			if (uguale)
				righe++; // se la riga è uguale
			if (righe == 2)
				return true; // se ne ho trovate due posso fermarmi
		}
		return false;
	}

	public static boolean check11(TesseraOggetto[][] libreria) {
		// parto direttamente da seconda riga e seconda colonna, prima non posso avere una X
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 4; j++) {
				if (libreria[i][j] != null) {

					// controllo se gli angoli non sono vuoti
					if (libreria[i - 1][j - 1] != null && libreria[i - 1][j + 1] != null
							&& libreria[i + 1][j - 1] != null && libreria[i + 1][j + 1] != null) {

						// se i colori sono uguali a quello che sto controllando
						if (GUI.getColor(libreria[i - 1][j - 1].getColore())
								.equals(GUI.getColor(libreria[i][j].getColore()))
								&& GUI.getColor(libreria[i - 1][j + 1].getColore())
										.equals(GUI.getColor(libreria[i][j].getColore()))
								&& GUI.getColor(libreria[i + 1][j - 1].getColore())
										.equals(GUI.getColor(libreria[i][j].getColore()))
								&& GUI.getColor(libreria[i + 1][j + 1].getColore())
										.equals(GUI.getColor(libreria[i][j].getColore()))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean check12(TesseraOggetto[][] libreria) {
		// controllo decrescente SX a DX
		if (countColonna(libreria, 0) - 1 == countColonna(libreria, 1)
				&& countColonna(libreria, 1) - 1 == countColonna(libreria, 2)
				&& countColonna(libreria, 2) - 1 == countColonna(libreria, 3)
				&& countColonna(libreria, 3) - 1 == countColonna(libreria, 4))
			return true;

		// controllo crescente da SX a DX
		if (countColonna(libreria, 0) + 1 == countColonna(libreria, 1)
				&& countColonna(libreria, 1) + 1 == countColonna(libreria, 2)
				&& countColonna(libreria, 2) + 1 == countColonna(libreria, 3)
				&& countColonna(libreria, 3) + 1 == countColonna(libreria, 4))
			return true;

		return false;
	}

	
	//uguale al metodo della classe "LIBRERIA"
	private static int hasAdiacente(int i, int j, TesseraOggetto[][] clone, char dir) {
		int c = 1;
		Color value = GUI.getColor(clone[i][j].getColore());
		clone[i][j] = null;

		if (i + 1 < 6 && dir != 'b') {
			if (clone[i + 1][j] != null) {
				if (GUI.getColor(clone[i + 1][j].getColore()).equals(value)) {
					c += hasAdiacente(i + 1, j, clone, 'a');
				}
			}
		}
		if (i - 1 >= 0 && dir != 'a') {
			if (clone[i - 1][j] != null) {
				if (GUI.getColor(clone[i - 1][j].getColore()).equals(value)) {
					c += hasAdiacente(i - 1, j, clone, 'b');
				}
			}
		}
		if (j + 1 < 5 && dir != 'd') {
			if (clone[i][j + 1] != null) {
				if (GUI.getColor(clone[i][j + 1].getColore()).equals(value)) {
					c += hasAdiacente(i, j + 1, clone, 's');
				}
			}
		}
		if (j - 1 >= 0 && dir != 's') {
			if (clone[i][j - 1] != null) {
				if (GUI.getColor(clone[i][j - 1].getColore()).equals(value)) {
					c += hasAdiacente(i, j - 1, clone, 'd');
				}
			}
		}

		return c;
	}

	
	/**
	 * metodo per il conteggio di quante caselle sono occupate nella singola colonna
	 * @param riga le righe della libreria
	 * @param colonna la colonna da controllare
	 * @return il numero di caselle occupate
	 */
	private static int countColonna(TesseraOggetto[][] riga, int colonna) {
		int count = 0;
		for (int i = 5; i >= 0; i--) {
			if (riga[i][colonna] != null) {
				count++;
			} else
				return count; // se trovo un null al di sopra non posso avere altre tessere
		}
		return count;

	}
	
	/**
	 * metodo che richiama i controlli sugli obiettivi corrispondenti all'obiettivo da controllare
	 * @param tessera_obiettivo indica l'obiettivo da controllare
	 * @param libreria indica la libreria del giocatore che va controllata
	 * @return true o false a seconda che l'obiettivo sia stato raggiunto
	 */
	public static boolean ControlloObiettivoRaggiunto(TesseraObiettivoComune tessera_obiettivo, TesseraOggetto[][] libreria) { 

		ObiettivoComune obiettivo = tessera_obiettivo.obiettivo;
		switch (obiettivo) {

		case Obiettivo1:
			// 6 gruppi da 2
			return check1(libreria);

		case Obiettivo2:
			// 4 tessere dello stesso tipo ai 4 angoli della libreria
			return check2(libreria);

		case Obiettivo3:
			// 4 gruppi (separati) di 4 tessere adiacenti dello stesso tipo (gruppi anche tipi diversi)
			return check3(libreria);

		case Obiettivo4:
			// 2 quadrati (separati) di 4 tessere dello stesso tipo anche i quadrati dello stesso tipo
			return check4(libreria);

		case Obiettivo5:
			// 3 colonne di 6 tessere di max 3 tipi diversi (colonne possono avere combinazioni di tipi diverse)
			return check5(libreria);

		case Obiettivo6:
			// 8 tessere dello stesso tipo
			return check6(libreria);

		case Obiettivo7:
			// 5 tessere dello stesso tipo che formano una diagonale;
			return check7(libreria);

		case Obiettivo8:
			// 4 righe di 5 tessere di max 3 tipi diversi (righe possono avere combinazioni di tipi diverse)
			return check8(libreria);

		case Obiettivo9:
			// 2 colonne ciascuna di 6 tipi diversi
			return check9(libreria);

		case Obiettivo10:
			// 2 righe ciascuna di 5 tipi diversi
			return check10(libreria);

		case Obiettivo11:
			// 5 tessere dello stesso tipo messe a X
			return check11(libreria);

		case Obiettivo12:
			// 5 colonne di altezza crescente/decrescente con tessere di qualsiasi tipo
			return check12(libreria);
		}
		return true;
	}

}
