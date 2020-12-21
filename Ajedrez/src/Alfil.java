
public class Alfil extends Pieza {
	
	public Alfil(Posicion p,boolean e) {
		super(e,p);
	}

	@Override
	public boolean[][] movimientos() {
		boolean [][]b=new boolean[8][8];
		Posicion actual=super.getPosicion();
		int num=actual.getNum();
		int letra=actual.getLetra();
		for(int i=1;i<8;i++) {
			if(num+i<=7){
				if(letra+i<=7) {
					b[num+i][letra+i]=true;
				}
				if(letra-i>=0) {
					b[num+i][letra-i]=true;
				}
			}
			if(num-i>=0){
				if(letra+i<=7) {
					b[num-i][letra+i]=true;
				}
				if(letra-i>=0) {
					b[num-i][letra-i]=true;
				}
			}
			
			
		}
		return b;
	}

	@Override
	public boolean movimientoPosible(Posicion nueva, Tablero t) {//
		boolean [][] b=this.movimientos();
		if(b[nueva.getNum()][nueva.getLetra()]==true && casillasIntermediasVacias(nueva, t)) {
			//MIRA A VER SI LA CASILLA ESTA VACÍA O ES DEL OTRO EQUIPO
			if(t.getTablero()[nueva.getNum()][nueva.getLetra()].getPieza() instanceof Vacia || t.getTablero()[nueva.getNum()][nueva.getLetra()].getPieza().getEquipo()!=this.getEquipo()) {
				return true;
			}
		}
		return false;
	}
	public boolean casillasIntermediasVacias(Posicion nueva, Tablero t) {//DEVUELVE TRUE SI NO ENCUENTRA PIEZAS EN SU CAMINO
		boolean vacio=true;
		
		int casillas=Math.abs(nueva.getNum()-this.getPosicion().getNum());
		for(int i=1;i<casillas && vacio==true;i++){
			if(this.getPosicion().getNum()>nueva.getNum()) {
				if(this.getPosicion().getLetra()>nueva.getLetra()) {//ARRIBA
					if(!(t.getTablero()[this.getPosicion().getNum()-i][this.getPosicion().getLetra()-i].getPieza() instanceof Vacia)) vacio=false;
				}
				else {
					if(!(t.getTablero()[this.getPosicion().getNum()-i][this.getPosicion().getLetra()+i].getPieza() instanceof Vacia)) vacio=false;
				}
			}
			else {
				if(this.getPosicion().getLetra()>nueva.getLetra()) {//ABAJO
					if(!(t.getTablero()[this.getPosicion().getNum()+i][this.getPosicion().getLetra()-i].getPieza() instanceof Vacia)) vacio=false;
				}
				else {
					if(!(t.getTablero()[this.getPosicion().getNum()+i][this.getPosicion().getLetra()+i].getPieza() instanceof Vacia)) vacio=false;

				}
			}
			
		}
		
		return vacio;
		
	}
}
