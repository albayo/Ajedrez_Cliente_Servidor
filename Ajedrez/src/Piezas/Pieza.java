package Piezas;
import java.io.Serializable;

import Juego.Casilla;
import Juego.Tablero;

public abstract class Pieza implements Serializable {
	private boolean equipo;
	private Posicion posicion;
	
	public Pieza() {
		this.equipo =false;
		this.posicion=new Posicion(0,0);
	}
	public Pieza(boolean e,Posicion p) {
		this.equipo=e;
		this.posicion=p;
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}
	public void setPosicion(Posicion p) {  //Comprobar 
		this.posicion.setX(p.getNum());
		this.posicion.setY(p.getLetra());
	}
	
	public boolean getEquipo() {
		return this.equipo;
	}
	public void setEquipo(boolean e) {
		this.equipo=e;
	}
	
	public abstract boolean[][] movimientos();
	public abstract boolean movimientoPosible(Posicion nuevaPosicion,Tablero t);
	
	public boolean[][] movimientosPosibles(Tablero t){
		boolean b[][]=this.movimientos();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(b[i][j]==true) {
					if(!this.movimientoPosible(new Posicion(i,j), t)) {
						b[i][j]=false;
					}
				}
			}
		}
		return b;
	}
	
	public boolean[][] piezasMismoEquipo(Tablero t){
		boolean b[][]=new boolean[8][8];
		Casilla[][] tablero=t.getTablero();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(tablero[i][j].getPieza().getEquipo()==this.getEquipo() && !(tablero[i][j].getPieza() instanceof Vacia)) {
					b[i][j]=true;
				}
				else b[i][j]=false;
			}
		}
		return b;
	}
	
	public boolean[][] piezasDistintoEquipo(Tablero t){
		boolean b[][]=new boolean[8][8];
		Casilla[][] tablero=t.getTablero();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(tablero[i][j].getPieza().getEquipo()!=this.getEquipo() && !(tablero[i][j].getPieza() instanceof Vacia)) {
					b[i][j]=true;
				}
				else b[i][j]=false;
			}
		}
		return b;
	}
	
	public boolean mover(Posicion nuevaP,Tablero t) {
		if(this.movimientosPosibles(t)[nuevaP.getNum()][nuevaP.getLetra()]) {
			t.cambiarPieza(new Vacia(), this.getPosicion());
			t.cambiarPieza(this, nuevaP);
			return true;
		}
			return false;
	}
	
	public boolean[][] juntarMatrices(boolean b1[][], boolean b2[][]){
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(b2[i][j]==true) {
					b1[i][j]=true;
				}
			}
		}
		return b1;
	}
	 public void imprimirPosicionesPosibles(Tablero t){//MIRAR
	       boolean [][] posicionesPosibles = movimientosPosibles(t);
	                      
	       for (int j = 7; j>=0; j--) {
	           for (int i = 0; i<8; i++) {
	               if(posicionesPosibles[i][j]){
	                   System.out.print("|("+i+","+j+")|");
	                   continue;
	               }
	               System.out.print("| |");
	           }
	           System.out.println("");
	       }
	   }
	

}
