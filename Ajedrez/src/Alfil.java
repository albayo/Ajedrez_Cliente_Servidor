
public class Alfil extends Pieza {

	@Override
	public boolean[][] movimientos() {
		Posicion actual=super.getPosicion();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
			
			}
		}
		return null;
	}

	@Override
	public boolean movimientoPosible(Posicion nuevaPosicion, Tablero t) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean casillasIntermediasVacias(Posicion p, Tablero t) {
		return false;
		
	}
}
