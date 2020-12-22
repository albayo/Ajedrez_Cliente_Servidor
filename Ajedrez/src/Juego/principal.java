package Juego;
import java.util.List;

import Piezas.Posicion;

public class principal {
	public static void main(String[] args) {

		Juego j=new Juego();
		j.posicionInicial();
		
		j.getTablero().getPiezas();
		List<Posicion>l=j.movimientosPosiblesPieza(j.getTablero().getCasilla(1, 3).getPieza());
		System.out.println(j.getTablero().getCasilla(1, 3).getPieza().getEquipo());
		for(Posicion p:l) {
			System.out.println(p.toString());
		}
		
		
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
