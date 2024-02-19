/* Crea il campo da gioco partendo da una matrice 6x5
Controlla il giocatore vincitore e aggiunge i relativi punti al mio Obbiettivo personale
Controlla gli Obbiettivi comuni e somma i punti ottenuti dall'obbiettivo comune
Aggiunge il punteggio all'Obbiettivo personale
*/


package myShelfie;

import java.util.ArrayList;

import tessere.TesseraObiettivoPersonale.TipoObiettivo;

import myShelfie.Game;
import tessere.TesseraObiettivoComune;
import tessere.TesseraOggetto;
import tessere.TesseraObiettivoPersonale;

public class Game {

	// Array contenente le Tessere Obbiettivo Comune, selezionate per il gioco
	public static ArrayList<TesseraObiettivoComune> obiettivi_comuni = new ArrayList<TesseraObiettivoComune>();

	/**
	 * controlla che giocatore ha vinto la partita
	 * @return nome del vincitore
	 */
	public static String END() { 
		String nome_vincitore = "";
		int max = 0;
		for (Giocatore g : Turno.giocatori) {
			// Vengono aggiunti i punti relativi al mio Obiettivo Personale
			addObiettivoPersonale(g); 
			
			//Vengono aggiunti al giocatore i punti per i gruppi di tessere creati
			g.PunteggioGiocatore += Libreria.countPunti(g.getLibreria().getLibreria());

			System.out.println("Punteggio di " + g.getNome() + ": " + g.PunteggioGiocatore);
			
			// In caso di pareggio, il maggiore uguale (>=), prenderà l'ultimo giocatore
			if (g.PunteggioGiocatore >= max) {
				max = g.PunteggioGiocatore;
				nome_vincitore = g.getNome();
			}
		}
		//ritorno nome di chi ha vinto la partita
		return nome_vincitore;
	}

	/**
	 * controlla gli obiettivi comuni
	 * @param giocatore, n
	 */
	public static void TURNO(Giocatore g, int n) {		
		checkObiettivoComune(g, n);
	}
	
	/**
	 * mostra l'obiettivo personale del giocatore
	 * @param giocatore
	 * @return obiettivo personale
	 */
	public static int ShowObiettivo(Giocatore g) {
		return TipoObiettivo.getNumeroObiettivo(g.obiettivo_personale);
	}

	/**
	 * controlla se sono stati raggiunti gli obiettivi comuni
	 * @param giocatore
	 * @param n
	 */
	private static void checkObiettivoComune(Giocatore g, int n) {
		//ciclo su tutti gli obiettivi comuni
		for (TesseraObiettivoComune o : obiettivi_comuni) {
			// Se l'obbiettivo comune viene soddisfatto e, non è già stato preso nei turni
			// precedenti DALLO STESSO GIOCATORE, risulterà TRUE
			if (TesseraObiettivoComune.ControlloObiettivoRaggiunto(o, g.getLibreria().getLibreria().getLibreria())
					&& !g.getObiettivi_comuni_completati().contains(o)) {

				//sommo i punti ottenuti dall'obiettivo comune
				g.PunteggioGiocatore += o.getPoints(n);
				System.out.println("Assegnato punteggio obiettivo comune a " + g.getNome());
				
				//inserisco al giocatore l'obiettivo comune completato in modo da non contarlo più volte
				g.obiettivoComuneCompletato(o);
			}
		}
	}

	
	/**
	 * aggiunge al punteggio del giocatore il punteggio ottenuto dall'obiettivo personale 
	 * @param giocatore
	 */
	private static void addObiettivoPersonale(Giocatore giocatore) {
		giocatore.PunteggioGiocatore += TesseraObiettivoPersonale.controlloObiettivoPersonale(giocatore);
	}

	
	/**
	 * conta il massimo numero di tessere raccoglibile (in base a quante caselle della stessa colonna della libreria sono rimaste libere)
	 * @param giocatore
	 * @return il numero di tessere
	 */
	public static int spazioLibreria(Giocatore g) {
		TesseraOggetto[][] libreria = g.getLibreria().getLibreria().getLibreria();
		int c = 0;
		int max = 0;
		for (int x = 0; x < 5; x++) {
			c = 0;
			for (int y = 0; y < 6; y++) {
				if (libreria[y][x] == null)
					c++;
				if (c > max)
					max = c;
			}
		}
		return max;
		// Otteniamo il numero più alto di spazi vuoti presenti sulla colonna
	}

}
