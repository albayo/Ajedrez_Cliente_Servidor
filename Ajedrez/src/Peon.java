
public class Peon extends Pieza{

	@Override
	public boolean[][] movimientos() {
		boolean[][] b=new boolean[8][8];
		if(this.getEquipo()==true) {
			if(this.getPosicion().getNum()!=7) {
				if(this.getPosicion().getNum()==1) {
					b[this.getPosicion().getNum()][this.getPosicion().getLetra()+2]=true;
				}
				b[this.getPosicion().getNum()][this.getPosicion().getLetra()+1]=true;
			}
		}
		else {
			if(this.getPosicion().getNum()!=0) {
				if(this.getPosicion().getNum()==6) {
					b[this.getPosicion().getNum()][this.getPosicion().getLetra()-2]=true;
				}
				b[this.getPosicion().getNum()][this.getPosicion().getLetra()-1]=true;
			}
		}
		return b;
	}

	@Override
	public boolean movimientoPosible(Posicion nuevaPosicion, Tablero t) {
		if(this.movimientos()[nuevaPosicion.getNum()][nuevaPosicion.getLetra()]==true && t.piezaEnCasilla(nuevaPosicion) instanceof Vacia) {
			return true;
		}
		else
			return false;
	}
	
	public boolean[][] movComer(){
		return null;//hacer
	}
	

}
