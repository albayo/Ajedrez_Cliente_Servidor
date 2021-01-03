package Piezas;
import java.io.Serializable;

import Juego.Tablero;

public class Dama extends Pieza implements Serializable{
	
	public Dama(Posicion p,boolean e) {
		super(e,p);
	}

	@Override
	public boolean[][] movimientos() {
		Alfil a=new Alfil(this.getPosicion(),false);
		Torre t=new Torre(this.getPosicion(),false);
		
		return juntarMatrices(a.movimientos(), t.movimientos());
		
	}

	@Override
	public boolean movimientoPosible(Posicion nueva, Tablero t) {
		boolean [][] b=this.movimientos();
		if(b[nueva.getNum()][nueva.getLetra()]==true && casillasIntermediasVacias(nueva, t)) {
			//MIRA A VER SI LA CASILLA ESTA VACÍA O ES DEL OTRO EQUIPO
			if(t.getTablero()[nueva.getNum()][nueva.getLetra()].getPieza() instanceof Vacia || t.getTablero()[nueva.getNum()][nueva.getLetra()].getPieza().getEquipo()!=this.getEquipo()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean casillasIntermediasVacias(Posicion nueva,Tablero t) {
		if(this.getPosicion().getNum()==nueva.getNum() || this.getPosicion().getLetra()==nueva.getLetra()) {
			Torre to=new Torre(this.getPosicion(),false);
			return to.casillasIntermediasVacias(nueva, t);
		}
		else {
			Alfil a=new Alfil(this.getPosicion(),false);
			return a.casillasIntermediasVacias(nueva, t);
		}
	}

}
