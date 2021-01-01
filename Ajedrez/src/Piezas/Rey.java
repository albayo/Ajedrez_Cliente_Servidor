package Piezas;
import Juego.Tablero;

public class Rey extends Pieza{

	public Rey(Posicion p,boolean e) {
		super(e,p);
	}
	
	public boolean[][] movimientos() {
		Posicion p=this.getPosicion();
		boolean[][] b=new boolean[8][8];
		//4 ifs para los dos lados, arriba y abajo
		if(p.getNum()<7) {
			b[p.getNum()+1][p.getLetra()]=true;
		}
		if(p.getNum()>0) {
			b[p.getNum()-1][p.getLetra()]=true;
		}
		if(p.getLetra()<7) {
			b[p.getNum()][p.getLetra()+1]=true;
		}
		if(p.getLetra()>0) {
			b[p.getNum()][p.getLetra()-1]=true;
		}
		
		//4 if para las cuatro diagonales
		if(p.getNum()+1<7 && p.getLetra()+1<7) {
			b[p.getNum()+1][p.getLetra()+1]=true;
		}
		if(p.getNum()+1<7 && p.getLetra()-1>0) {
			b[p.getNum()+1][p.getLetra()-1]=true;
		}
		if(p.getNum()-1>0 && p.getLetra()+1<7) {
			b[p.getNum()-1][p.getLetra()+1]=true;
		}
		if(p.getNum()-1>0 && p.getLetra()-1>0) {
			b[p.getNum()-1][p.getLetra()-1]=true;
		}
		//ENROQUE
		if(this.getEquipo()==true && p.getNum()==0 && p.getLetra()==3) {

			b[0][1]=true;
			b[0][5]=true;
			
		}
		if(this.getEquipo()==false && p.getNum()==7 && p.getLetra()==3) {
			b[7][1]=true;
			b[7][5]=true;
		}
		
		return b;
	}

	public boolean movimientoPosible(Posicion nuevaPosicion, Tablero t) {
		boolean[][] b=this.movimientos();
		
		if(nuevaPosicion.getLetra()==1 && nuevaPosicion.getNum()==0 && this.getPosicion().getLetra()==3) {
			if(!(t.piezaEnCasilla(new Posicion(0,0)) instanceof Torre)) {
				return false;
			}
		}
		else if(nuevaPosicion.getLetra()==5 && nuevaPosicion.getNum()==0 && this.getPosicion().getLetra()==3) {
			if(!(t.piezaEnCasilla(new Posicion(0,7)) instanceof Torre)) {
				return false;
			}
		}
		else if(nuevaPosicion.getLetra()==1 && nuevaPosicion.getNum()==7 && this.getPosicion().getLetra()==3) {
			if(!(t.piezaEnCasilla(new Posicion(7,0)) instanceof Torre)) {
				return false;
			}
		}
		else if(nuevaPosicion.getLetra()==5 && nuevaPosicion.getNum()==7 && this.getPosicion().getLetra()==3) {
			if(!(t.piezaEnCasilla(new Posicion(7,7)) instanceof Torre)) {
				return false;
			}
		}
		
		if((t.piezaEnCasilla(nuevaPosicion) instanceof Vacia) || (b[nuevaPosicion.getNum()][nuevaPosicion.getLetra()]==true && t.piezaEnCasilla(nuevaPosicion).getEquipo()!=this.getEquipo())) {
			return true;
		}
	
		else
			return false;
	}
	

}
