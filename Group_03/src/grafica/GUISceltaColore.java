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

import tessere.TesseraOggetto;

public class GUISceltaColore extends JFrame implements ActionListener {
	JButton[] line;
	JPanel panel_scelta;
	int size = 3;
	static ArrayList<TesseraOggetto> tessere;
	JButton button_now;
	
	/**
	 * crea nella grafica la parte dove verranno inserite le tesssere scelta dal giocatore per inserirle nella libreria
	 */
	public GUISceltaColore() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//linea che contiene bottoni con tessere raccolte
		line = new JButton[size];// di dimensioni fisse

		panel_scelta = new JPanel(new GridLayout(1, 3));
		panel_scelta.setName("Scelta colore");
		for (int i = 0; i < size; i++) {
			//inserisco bottoni alla finestra
			line[i] = new JButton();
			line[i].setName(i + "");
			line[i].addActionListener(this);
			
			//grafica
			line[i].setPreferredSize(new Dimension(80, 80));
			line[i].setBackground(new Color(0, 0, 0));
			line[i].setEnabled(false);
			panel_scelta.add(line[i]);
		}
		//inserisco pannella nella finestra
		add(panel_scelta);
		pack();
		setVisible(false);
	}

	static TesseraOggetto tmp;
	
	/**
	 * inserisce nella finestra degli oggetti del giocatore le tessere che il giocatore ha scelto dalla plancia
	 * @param tessere
	 */
	public void inserisci(ArrayList<TesseraOggetto> tessere) {
		//clono le tessere che sono state selezionate
		this.tessere = (ArrayList<TesseraOggetto>) tessere.clone();
		
		//svuoto la finestra per poi aggiornarla
		for (int i = 0; i < 3; i++) {		
			line[i].setIcon(null);
			line[i].setBackground(new Color(0, 0, 0));
		}
		for (int i = 0; i < tessere.size(); i++) {
			//inserisco le immagini e le funzionalità del click
			line[i].addActionListener(this);
			line[i].setEnabled(true);
			line[i].setIcon(new ImageIcon(tessere.get(i).getPath()));
			line[i].setBackground(GUI.getColor(tessere.get(i).getColore()));
		}
		tmp = null;
		inserita = true;
	}

	static boolean inserita = true;
	static JButton tessera;

	@Override
	public void actionPerformed(ActionEvent e) {
		//recupero il bottone che ha scatenato l'evento
		button_now = (JButton) e.getSource();
		String name = button_now.getName();
		int i = Integer.parseInt(name);
		
		//copio colore della tessera selezionata
		tmp = tessere.get(i);
		tessera = line[i];
	}

	public JPanel getPanel() {
		return panel_scelta;
	}

}
