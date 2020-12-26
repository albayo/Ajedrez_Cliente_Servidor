package Piezas;
import Juego.Tablero;

public class Peon extends Pieza{
	
	public Peon(Posicion p,boolean e) {
		super(e,p);
	}

	@Override
	public boolean[][] movimientos() {
		boolean[][] b=new boolean[8][8];
		if(this.getEquipo()==true) {
			if(this.getPosicion().getNum()!=7) {
				if(this.getPosicion().getNum()==1) {
					b[this.getPosicion().getNum()+2][this.getPosicion().getLetra()]=true;
				}
				b[this.getPosicion().getNum()+1][this.getPosicion().getLetra()]=true;
			}
		}
		else {
			if(this.getPosicion().getNum()!=0) {
				if(this.getPosicion().getNum()==6) {
					b[this.getPosicion().getNum()-2][this.getPosicion().getLetra()]=true;
				}
				b[this.getPosicion().getNum()-1][this.getPosicion().getLetra()]=true;
			}
		}
		return this.juntarMatrices(b, this.movComer());
	}

	@Override
	public boolean movimientoPosible(Posicion nuevaPosicion, Tablero t) {
		if(this.movimientos()[nuevaPosicion.getNum()][nuevaPosicion.getLetra()]==true) {
			if(this.movComer()[nuevaPosicion.getNum()][nuevaPosicion.getLetra()]==true && !(t.piezaEnCasilla(nuevaPosicion) instanceof Vacia) && t.piezaEnCasilla(nuevaPosicion).getEquipo()!=this.getEquipo()) {
				return true;
			}
			else if(this.movComer()[nuevaPosicion.getNum()][nuevaPosicion.getLetra()]!=true && t.piezaEnCasilla(nuevaPosicion) instanceof Vacia) {
				return true;
			}
			else
				return false;
		}
		else return false;
	}
	
	public boolean[][] movComer(){
		boolean[][] b=new boolean[8][8];
		Posicion p=this.getPosicion();
		if(this.getEquipo()==true) {
			if(p.getNum()<7 && p.getLetra()<7) {
				b[p.getNum()+1][p.getLetra()+1]=true;
			}
			if(p.getNum()<7 && p.getLetra()>0) {
				b[p.getNum()+1][p.getLetra()-1]=true;
			}						
		}
		else {
			if(p.getNum()>0 && p.getLetra()<7) {
				b[p.getNum()-1][p.getLetra()+1]=true;
			}
			if(p.getNum()>0 && p.getLetra()>0) {
				b[p.getNum()-1][p.getLetra()-1]=true;
			}	
		}
		return b;
	}
	

}
