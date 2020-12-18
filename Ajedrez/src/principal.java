
public class principal {
	public static void main(String[] args) {
		Tablero t=new Tablero();
		Peon p=new Peon();
		p.setEquipo(true);
		Peon p1=new Peon();
		p1.setEquipo(false);
		t.cambiarPieza(p1, new Posicion(3, 2));
		t.cambiarPieza(p, new Posicion(2,3));
		t.getPiezas();
		separar();
		matrizboolean(p.movimientos());
		separar();
		matrizboolean(p.movComer());
		System.out.println(p.movimientoPosible(new Posicion(3, 2), t));
		System.out.println(p.mover(new Posicion(3, 2), t));
		t.getPiezas();
		
	}
	public static void matrizboolean(boolean[][] b) {
		String s="";
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if((b[i][j]==true)) {
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
	public static void separar() {
		for(int i=0;i<5;i++) {
			System.out.println("");
		}
	}
	
}
