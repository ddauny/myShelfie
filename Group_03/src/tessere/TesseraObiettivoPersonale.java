package tessere;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import grafica.GUI;
import myShelfie.Giocatore;

public class TesseraObiettivoPersonale {
	enum Colore {
		BLUE, GREEN, YELLOW, WHITE, RED, LIGHTBLUE;
	}

	public String img = "/img";

	/**
	 * enumerativo degli obiettivi personali, con random degli obiettivi personali da assegnare
	 *
	 */
	public enum TipoObiettivo {
		ObiettivoPersonale1, ObiettivoPersonale2, ObiettivoPersonale3, ObiettivoPersonale4, ObiettivoPersonale5,
		ObiettivoPersonale6, ObiettivoPersonale7, ObiettivoPersonale8, ObiettivoPersonale9, ObiettivoPersonale10,
		ObiettivoPersonale11, ObiettivoPersonale12;

		// assegna in modo casuale al giocatore un obiettivo
		private static final TipoObiettivo[] obiettivo = TipoObiettivo.values();
		private static final Random ObiettivoCasuale = new Random();

		private static ArrayList<TipoObiettivo> gia_usciti = new ArrayList<TipoObiettivo>();
		
		/**
		 * prende in una lista tutti gli obiettivi personali
		 * @return lista obiettivi personali
		 */
		public static TipoObiettivo getObiettivo(int i) {
			return TipoObiettivo.obiettivo[i];
		}
		
		public static int getNumeroObiettivo(TipoObiettivo o) {
			return TipoObiettivo.valueOf(o.name()).ordinal() + 1;
		}
		
		/**
		 * random degli obiettivi che verranno assegnati ai giocatori 
		 * @return tessera da assegnare
		 */
		public static TipoObiettivo casuale() {
			TipoObiettivo[] obiettivo = values();
			TipoObiettivo tmp = obiettivo[ObiettivoCasuale.nextInt(obiettivo.length)];
			// finchè non ne esce uno nuovo continua a farne uscire
			if (!gia_usciti.contains(tmp)) {
				gia_usciti.add(tmp);
				return tmp;
			} else
				return casuale();
		}
	}

	/**
	 * converte enum Colore in una classe Color
	 * @return colore
	 */
	private static Color getColor(Colore color) {
		String colore = color.name();
		Color c = new Color(255, 255, 255);
		switch (colore) {
		case "RED":
			c = new Color(255, 0, 0);
			break;
		case "GREEN":
			c = new Color(0, 255, 0);
			break;
		case "BLUE":
			c = new Color(0, 0, 255);
			break;
		case "YELLOW":
			c = new Color(255, 255, 20);
			break;
		case "WHITE":
			c = new Color(255, 255, 255);
			break;
		case "LIGHTBLUE":
			c = new Color(51, 153, 255);
			break;
		}
		return c;
	}

	/**
	 * controllo se gli obiettivi personali vengono raggiunti
	 * @param giocatore
	 * @return punteggio raggiunto dal giocatore
	 */
	public static int controlloObiettivoPersonale(Giocatore g) {
		//salvo libreria del giocatore
		TesseraOggetto LibreriaGiocatore[][] = g.getLibreria().getLibreria().getLibreria();
		
		//salvo obiettivo del giocatore
		TipoObiettivo ObiettivoPersonale = g.getObiettivoPersonale();
		int count = 0;
		switch (ObiettivoPersonale) {
		case ObiettivoPersonale1:
			if (LibreriaGiocatore[0][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][0].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[0][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][2].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[1][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][4].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[2][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][3].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][1].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[5][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][2].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}

			break;

		case ObiettivoPersonale2:
			if (LibreriaGiocatore[1][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][1].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[2][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][0].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[2][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][2].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[3][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][4].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][3].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[5][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][4].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}

			break;

		case ObiettivoPersonale3:
			if (LibreriaGiocatore[1][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][0].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[1][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][3].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[2][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][2].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[3][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][1].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[3][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][4].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[5][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][0].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}

			break;

		case ObiettivoPersonale4:
			if (LibreriaGiocatore[0][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][4].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[2][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][0].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[2][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][2].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][3].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[4][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][4].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][2].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}

			break;

		case ObiettivoPersonale5:
			if (LibreriaGiocatore[1][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][1].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][1].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][2].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][4].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[5][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][0].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[5][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][3].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}

			break;

		case ObiettivoPersonale6:
			if (LibreriaGiocatore[0][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][2].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[0][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][4].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[2][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][3].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][1].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[4][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][3].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[5][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][0].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}

			break;

		case ObiettivoPersonale7:
			if (LibreriaGiocatore[0][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][0].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[1][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][3].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[2][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][1].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[3][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][0].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][4].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[5][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][2].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}

			break;

		case ObiettivoPersonale8:
			if (LibreriaGiocatore[0][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][4].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[1][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][1].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[2][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][2].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][0].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[4][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][3].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[5][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][3].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}

			break;

		case ObiettivoPersonale9:
			if (LibreriaGiocatore[0][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][2].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[2][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][2].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[3][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][4].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][1].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][4].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[5][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][0].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}

			break;

		case ObiettivoPersonale10:
			if (LibreriaGiocatore[0][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][4].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[1][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][1].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[2][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][0].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][3].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[4][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][1].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[5][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][3].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}

			break;

		case ObiettivoPersonale11:
			if (LibreriaGiocatore[0][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][2].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[1][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][1].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[2][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][0].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[3][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][2].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][4].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}
			if (LibreriaGiocatore[5][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][3].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}

			break;

		case ObiettivoPersonale12:
			if (LibreriaGiocatore[0][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[0][2].getColore()).equals(getColor(Colore.WHITE)))) {
					count++;
				}
			if (LibreriaGiocatore[1][1] != null)
				if ((GUI.getColor(LibreriaGiocatore[1][1].getColore()).equals(getColor(Colore.RED)))) {
					count++;
				}
			if (LibreriaGiocatore[2][2] != null)
				if ((GUI.getColor(LibreriaGiocatore[2][2].getColore()).equals(getColor(Colore.BLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[3][3] != null)
				if ((GUI.getColor(LibreriaGiocatore[3][3].getColore()).equals(getColor(Colore.LIGHTBLUE)))) {
					count++;
				}
			if (LibreriaGiocatore[4][4] != null)
				if ((GUI.getColor(LibreriaGiocatore[4][4].getColore()).equals(getColor(Colore.YELLOW)))) {
					count++;
				}
			if (LibreriaGiocatore[5][0] != null)
				if ((GUI.getColor(LibreriaGiocatore[5][0].getColore()).equals(getColor(Colore.GREEN)))) {
					count++;
				}

			break;

		}

		//converte il numero di tessere nella posizione corretta nel punteggio che viene poi assegnato
		switch (count) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 4;
		case 4:
			return 6;
		case 5:
			return 9;
		case 6:
			return 12;
		//non dovrei mai arrivare qua
		default:
			return 0;
		}
	}
}
