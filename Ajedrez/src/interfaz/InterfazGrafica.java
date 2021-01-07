package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

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
import Piezas.Posicion;
import Piezas.Rey;
import Piezas.Torre;
import Piezas.Vacia;
import Servidor.Player;

public class InterfazGrafica extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Juego juego;
	private boolean movido;
	private Casilla[] casillaMov;
	private JButton[][] botones;
	private JFrame ventana=new JFrame("Ajedrez");
	
	public boolean getMovido() {
		return this.movido;
	}
	public void setMovido(boolean b) {
		this.movido=b;
	}
	public InterfazGrafica() {
		this.juego=new Juego();
		juego.posicionInicial();
		
		botones = new JButton[8][8];

		// SE CREA UN OYENTE DE ACCIONES Y SE LE PASA EL
		// PANEL COMO ARGUMENTO

		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones[i].length; j++) {
				botones[i][j] = new JButton();
				if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Vacia) {
//					botones[i][j].setEnabled(false);
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
				botones[i][j].setPreferredSize(new Dimension(60, 60));
				if ((i + j + 1) % 2 == 0) {
					botones[i][j].setBackground(Color.GRAY);
				}
				//botones[i][j].addMouseListener(oyente);
				botones[i][j].addMouseListener(new ManejadorDeClicks(juego.getTablero().getCasilla(i, j)));
					
					
			
				add(botones[i][j]);
			}
			setLayout(new GridLayout(8, 8));

		}

	}
	
	public boolean getTurno() {
		return this.juego.getTurno();
	}
	public void addClick() {
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones[i].length; j++) {
				botones[i][j].addMouseListener(new ManejadorDeClicks(juego.getTablero().getCasilla(i, j)));
			}
		}
	}
	
	public void ejecutar() {
		
		
		ventana.add(this);
		ventana.pack();
		ventana.setVisible(true);
		
	}

	private class ManejadorDeClicks implements MouseListener{
		private Casilla casilla;
		
		public ManejadorDeClicks(Casilla c) {
			this.casilla=c;	
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			boolean enroque;
			if(casillaMov == null){
                casillaMov = new Casilla[2];
                casillaMov[0] = this.casilla;
                casillaMov[1] = null;
                if(!(casillaMov[0].getPieza() instanceof Vacia) && casillaMov[0].getPieza().getEquipo()==juego.getTurno()) {
                	System.out.println("Primera casilla "+ casillaMov[0].getNum() 
                			+"," +  casillaMov[0].getLetra() );
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
//                	casillaMov[0].getPieza().imprimirPosicionesPosibles(juego.getTablero());
                }
                else {
                	System.out.println("Mueve una pieza de tu equipo");
                	casillaMov=null;
                }
                        
                
            } else if(casillaMov[1] == null){
                casillaMov[1] = this.casilla;
                System.out.println("Segunda casilla "+ casillaMov[1].getNum() 
                        +"," +  casillaMov[1].getLetra() );


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
                	if(!movido) {
                	enroque=true;
                	if(casillaMov[0].getPieza() instanceof Rey) {
              
                		if(juego.estarEnJaque(juego.getTurno()) && (casillaMov[1].getLetra()==1|| casillaMov[1].getLetra()==5)) {
                			enroque=false;
                		}
                		else if(casillaMov[1].getNum()==0 && casillaMov[1].getLetra()==1) {
                			if(juego.getb00()==false) enroque=false;
                			else if(juego.movimientoAJaque(casillaMov[0], juego.getTablero().getCasilla(0, 2))) enroque =false;
                		}
                		else if(casillaMov[1].getNum()==0 && casillaMov[1].getLetra()==5) {
                			if(juego.getb000()==false) enroque=false;
                			else if(juego.movimientoAJaque(casillaMov[0], juego.getTablero().getCasilla(0, 4))) enroque =false;
                		}
                		else if(casillaMov[1].getNum()==7 && casillaMov[1].getLetra()==1 ) {
                			if(juego.getn00()==false) enroque=false;
                			else if(juego.movimientoAJaque(casillaMov[0], juego.getTablero().getCasilla(7, 2))) enroque =false;
                		}
                		else if(casillaMov[1].getNum()==7 && casillaMov[1].getLetra()==5) {
                			if(juego.getn000()==false) enroque=false;
                			else if(juego.movimientoAJaque(casillaMov[0], juego.getTablero().getCasilla(7, 4))) enroque =false;
                		}
                		
                	}
                    if(juego.moverPieza(casillaMov[0].getPieza(), new Posicion(casillaMov[1].getNum(),casillaMov[1].getLetra()))) {
                    	if(enroque && casillaMov[1].getPieza() instanceof Rey) {
                    		
                    		if(casillaMov[1].getLetra()==1 && casillaMov[1].getNum()==0) {
                    			juego.moverPieza(juego.getTablero().getCasilla(0, 0).getPieza(), new Posicion(0,2));
                    			
                    		}
                    		if(casillaMov[1].getLetra()==5 && casillaMov[1].getNum()==0) {
                    			juego.moverPieza(juego.getTablero().getCasilla(0, 7).getPieza(), new Posicion(0,4));
                    			
                    		}
                    		if(casillaMov[1].getLetra()==1 && casillaMov[1].getNum()==7) {
                    			juego.moverPieza(juego.getTablero().getCasilla(7, 0).getPieza(), new Posicion(7,2));
                    			
                    		}
                    		if(casillaMov[1].getLetra()==5 && casillaMov[1].getNum()==7) {
                    			juego.moverPieza(juego.getTablero().getCasilla(7, 7).getPieza(), new Posicion(7,4));
                    			
                    		}
                    	}
                    	
                    	juego.cambiarTurno();
                    	pintarTablero(juego);
                    	
                    	
                    	if(juego.mate(juego.getTurno())) {
                       		if(juego.getTurno())
                       			System.out.println("GANAN NEGRAS");
                       		else
                       			System.out.println("GANAN BLANCAS");
                        }
                        else if(juego.estarEnJaque(juego.getTurno()))
                        	System.out.println("ESTAS EN JAQUE");
                        else if(juego.ahogado(juego.getTurno()))
                        	System.out.println("AHOGADO----TABLAS");
                    	movido=true;
                    }
                    else System.out.println("MUEVE BIEN");
                    casillaMov = null;                    
                	}  
                	
               } catch(RuntimeException ex){
                    System.out.println("¡Algo pasó!");
                }
            }
            
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public static void main(String[] args) {
				InterfazGrafica i=new InterfazGrafica();
				
					i.ejecutar();
					
				
	}
	
	public boolean fin() {
		return juego.mate(true) || juego.mate(false) || juego.ahogado(true) || juego.ahogado(false);
	}
	
	public JButton click(MouseEvent e) {
		return (JButton) e.getComponent();
	}
	
	public void pintarTablero(Juego juego) {
	//JButton[][] botones = new JButton[8][8];

	// SE CREA UN OYENTE DE ACCIONES Y SE LE PASA EL
	// PANEL COMO ARGUMENTO

	for (int i = 0; i < botones.length; i++) {
		for (int j = 0; j < botones[i].length; j++) {
//			botones[i][j] = new JButton();
			if(juego.getTablero().getCasilla(i, j).getPieza() instanceof Vacia) {
//				botones[i][j].setEnabled(false);
				botones[i][j].setIcon(null);
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

		}
	}
	}
}
