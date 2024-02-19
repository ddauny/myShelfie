package myShelfie;

import java.util.ArrayList;

import myShelfie.Turno;

//gestisce l'avanzamento dei turni
public class Turno {
	static ArrayList<Giocatore> giocatori; 
	
	/**
	 * crea array grande quanto il numero dei giocatori della partita
	 * @param numero dei giocatori
	 */
	public Turno(int numero) {
		giocatori = new ArrayList<Giocatore>(numero);
	}
	
	/**
	 * aggiunge un giocatore ad un array che contiene tutti i giocatori
	 * @param g
	 */
	static public void addGiocatore(Giocatore g) {
		giocatori.add(g);
	}
	
	/**
	 * prende il giocatore dall'array di giocatori
	 * @return giocatore
	 */
	static public Giocatore getGiocatore(int i) {
		return giocatori.get(i);
	}
}
