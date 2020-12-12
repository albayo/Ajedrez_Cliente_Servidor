
public abstract class Pieza {
	private boolean equipo;
	private Posicion posicion;
	
	public Pieza() {
		this(false);
	}
	public Pieza(boolean e) {
		this.equipo=e;
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}
	public void setPosicion(Posicion p) {  //Comprobar 
		this.posicion.setX(p.getX());
		this.posicion.setY(p.getY());
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
		if(this.movimientosPosibles(t)[nuevaP.getX()][nuevaP.getY()]) {
			t.cambiarPieza(new Vacia(), this.getPosicion());
			t.cambiarPieza(this, nuevaP);
			return true;
		}
			return false;
	}

}
