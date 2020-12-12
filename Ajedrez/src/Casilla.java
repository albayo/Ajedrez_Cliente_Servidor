
public class Casilla {
	private boolean color;
	private Pieza pieza;
	private int letra,num;
	
	public Casilla(int num,int letra,boolean color,Pieza p) {
		this.color=color;
		this.pieza=p;
		this.letra=letra;
		this.num=num;
	}
	public boolean getColor() {
		return this.color;
	}
	
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
	public int[] getUbi() {
		int i[]=new int[2];
		i[0]=this.letra;
		i[1]=this.num;
		return i;
	}
}
