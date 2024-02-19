package grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import grafica.GUILibreria;
import myShelfie.Libreria;
import tessere.TesseraOggetto;

public class GUILibreria extends JFrame implements ActionListener {

	JButton[][] square;
	// int numerogiocatori;
	int size, x_now, y_now;
	String nome;
	Libreria libreria = new Libreria();
	JPanel panel_libreria;
	int x = 6, y = 5, counter = 0;
	ArrayList<TesseraOggetto> tessere;
	JButton button_now;
	int y_def = 0;
	
	/**
	 * crea la grafica della libreria del giocatore
	 * @param nome giocatore
	 */
	public GUILibreria(String nome) {
		super(nome);
		this.nome = nome;
		// numerogiocatori = n;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// libreria di dimensioni fisse
		square = new JButton[x][y];

		size = libreria.getLibreria().length;

		panel_libreria = new JPanel(new GridLayout(size, size));
		panel_libreria.setName(nome);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				//inserisco i bottoni nella libreria
				square[i][j] = new JButton();
				//imposto nome in modo da riconoscere la posizione
				square[i][j].setName(i + "," + j);
				
				square[i][j].addActionListener(this);
				//impostazioni grafiche
				square[i][j].setPreferredSize(new Dimension(80, 80));
				square[i][j].setBackground(new Color(120, 60, 0));
				panel_libreria.add(square[i][j]);
			}
		}
		//aggiungo il panel alla finestra
		add(panel_libreria);
		pack();
		setVisible(false);

	}
	/**
	 * imposta tutta la libreria a cliccabile
	 */
	public void setEnabled() {
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				square[i][j].setEnabled(true);
			}
		}
	}
	
	
	public void inserisci(ArrayList<TesseraOggetto> tessere) {
		this.tessere = (ArrayList<TesseraOggetto>) tessere.clone();
		counter = 0;
	}
	
	boolean gioco = true;
	boolean prese = false;
	@Override
	public void actionPerformed(ActionEvent e) {
		button_now = (JButton) e.getSource();
		String name = button_now.getName();
		x_now = Integer.parseInt(name.split(",")[0]);
		y_now = Integer.parseInt(name.split(",")[1]);
		prese = false;
		if (GUISceltaColore.tmp != null) {
			if (checkPosition() && counter < tessere.size()) {// se sotto la tessera c'è un blocco solido
				// posso inserire le tessere solo in una colonna per turno
				if (counter == 0)
					y_def = y_now;
				if (y_def == y_now) {

					// NON DEVO PRENDERLE IN ORDINE MA DEVO PRENDERE IL COLORE CHE HO PASSATO
					libreria.addTessera(x_now, y_now, GUISceltaColore.tmp);
					//impostazioni grafiche
					button_now.setBackground(GUI.getColor(GUISceltaColore.tmp.getColore()));
					button_now.setIcon(new ImageIcon(GUISceltaColore.tmp.getPath()));
					//tolgo possibilità di ricliccare una casella già cliccata
					button_now.removeActionListener(this);
					square[x_now][y_now].setIcon(new ImageIcon(GUISceltaColore.tmp.getPath()));
					square[x_now][y_now].removeActionListener(this);
					square[x_now][y_now].setBackground(GUI.getColor(GUISceltaColore.tmp.getColore()));
					//-----------------------------
					counter++;
					//rimuovo la tessera scelta
					GUISceltaColore.tmp = null;
					//tolto possibilità di cliccare la tessera appena scelta dal pannello scelta
					GUISceltaColore.tessera.removeActionListener(this);
					//se le ho inserite tutte lo notifico
					if(counter == tessere.size()) prese = true;
				}
			}
		}
	}

	int max = 0;
	int totlibere = 0;
	
	/**
	 * ritorna il pannello della libreria e imposta variabile per numero di tessere libere
	 * @param c
	 * @return
	 */
	public JPanel getPanel(int c) {
		setEnabled();
		int counter = 0;
		totlibere = 0;
		max = 0;
		for(int j = 0; j < 5; j++) {
			counter = 0;
			for(int i = 0; i < 6; i++) {
				if(square[i][j].getIcon() == null) {
					counter++;
					totlibere++;
				}
					
			}
			if(counter > max) max = counter;
			if(counter < c) {
				disabilitaColonna(j);
			}
		}
		return panel_libreria;
	}
	
	/**
	 * toglie la possibilità di cliccare i bottoni se la colonna ha meno spazio delle tessere raccolte
	 * @param y
	 */
	private void disabilitaColonna(int y) {
		for(int i = 0; i < 6; i++) {
			if(square[i][y].getIcon() == null)
				square[i][y].setEnabled(false);
		}
	}
	
	/**
	 * controlla la posizione della tessera
	 */
	private boolean checkPosition() {
		//se sono ultimo
		if (x_now + 1 > 5)
			return true;
		if (libreria.getTessera(x_now + 1, y_now) != null)
			return true;
		return false;
	}


	public Libreria getLibreria() {
		return libreria;
	}

}
