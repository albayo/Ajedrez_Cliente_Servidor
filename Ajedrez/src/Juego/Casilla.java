package Juego;
import java.io.Serializable;

import Piezas.Pieza;

public class Casilla implements Serializable{
	//private boolean color;
	private Pieza pieza;
	private int letra,num;
	
	public Casilla(int num,int letra,Pieza p) {
		
		this.pieza=p;
		this.letra=letra;
		this.num=num;
	}
//	public boolean getColor() {
//		return this.color;
//	}
	
	public Pieza getPieza() {
		return this.pieza;
	}
	public void setPieza(Pieza p) {
		this.pieza=p;
	}
	
	public void setUbi(int l,int n) {
		this.letra=l;
		this.num=n;
	}
	public int getLetra() {
		return this.letra;
	}
	public int getNum() {
		return this.num;
	}
	
}
