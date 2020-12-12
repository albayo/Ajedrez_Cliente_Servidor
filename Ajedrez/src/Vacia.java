
public class Vacia extends Pieza{

	public Vacia() {
		super();
	}

	@Override
	public boolean[][] movimientos() {
		// TODO Auto-generated method stub
		return new boolean[8][8];
	}

	@Override
	public boolean movimientoPosible(Posicion nuevaPosicion, Tablero t) {
		// TODO Auto-generated method stub
		return false;
	}		
}
