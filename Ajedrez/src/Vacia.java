
public class Vacia extends Pieza{

	public Vacia() {
		super();
	}

	@Override
	public boolean[][] movimientos() {
		return new boolean[8][8];
	}

	@Override
	public boolean movimientoPosible(Posicion nuevaPosicion, Tablero t) {
		return false;
	}		
}
