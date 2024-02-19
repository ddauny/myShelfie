package myShelfie;

import java.util.ArrayList;

import grafica.GUILibreria;
import grafica.GUISceltaColore;
import tessere.TesseraObiettivoComune;
import tessere.TesseraObiettivoPersonale.TipoObiettivo;
import tessere.TesseraOggetto;

public class Giocatore {
	private String nome;
	public int PunteggioGiocatore = 0;
	public int quante = 0;
	private GUILibreria libreria;
	private GUISceltaColore scelta;
	TipoObiettivo obiettivo_personale; 
	//array che contiene le tessere degli obiettivi comuni completati dal giocatore
	private ArrayList<TesseraObiettivoComune> obiettivi_comuni_completati = new ArrayList<TesseraObiettivoComune>();
	//array che contiene le tessere raccolte durante il round
	private ArrayList<TesseraOggetto> tessere_raccolte = new ArrayList<TesseraOggetto>();
	
	/**
	 * costruttore di Giocatore, che dichiara il nome, il punteggio, la libreria e gli obiettivi del giocatore stesso
	 * @param nome
	 */
	public Giocatore(String nome) {
		this.nome = nome;
		this.PunteggioGiocatore = 0;
		libreria = new GUILibreria(nome);
		scelta = new GUISceltaColore();
		obiettivo_personale = TipoObiettivo.casuale();
		System.out.println("Assegnato a " + nome + " l'obiettivo numero: " + obiettivo_personale.getNumeroObiettivo(obiettivo_personale));
	}
	
	/**
	 * svuota le tessere raccolte ad ogni round
	 */
	public void svuotaRaccolte() {
		tessere_raccolte.clear();
	}
	
	/**
	 * aggiunge la tessera appena raccolta all'array
	 * @param tessera
	 */
	public void addTesseraRaccolta(TesseraOggetto t) {
		tessere_raccolte.add(t);
	}
	
	/**
	 * metodo get delle tessere raccolte
	 * @return tessera
	 */
	public TesseraOggetto getTesseraRaccolta(int i) {
		return tessere_raccolte.get(i);
	}

	public GUILibreria getLibreria() {
		return libreria;
	}
	
	public GUISceltaColore getFinestraScelta() {
		return scelta;
	}
	
	public ArrayList<TesseraObiettivoComune> getObiettivi_comuni_completati() {
		return obiettivi_comuni_completati;
	}
	
	/**
	 * aggiunge obiettivo comune appena completato alla lista
	 * @param obiettivo comune
	 */
	public void obiettivoComuneCompletato(TesseraObiettivoComune o) {
		obiettivi_comuni_completati.add(o);
	}
	
	/**
	 * metodo get dell'obiettivo personale
	 * @return obiettivo personale
	 */
	public TipoObiettivo getObiettivoPersonale() {
		return obiettivo_personale;
	}
	
	/**
	 * metodo get del nome del giocatore
	 * @return nome
	 */
	public String getNome() { 
		return nome;
	}
	
	/**
	 * assegna il nome
	 * @param nome
	 */
	public void setNome(String nome) { 
		this.nome = nome;
	}
	
	/** 
	 * restituisce il punteggio del giocatore
	 * @return punteggio
	 */
	public int getPunteggioGiocatore() { 
		return PunteggioGiocatore;
	}
	
	/**
	 * assegna un punteggio al giocatore
	 * @param PunteggioGiocatore
	 */
	public void setPunteggioGiocatore(int PunteggioGiocatore) {
		this.PunteggioGiocatore = PunteggioGiocatore;
	}

}
