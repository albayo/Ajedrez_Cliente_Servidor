package Juego;
import java.util.List;

import Piezas.Posicion;
import Piezas.Rey;

public class principal {
	public static void main(String[] args) {

//		Juego j=new Juego();
//		j.posicionInicial();
//		
//		j.getTablero().getPiezas();
//		List<Posicion>l=j.movimientosPosiblesPieza(j.getTablero().getCasilla(1, 3).getPieza());
//		for(Posicion p:l) {
//			System.out.println(p.toString());
//		}
//		
//		System.out.println(j.moverPieza(j.getTablero().getCasilla(1, 3).getPieza(), new Posicion(2,3)));
//		j.getTablero().getPiezas();
		Tablero t=new Tablero();
		Rey r=new Rey(new Posicion(0,1),true);
		t.cambiarPieza(r, r.getPosicion());
		matrizboolean(r.movimientosPosibles(t));
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
