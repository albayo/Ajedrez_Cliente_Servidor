package Piezas;
import Juego.Tablero;

public class Caballo extends Pieza {
	
	public Caballo(Posicion p,boolean e) {
		super(e,p);
	}
	@Override
	public boolean[][] movimientos() {
		boolean [][] b=new boolean[8][8];
		int px=super.getPosicion().getNum();
		int py=super.getPosicion().getLetra();
		int i=0,j=0;
		if(px>1) {//2
			i=px-2;
			if(py!=0) {
				b[i][py-1]=true;
			}
			if(py!=7) {
				b[i][py+1]=true;
			}	
		}
		if(px<6) {//2
			i=px+2;
			if(py!=0) {
				b[i][py-1]=true;
			}
			if(py!=7) {
				b[i][py+1]=true;
			}	
		}
		if(py>1) {//2
			j=py-2;
			if(px!=0) {
				b[px-1][j]=true;
			}
			if(px!=7) {
				b[px+1][j]=true;
			}
		}
		if(py<6) {//2
			j=py+2;
			if(px!=0) {
				b[px-1][j]=true;
			}
			if(px!=7) {
				b[px+1][j]=true;
			}
		}
		return b;
	}

	@Override
	public boolean movimientoPosible(Posicion nuevaP, Tablero t) {
		boolean [][] b=this.movimientos();
		
		if(t.getTablero()[nuevaP.getNum()][nuevaP.getLetra()].getPieza().getEquipo()!=this.getEquipo() || (t.getTablero()[nuevaP.getNum()][nuevaP.getLetra()].getPieza() instanceof Vacia)) {
			return b[nuevaP.getNum()][nuevaP.getLetra()];
		}
		else {
			return false;
		}
	}

}
