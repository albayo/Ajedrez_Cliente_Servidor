package Piezas;

public class Posicion {
	int x,y;
	public Posicion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public void setPosicion(int i,int j) {
		this.x=i;
		this.y=j;
	}
	public int getNum() {
		return this.x;
	}
	public int getLetra() {
		return this.y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public String toString() {
		return ("("+this.x+","+this.y+")");
	}
	
	public boolean equals(Object obj) {
		Posicion p=(Posicion)obj;
		if(p==this) return true;
		if(this.x==p.getNum() && this.y==p.getLetra()) return true;
		else return false;
	}
}
