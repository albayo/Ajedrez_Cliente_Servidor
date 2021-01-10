package Juego;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Piezas.Alfil;
import Piezas.Caballo;
import Piezas.Dama;
import Piezas.Peon;
import Piezas.Pieza;
import Piezas.Posicion;
import Piezas.Rey;
import Piezas.Torre;

public class Juego implements Serializable{
	private Tablero t;
	private boolean turno;//TRUE=BLANCAS FALSE=NEGRAS
	private boolean b00,b000,n00,n000;	//EST0S BOOLEANS SON PARA EL ENROQUE CORTO Y LARGO
	public Juego() {
		this.t=new Tablero();
		this.turno=true;
		this.b00=true;
		this.b000=true;
		this.n00=true;
		this.n000=true;
	}
	//EST0S BOOLEANS SON PARA EL ENROQUE CORTO Y LARGO
	public boolean getb00(){
		return this.b00;
	}
	public boolean getb000() {
		return this.b000;
	}
	public boolean getn00() {
		return this.n00;
	}
	public boolean getn000() {
		return this.n000;
	}
	public Tablero getTablero() {
		return this.t;
	}
	public void posicionInicial() {
		for(int i=0;i<8;i++) {
			t.cambiarPieza(new Peon(new Posicion(6,i),false),new Posicion(6,i));
			t.cambiarPieza(new Peon(new Posicion(1,i),true),new Posicion(1,i));
		}
		t.cambiarPieza(new Torre(new Posicion (0,0),true), new Posicion(0,0));
		t.cambiarPieza(new Torre(new Posicion (0,7),true), new Posicion(0,7));
		t.cambiarPieza(new Torre(new Posicion (7,0),false), new Posicion(7,0));
		t.cambiarPieza(new Torre(new Posicion (7,7),false), new Posicion(7,7));

		t.cambiarPieza(new Caballo(new Posicion (0,1),true), new Posicion(0,1));
		t.cambiarPieza(new Caballo(new Posicion (0,6),true), new Posicion(0,6));
		t.cambiarPieza(new Caballo(new Posicion (7,1),false), new Posicion(7,1));
		t.cambiarPieza(new Caballo(new Posicion (7,6),false), new Posicion(7,6));


		t.cambiarPieza(new Alfil(new Posicion (0,2),true), new Posicion(0,2));
		t.cambiarPieza(new Alfil(new Posicion (0,5),true), new Posicion(0,5));
		t.cambiarPieza(new Alfil(new Posicion (7,2),false), new Posicion(7,2));
		t.cambiarPieza(new Alfil(new Posicion (7,5),false), new Posicion(7,5));

		t.cambiarPieza(new Rey(new Posicion (0,3),true), new Posicion(0,3));
		t.cambiarPieza(new Dama(new Posicion (0,4),true), new Posicion(0,4));
		t.cambiarPieza(new Rey(new Posicion (7,3),false), new Posicion(7,3));
		t.cambiarPieza(new Dama(new Posicion (7,4),false), new Posicion(7,4));
	}

	public void cambiarTurno() {
		this.turno=!this.turno;
	}

	public boolean estarEnJaque(boolean e) {//ESTAS EN JAQUE
		boolean b=false;
		Posicion p=t.getRey(e);
		List<Pieza> l=t.getPiezasColor(!e);
		int j=l.size();
		for(int i=0;i<j && b==false;i++) {
			if(l.get(i).movimientoPosible(p, t)) b=true;
		}
		return b;
	}

	public List<Posicion> movimientosPosiblesPieza(Pieza p){
		List<Posicion> l=new ArrayList<>();
		boolean [][] b=p.movimientosPosibles(t);
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(b[i][j]==true && !movimientoAJaque(t.getCasilla(p.getPosicion().getNum(), p.getPosicion().getLetra()),t.getCasilla(i, j))) {
					l.add(new Posicion(i,j));
				}
			}
		}
		return l;
	}

	public boolean movimientoAJaque (Casilla actual, Casilla destino){//TRUE SI ILEGAL, FALSO OKKK.

		Pieza piezaAMover = actual.getPieza();
		Pieza piezaAReemplazar = destino.getPieza();
		Posicion posActual = new Posicion(actual.getNum(),actual.getLetra());
		Posicion posDestino = new Posicion(destino.getNum(),destino.getLetra());
		boolean movimientoAJaque = false;


		if(piezaAMover.mover(posDestino, t)){


			if (estarEnJaque(piezaAMover.getEquipo())) {
				movimientoAJaque = true;
			}

			piezaAMover.setPosicion(posActual);
			piezaAReemplazar.setPosicion(posDestino);
			actual.setPieza(piezaAMover);
			destino.setPieza(piezaAReemplazar);

		}
		return movimientoAJaque;
	}

	public boolean mate(boolean e) {
		if(estarEnJaque(e)) {
			List<Pieza> l=this.getTablero().getPiezasColor(e);

			for(Pieza p:l) {
				if(this.movimientosPosiblesPieza(p).size()!=0) {
					return false;
				}

			}
			return true;
		}
		return false;
	}

	public boolean ahogado(boolean e) {
		if(!estarEnJaque(e)) {
			List<Pieza> l=this.getTablero().getPiezasColor(e);

			for(Pieza p:l) {
				if(this.movimientosPosiblesPieza(p).size()!=0) {
					return false;
				}

			}
			return true;
		}
		return false;

	}
	public void partida() {
		while(!mate(this.turno)) {

		}
	}

	public boolean moverPieza(Pieza p,Posicion nueva) {
		List<Posicion>l=this.movimientosPosiblesPieza(p);
		Posicion vieja=p.getPosicion();
		for(Posicion po:l) {
			if(po.equals(nueva)) {

				if(p.mover(nueva, t)) {
					if(p instanceof Peon && (nueva.getNum()==0 || nueva.getNum()==7)) {
						this.t.cambiarPieza(new Dama(nueva,p.getEquipo()), nueva);
					}
					if(p instanceof Rey) {

						if(p.getEquipo()==true) { 
							b00=false; 
							b000=false;

						}
						else { n00=false; n000=false;}
					}
					if(p instanceof Torre) {
						if(p.getEquipo()==true) {
							if(vieja.getLetra()==0) b00=false;
							if(vieja.getLetra()==7) b000=false; 
						}else {
							if(vieja.getLetra()==0) n00=false;
							if(vieja.getLetra()==7) n000=false; 
						}

					}
					return true;
				}
			}
		}
		return false;		

	}


	public boolean getTurno() {
		return this.turno;
	}

}
