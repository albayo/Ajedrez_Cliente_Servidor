package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Juego.Casilla;
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
	private Casilla[] casillaMov;
	
	public InterfazGrafica(Juego ju) {
		this.juego=ju;
		ju.posicionInicial();
		
		JButton[][] botones = new JButton[8][8];

		// SE CREA UN OYENTE DE ACCIONES Y SE LE PASA EL
		// PANEL COMO ARGUMENTO

		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones[i].length; j++) {
				botones[i][j] = new JButton();
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Vacia) {
					botones[i][j].setEnabled(false);
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Peon) {
					if(juego.getTablero().getCasilla(i, j).getPieza().getEquipo()==true)
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/peonb.png"));
					else {
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/peonn.png"));
					}
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Torre) {
					//botones[i][j].setText("T");
					if(juego.getTablero().getCasilla(i, j).getPieza().getEquipo()==true)
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/torreb.png"));
					else {
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/torren.png"));
					}
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Alfil) {
					if(juego.getTablero().getCasilla(i, j).getPieza().getEquipo()==true)
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/alfilb.png"));
					else {
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/alfiln.png"));
					}
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Caballo) {
					if(juego.getTablero().getCasilla(i, j).getPieza().getEquipo()==true)
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/caballob.png"));
					else {
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/caballon.png"));
					}
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Rey) {
					if(juego.getTablero().getCasilla(i, j).getPieza().getEquipo()==true)
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/reyb.png"));
					else {
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/reyn.png"));
					}
				}
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Dama) {
					if(juego.getTablero().getCasilla(i, j).getPieza().getEquipo()==true)
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/reinab.png"));
					else {
						botones[i][j].setIcon(new ImageIcon("Ajedrez/src/reinan.png"));
					}
				}
				
				// SE CREAN LOS BOTONES Y SE ESTABLECE SU TAMAÑO PREFERIDO
				botones[i][j].setPreferredSize(new Dimension(50, 50));
				if ((i + j + 1) % 2 == 0) {
					botones[i][j].setBackground(Color.GRAY);
				}
				//botones[i][j].addMouseListener(oyente);
				botones[i][j].addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						click(e);
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				//asd
				add(botones[i][j]);
			}
			setLayout(new GridLayout(8, 8));

		}

	}
	
	private class ManejadorDeClicks implements MouseListener{
		private Casilla casilla;
		
		public ManejadorDeClicks(Casilla c) {
			this.casilla=c;	
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(casillaMov == null){
                casillaMov = new Casilla[2];
                casillaMov[0] = this.casilla;
                casillaMov[1] = null;
                System.out.println("Primera casilla "+ casillaMov[0].getPieza().getPosicion().getNum() 
                        +"," +  casillaMov[0].getPieza().getPosicion().getLetra() );
                System.out.println("Pieza: ");
                
                if(casillaMov[0].getPieza() instanceof Dama)
                    System.out.println("Reina");
                if(casillaMov[0].getPieza() instanceof Vacia)
                    System.out.println("NoPieza");
                if(casillaMov[0].getPieza() instanceof Rey)
                    System.out.println("Rey");
                if(casillaMov[0].getPieza() instanceof Peon)
                    System.out.println("Peon");
                if(casillaMov[0].getPieza() instanceof Caballo)
                    System.out.println("Caballo");
                if(casillaMov[0].getPieza() instanceof Torre)
                    System.out.println("Torre");
                if(casillaMov[0].getPieza() instanceof Alfil)
                    System.out.println("Alfil");
                casillaMov[0].getPieza().imprimirPosicionesPosibles();
                        
                
            } else if(casillaMov[1] == null){
                casillaMov[1] = this.casilla;
                System.out.println("Segunda casilla "+ casillaMov[1].getPieza().getPosicion().getNum() 
                        +"," +  casillaMov[1].getPieza().getPosicion().getLetra() );


                if(casillaMov[1].getPieza() instanceof Dama)
                    System.out.println("Reina");
                if(casillaMov[1].getPieza() instanceof Vacia)
                    System.out.println("NoPieza");
                if(casillaMov[1].getPieza() instanceof Rey)
                    System.out.println("Rey");
                if(casillaMov[1].getPieza() instanceof Peon)
                    System.out.println("Peon");
                if(casillaMov[1].getPieza() instanceof Caballo)
                    System.out.println("Caballo");
                if(casillaMov[1].getPieza() instanceof Torre)
                    System.out.println("Torre");
                if(casillaMov[1].getPieza() instanceof Alfil)
                    System.out.println("Alfil");

                try{
                    juego.moverPieza(casillaMov[0].getPieza(), casillaMov[1].getPieza().getPosicion());
                    casillaMov = null;
                    pintarTablero(); //Implementar metodo para ir pintando el tablero cambiado
               } catch(RuntimeException ex){
                    System.out.println("¡Algo pasó!");
                }
            }

			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) {
				JFrame ventana=new JFrame("Ajedrez");
				InterfazGrafica i=new InterfazGrafica(new Juego());
				ventana.add(i);
				ventana.pack();
				ventana.setVisible(true);
				
			
	}
	
	public void click(MouseEvent e) {
		JButton b=(JButton) e.getComponent();
		
	}
	
	

}
