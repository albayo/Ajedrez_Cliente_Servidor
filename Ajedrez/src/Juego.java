
public class Juego {
	private Tablero t;
	
	public Juego() {
		this.t=new Tablero();
		
	}
	
	public void posicionInicial() {
		for(int i=0;i<8;i++) {
			t.cambiarPieza(new Peon(new Posicion(6,i),false),new Posicion(6,i));
			t.cambiarPieza(new Peon(new Posicion(1,i),true),new Posicion(1,i));
		}
			
		
		}
	
}
