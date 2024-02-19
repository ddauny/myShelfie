package myShelfie;

import java.util.HashMap;

import myShelfie.Plancia;

import tessere.TesseraOggetto;

/*Permette di creare la tabella per il punteggio delle tessere nella plancia
Permette di settare il campo da gioco in base al numero di giocatori
presenti, di base è 9x9 Nel caso in cui i giocatori siano solamente 2, il campo da gioco è 7x7
Imposta il campo da gioco in base al numero di giocatori
*/
public class Plancia implements Cloneable {

	private TesseraOggetto plancia[][];
	//hashmap per contare il numero di tessere uscite per ogni categoria 
	private HashMap<String, Integer> counter;

	public Plancia(int numeroGiocatori) {
		counter = new HashMap<String, Integer>();
		initPlancia(numeroGiocatori);
	}

	public Plancia(Plancia plancia, int numeroGiocatori) {
		this.plancia = plancia.getPlancia();
		counter = new HashMap<String, Integer>();
		initPlancia(numeroGiocatori);
	}
	
	public void initPlancia(int numeroGiocatori) {
		plancia = new TesseraOggetto[9][9];

		switch (numeroGiocatori) {
		case 2:
			plancia = new TesseraOggetto[7][7];
			initPlancia2();
			break;
		case 3:
			initPlancia3();
			break;
		case 4:
			initPlancia4();
			break;
		default:
			// fare qualcosa in caso di errore
			break;
		}
		riempiPlancia();
	}

	public TesseraOggetto[][] getPlancia() {
		return plancia;
	}

	/*
	 * ricevo posizione della tessera da cancellare
	 */
	public void setTesseraNull(int i, int j) {
		plancia[i][j] = null;
	}

	public void setPlancia(TesseraOggetto[][] plancia) {
		this.plancia = plancia;
	}

	public void riempiPlancia() {
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia.length; j++) {
				if (plancia[i][j] != null) {// se la posizione che sto guardando si può usare nella partita la riempio
					plancia[i][j] = newTessera();
				}
			}
		}
	}
	/*
	 * Viene creata la tessera e si controlla che il numero delle tessera salvate sia minore di 21.
	 * se nella plancia è già stato inserito il numero massimo di tessere di un tipo, allora si passa ad 
	 * un altro tipo.
	 */
	public TesseraOggetto newTessera() {
		
		TesseraOggetto tmp = new TesseraOggetto();// creo una tessera
		
		//salvo il tipo della tessera
		String name = tmp.getColore().name();
		
		//se non esiste vuol dire che è la prima di quel tipo
		if (counter.get(name) == null) {
			//sommo e ritorno
			counter.merge(tmp.getColore().name(), 1, Integer::sum);
			return tmp;
		} else if (counter.get(name) <= 21) {// conto quante ne ho già  inserite usando il MAP (dovrebbe essere più efficiente)
			counter.merge(tmp.getColore().name(), 1, Integer::sum);// aggiungo 1 al tipo di tessera che è uscito

			return tmp;// ritorno la tessera che verrà  inserita nella plancia
		} else {// se sono già  al massimo di tessere di quel tipo ne creo un'altra
			return newTessera();
		}
	}
	/*
	 * Viene riempita la plancia con tessere estratte casualmente, successivamente si cancellano le celle
	 * non utili al gioco 
	 */
	private void preInitPlancia() {
		// Problema dovuto al fatto che altrimenti tutte le celle della plancia sarebbero NULL
		for (int i = 0; i < plancia.length; i++) {
			for (int j = 0; j < plancia.length; j++) {
				plancia[i][j] = new TesseraOggetto(true);
			}
		}
	}
	/*
	 * Vengono inizializzate le plancie per formare il campo di gioco in base al numero di giocatori.
	 * inizialmente le celle vengono impostate come vuote=null.
	 */
	/*
	 * 
	 * 
	 * inizializzazione della plancia con 2 giocatori, si tolgono le celle non utili al gioco.
	 */
	private void initPlancia2() {
		preInitPlancia();
		plancia[0][0] = null;
		plancia[0][1] = null;
		plancia[0][4] = null;
		plancia[0][5] = null;
		plancia[0][6] = null;

		plancia[1][0] = null;
		plancia[1][1] = null;
		plancia[1][5] = null;
		plancia[1][6] = null;

		plancia[2][0] = null;

		plancia[4][6] = null;

		plancia[5][0] = null;
		plancia[5][1] = null;
		plancia[5][5] = null;
		plancia[5][6] = null;

		plancia[6][0] = null;
		plancia[6][1] = null;
		plancia[6][2] = null;
		plancia[6][5] = null;
		plancia[6][6] = null;
	}
	/*
	 * inizializzazione della plancia con 3 giocatori, si tolgono le tessere non utili al gioco.
	 */
	private void initPlancia3() {
		preInitPlancia();
		plancia[0][0] = null;
		plancia[0][1] = null;
		plancia[0][2] = null;
		plancia[0][4] = null;
		plancia[0][5] = null;
		plancia[0][6] = null;
		plancia[0][7] = null;
		plancia[0][8] = null;

		plancia[1][0] = null;
		plancia[1][1] = null;
		plancia[1][2] = null;
		plancia[1][5] = null;
		plancia[1][6] = null;
		plancia[1][7] = null;
		plancia[1][8] = null;

		plancia[2][0] = null;
		plancia[2][1] = null;
		plancia[2][7] = null;
		plancia[2][8] = null;

		plancia[3][0] = null;
		plancia[3][1] = null;

		plancia[4][0] = null;
		plancia[4][8] = null;

		plancia[5][7] = null;
		plancia[5][8] = null;

		plancia[6][0] = null;
		plancia[6][1] = null;
		plancia[6][7] = null;
		plancia[6][8] = null;

		plancia[7][0] = null;
		plancia[7][1] = null;
		plancia[7][2] = null;
		plancia[7][3] = null;
		plancia[7][6] = null;
		plancia[7][7] = null;
		plancia[7][8] = null;

		plancia[8][0] = null;
		plancia[8][1] = null;
		plancia[8][2] = null;
		plancia[8][3] = null;
		plancia[8][4] = null;
		plancia[8][6] = null;
		plancia[8][7] = null;
		plancia[8][8] = null;
	}
	/*
	 * inizializzazione della plancia con 4 giocatori 
	 */
	private void initPlancia4() {
		preInitPlancia();
		plancia[0][0] = null;
		plancia[0][1] = null;
		plancia[0][2] = null;
		plancia[0][5] = null;
		plancia[0][6] = null;
		plancia[0][7] = null;
		plancia[0][8] = null;

		plancia[1][0] = null;
		plancia[1][1] = null;
		plancia[1][2] = null;
		plancia[1][6] = null;
		plancia[1][7] = null;
		plancia[1][8] = null;

		plancia[2][0] = null;
		plancia[2][1] = null;
		plancia[2][7] = null;
		plancia[2][8] = null;

		plancia[3][0] = null;

		plancia[5][8] = null;

		plancia[6][0] = null;
		plancia[6][1] = null;
		plancia[6][7] = null;
		plancia[6][8] = null;

		plancia[7][0] = null;
		plancia[7][1] = null;
		plancia[7][2] = null;
		plancia[7][6] = null;
		plancia[7][7] = null;
		plancia[7][8] = null;

		plancia[8][0] = null;
		plancia[8][1] = null;
		plancia[8][2] = null;
		plancia[8][3] = null;
		plancia[8][6] = null;
		plancia[8][7] = null;
		plancia[8][8] = null;
	}
}
