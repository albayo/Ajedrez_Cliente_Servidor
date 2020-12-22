package Juego;
import java.util.ArrayList;
import java.util.List;

import Piezas.Pieza;
import Piezas.Posicion;
import Piezas.Rey;
import Piezas.Vacia;

public class Tablero {
	private Casilla[][] tablero;
	
	public Tablero() {
		this.tablero=new Casilla[8][8];
		boolean b=false;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.tablero[i][j]=new Casilla(i,j,b,new Vacia());
				if(b==true) {b=false;}
				else {b=true;}
			}
			if(b==true) {b=false;}
			else {b=true;}
		}
	}
	
	public Casilla[][] getTablero(){
		return this.tablero;
	}
	
	public void setTablero(Casilla[][] c) {
		this.tablero=c;
	}
	
	public void cambiarPieza(Pieza p,Posicion pos) {
		
		this.tablero[pos.getNum()][pos.getLetra()].setPieza(p);
		p.setPosicion(pos);
	}
	
	public Pieza piezaEnCasilla(Posicion p) {
		return this.tablero[p.getNum()][p.getLetra()].getPieza();
	}
	public Posicion getRey(boolean b) {
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(this.tablero[i][j].getPieza() instanceof Rey && this.tablero[i][j].getPieza().getEquipo()==b) {
					return this.getTablero()[i][j].getPieza().getPosicion();
				}
			}
		}
		return null;
	}
	public void getPiezas() {
		String s="";
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(!(this.tablero[i][j].getPieza() instanceof Vacia)) {
					s=s+" | 1 |";
				}
				else {
					s=s+" | 0 |";
				}
			}
			System.out.println(s);
			s="";
			}
	}
	public Casilla getCasilla(int x,int y) {
		return this.tablero[x][y];
	}
	public List<Pieza> getPiezasColor(boolean b){
		List<Pieza> l=new ArrayList<>();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
			if(this.getTablero()[i][j].getPieza().getEquipo()==b && !(this.getTablero()[i][j].getPieza() instanceof Vacia)) {
				l.add(this.getTablero()[i][j].getPieza());
			}
		}
		
	}
		return l;
	}
}
