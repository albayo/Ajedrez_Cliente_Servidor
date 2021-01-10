package Piezas;


import Juego.Tablero;

public class Torre extends Pieza{
	
	public Torre(Posicion p,boolean e) {
		super(e,p);
	}

	@Override
	public boolean[][] movimientos() {
		// TODO Auto-generated method stub
		boolean [][]b=new boolean[8][8];
		Posicion actual=super.getPosicion();
		int num=actual.getNum();
		int letra=actual.getLetra();
		for(int i=0;i<8;i++) {
			if(i!=num) {
				b[i][letra]=true;
			}
			if(i!=letra) {
				b[num][i]=true;
			}
			
		}
		return b;
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
		else {
			if(t.piezaEnCasilla(new Posicion(0,1)) instanceof Rey && nueva.getNum()==0 && nueva.getLetra()==2 &&t.piezaEnCasilla(new Posicion(0,1)).getEquipo()==this.getEquipo()) return true;
			if(t.piezaEnCasilla(new Posicion(0,5)) instanceof Rey && nueva.getNum()==0 && nueva.getLetra()==4 && t.piezaEnCasilla(new Posicion(0,5)).getEquipo()==this.getEquipo()) return true;
			
			if(t.piezaEnCasilla(new Posicion(7,1)) instanceof Rey && nueva.getNum()==7 && nueva.getLetra()==2 && t.piezaEnCasilla(new Posicion(7,1)).getEquipo()==this.getEquipo()) return true;
			if(t.piezaEnCasilla(new Posicion(7,5)) instanceof Rey && nueva.getNum()==7 && nueva.getLetra()==4 && t.piezaEnCasilla(new Posicion(7,5)).getEquipo()==this.getEquipo()) return true;
		}
		return false;
	
	}
	public boolean casillasIntermediasVacias(Posicion nueva, Tablero t) {
		boolean vacio=true;
		boolean suma=false;
		if(this.getPosicion().getNum()==nueva.getNum()) {
			
			int casillas=Math.abs(nueva.getLetra()-this.getPosicion().getLetra());
			if(casillas==0) vacio=false;
			else if(nueva.getLetra()>this.getPosicion().getLetra()) suma=true;
			else suma=false;
			for(int i=1;i<casillas && vacio==true;i++){
				if(suma==true) {
					if(!(t.getTablero()[this.getPosicion().getNum()][this.getPosicion().getLetra()+i].getPieza() instanceof Vacia))vacio=false;
				}
				else {
					if(!(t.getTablero()[this.getPosicion().getNum()][this.getPosicion().getLetra()-i].getPieza() instanceof Vacia))vacio=false;
				}
				
			}
		}
		else if(this.getPosicion().getLetra()==nueva.getLetra()) {
			
			int casillas=Math.abs(nueva.getNum()-this.getPosicion().getNum());
			if(casillas==0) vacio=false;
			else if(nueva.getNum()>this.getPosicion().getNum()) suma=true;
			else suma=false;
			for(int i=1;i<casillas && vacio==true;i++){
				if(suma==true) {
					if(!(t.getTablero()[this.getPosicion().getNum()+i][this.getPosicion().getLetra()].getPieza() instanceof Vacia))vacio=false;
				}
				else {
					if(!(t.getTablero()[this.getPosicion().getNum()-i][this.getPosicion().getLetra()].getPieza() instanceof Vacia))vacio=false;
				}
				
				
			}
		}else return false;
		
		
		return vacio;
	}

}
