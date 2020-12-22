package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Juego.Juego;
import Piezas.Alfil;
import Piezas.Caballo;
import Piezas.Dama;
import Piezas.Peon;
import Piezas.Rey;
import Piezas.Torre;
import Piezas.Vacia;

public class InterfazGrafica extends JPanel{
	private Juego juego;
	
	public InterfazGrafica(Juego ju) {
		this.juego=ju;
		ju.posicionInicial();
		
		JButton[][] botones;
		botones = new JButton[8][8];

		// SE CREA UN OYENTE DE ACCIONES Y SE LE PASA EL
		// PANEL COMO ARGUMENTO
		
		Oyente oyente = new Oyente(this);

		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones[i].length; j++) {
				botones[i][j] = new JButton();
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Vacia) {
					botones[i][j].setEnabled(false);
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Peon) {
					botones[i][j].setText("P");
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Torre) {
					botones[i][j].setText("T");
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Alfil) {
					botones[i][j].setText("A");
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Caballo) {
					botones[i][j].setText("C");
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Rey) {
					botones[i][j].setText("R");
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Dama) {
					botones[i][j].setText("D");
				}
				
				// SE CREAN LOS BOTONES Y SE ESTABLECE SU TAMAÑO PREFERIDO
				botones[i][j].setPreferredSize(new Dimension(50, 50));
				if ((i + j + 1) % 2 == 0) {
					botones[i][j].setBackground(Color.BLACK);
				}
				
				botones[i][j].addMouseListener(oyente);
				add(botones[i][j]);
			}
			setLayout(new GridLayout(8, 8));

		}

	}
	
	public static void main(String[] args) {
				JFrame ventana=new JFrame("Ajedrez");
				InterfazGrafica i=new InterfazGrafica(new Juego());
				ventana.add(i);
				ventana.pack();
				ventana.setVisible(true);
				
			
	}
	
	

}
