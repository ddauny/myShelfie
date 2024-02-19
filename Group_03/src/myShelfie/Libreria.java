package myShelfie;

import tessere.TesseraOggetto;

import java.awt.Color;

import grafica.GUI;
import myShelfie.Libreria;

public class Libreria {

	private TesseraOggetto libreria[][];
	
	public Libreria() {
		libreria = new TesseraOggetto[6][5];
		//svuoto la libreria 
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				libreria[i][j] = null;
			}
		}
	}

	public Libreria(Libreria libreria) {
		this.libreria = libreria.getLibreria();
	}

	public TesseraOggetto[][] getLibreria() {
		return libreria;
	}

	public void setLibreria(TesseraOggetto[][] libreria) {
		this.libreria = libreria;
	}

	public void addTessera(int i, int j, TesseraOggetto tessera) {
		// Ci permette di aggiungere una tessera alla libreria in posizione i,j
		if (checkIndex(i, j))
			this.libreria[i][j] = tessera;
	}

	public TesseraOggetto getTessera(int i, int j) {
		// Otteniamo la tessera, nella libreria, in posizione i,j
		if (checkIndex(i, j))
			return libreria[i][j];
		else
			return null;
	}

	private boolean checkIndex(int i, int j) {
		// controllo che gli indici passati rispettino la grandezza
		// della Libreria
		if (i < 6 && j < 5)
			return true;
		return false;
	}

	static int vicini = 1;

	
	//conta i punti assegnati in base alla grandezza dei gruppi
	public static int countPunti(Libreria lib) {
		TesseraOggetto[][] cells = lib.getLibreria();

		int points = 0;
		TesseraOggetto[][] clone = cells.clone();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (cells[i][j] != null) {
					if (clone[i][j] != null) {

						// SE HO UNO ADIACENTE UGUALE A ME
						vicini = hasAdiacente(i, j, clone, 'n');
					}
				}
				points += getPoints(vicini);
				vicini = 1;
			}
		}
		return points;
	}

	private static int hasAdiacente(int i, int j, TesseraOggetto[][] clone, char dir) {
		int c = 1;
		Color value = GUI.getColor(clone[i][j].getColore());
		clone[i][j] = null;

		//CALCOLO DELLA GRANDEZZA DEL GRUPPO (RICORSIVA)
		
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

	//converto grandezza del gruppo in punteggio
	private static int getPoints(int vicini) {
		if (vicini < 3)
			return 0;
		switch (vicini) {
		case 3:
			return 2;
		case 4:
			return 3;
		case 5:
			return 5;
		default:
			return 8;
		}
	}
}
