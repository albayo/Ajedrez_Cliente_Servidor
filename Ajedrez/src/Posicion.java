
public class Posicion {
	int x,y;
	public Posicion(int x, int y) {
		this.x=x;
		this.y=x;
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
}
